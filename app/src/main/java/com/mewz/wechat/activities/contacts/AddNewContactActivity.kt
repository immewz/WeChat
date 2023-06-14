package com.mewz.wechat.activities.contacts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.mewz.wechat.R
import com.mewz.wechat.activities.BaseActivity
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.ActivityAddNewContactBinding
import com.mewz.wechat.mvp.presenters.NewContactPresenter
import com.mewz.wechat.mvp.presenters.impls.NewContactPresenterImpl
import com.mewz.wechat.mvp.presenters.impls.SplashPresenterImpl
import com.mewz.wechat.mvp.views.NewContactView
import com.mewz.wechat.mvp.views.SplashView

class CaptureActivityPortrait : CaptureActivity()
class AddNewContactActivity : BaseActivity(), NewContactView {

    private lateinit var binding: ActivityAddNewContactBinding

    private lateinit var mPresenter: NewContactPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddNewContactActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpListeners()
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<NewContactPresenterImpl, NewContactView>()
    }

    private fun setUpListeners() {
        binding.btnAddContactBack.setOnClickListener {
            onBackPressed()
        }

        binding.ivScan.setOnClickListener {
            setUpQRCodeScanner()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                mPresenter.getUsers(result.contents)
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun setUpQRCodeScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setPrompt("Scan a QR Code")
        integrator.setCameraId(0) // Use a specific camera of the device

        integrator.setOrientationLocked(true)
        integrator.setBeepEnabled(true)
        integrator.captureActivity = CaptureActivityPortrait::class.java
        integrator.initiateScan()
    }

    override fun getUsers(userList: List<UserVO>, qrExporterUserId: String) {
        outer@ for (user in userList) {
            if (mPresenter.getScannerUserId() == user.userId) {
                for (exportUser in userList) {
                    if (qrExporterUserId == exportUser.userId) {
                        mPresenter.createContact(
                            mPresenter.getScannerUserId(),
                            qrExporterUserId,
                            exportUser
                        )
                        break@outer
                    }
                }
            }
        }

        outer@ for (user in userList) {
            if (qrExporterUserId == user.userId) {
                for (scannerUser in userList) {
                    if (mPresenter.getScannerUserId() == scannerUser.userId) {
                        mPresenter.createContact(
                            qrExporterUserId,
                            mPresenter.getScannerUserId(),
                            scannerUser
                        )
                        break@outer
                    }
                }
            }
        }
    }

}