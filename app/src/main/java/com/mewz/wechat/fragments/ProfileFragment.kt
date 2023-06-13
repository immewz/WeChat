package com.mewz.wechat.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.mewz.wechat.activities.login.RegisterActivity
import com.mewz.wechat.data.vos.MomentVO
import com.mewz.wechat.data.vos.MyMomentVO
import com.mewz.wechat.data.vos.UserVO
import com.mewz.wechat.databinding.DialogEditProfileBinding
import com.mewz.wechat.databinding.DialogQrCodeBinding
import com.mewz.wechat.databinding.FragmentProfileBinding
import com.mewz.wechat.dialogs.EditProfileDialog
import com.mewz.wechat.dialogs.QrCodeDialog
import com.mewz.wechat.mvp.presenters.ProfilePresenter
import com.mewz.wechat.mvp.presenters.impls.ProfilePresenterImpl
import com.mewz.wechat.mvp.views.ProfileView
import com.mewz.wechat.utils.loadBitMapFromUri
import com.mewz.wechat.utils.scaleToRatio
import com.mewz.wechat.views.viewpods.MomentViewPod
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.*

class ProfileFragment : Fragment(), ProfileView {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var mViewPod: MomentViewPod

    private lateinit var mPresenter: ProfilePresenter

    private val PICK_IMAGE_REQUEST = 1111
    private var bitmap: Bitmap? = null
    private var mUser:UserVO? = null
    private var mMomentList:ArrayList<MyMomentVO> = arrayListOf()

    private var email:String = ""
    private var password:String = ""
    private var qrCode:String = ""
    private var imageUrl:String = ""
    private var day:String = ""
    private var month:String = ""
    private var year:String = ""
    private var gender:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_profile, container, false)

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

        setUpViewPod()
        setUpListeners()

        mPresenter.onUiReady(requireActivity(), this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ProfilePresenterImpl::class.java]
        mPresenter.initPresenter(this)
    }

    private fun setUpListeners() {
        binding.btnEditProfile.setOnClickListener {
            mPresenter.onTapEditProfileButton()
        }

        binding.btnChangeProfile.setOnClickListener {
            mPresenter.onTapChangeImage()
        }

        binding.btnShowCode.setOnClickListener {
            mPresenter.onTapQrCodeImage()
        }
    }

    private fun setUpViewPod() {
        mViewPod = binding.vpProfileMoment.root
        mViewPod.setUpMomentViewPod(mPresenter)
    }

    override fun showUserInformation(userList: List<UserVO>) {
        for (user in userList){
            if (mPresenter.getUserId() == user.userId){
                mUser = user
                binding.tvProfileName.text = user.userName
                binding.tvPhoneNumber.text = user.phone
                binding.tvDateOfBirth.text = user.dateOfBirth
                binding.tvGender.text = user.gender

                email = user.email
                password = user.password
                qrCode = user.qrCode
                imageUrl = user.imageUrl

                Glide.with(requireActivity())
                    .load(user.imageUrl)
                    .into(binding.ivProfileImage)

                binding.btnShowCode.setImageBitmap(textToImageEncode(qrCode))

            }
        }
    }

    override fun showEditProfileDialog() {
        val dialogBinding = DialogEditProfileBinding.inflate(layoutInflater)
        val dialog = EditProfileDialog(requireActivity())

        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        dialogBinding.etName.setText(binding.tvProfileName.text.toString())
        dialogBinding.etPhone.setText(binding.tvPhoneNumber.text.toString())

        setUpDateOfBirth(dialogBinding)
        setUpGender(dialogBinding)

        dialogBinding.btnSave.setOnClickListener {
            val userName = dialogBinding.etName.text.toString()
            val phone = dialogBinding.etPhone.text.toString()
            val birthDate = "$year-$month-$day"
            val user = UserVO(mPresenter.getUserId(), userName, phone, email, password, birthDate, gender, qrCode, imageUrl)
            mPresenter.updateUserInformation(user)
            dialog.dismiss()
        }

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }


    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Selected Picture"),
            RegisterActivity.PICK_IMAGE_REQUEST
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            val filePath = data.data

            try {
                filePath?.let { fileUrl ->
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source = ImageDecoder.createSource(requireActivity().contentResolver, fileUrl)
                        val bitmapImage = ImageDecoder.decodeBitmap(source)
                        bitmap = bitmapImage
                        bitmap?.let { image ->
                            mUser?.let {user ->
                                mPresenter.uploadAndUpdateProfileImage(image, user)
                            }
                        }

                    } else {
                        val bitmapImage = MediaStore.Images.Media.getBitmap(
                            context?.applicationContext?.contentResolver, fileUrl
                        )
                        bitmap = bitmapImage
                        bitmap?.let { image ->
                            mUser?.let {user ->
                                mPresenter.uploadAndUpdateProfileImage(image, user)
                            }
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun showQrCode() {
        val dialogBinding = DialogQrCodeBinding.inflate(layoutInflater)
        val dialog = QrCodeDialog(requireActivity())

        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.ivMyQrCode.setImageBitmap(textToImageEncode(qrCode))

        dialog.show()
    }

    override fun showMoments(momentList: List<MyMomentVO>) {
        for (moment in momentList) {
            if(moment.isBookmarked) {
                mMomentList.add(moment)
            }
        }
        mViewPod.setNewData(mMomentList)
        Log.d("ProfileFragment","$momentList")
    }

    override fun getMomentIsBookmarked(id: String, bookmarked: Boolean) {
        for(moment in mMomentList) {
            if(id == moment.id) {
                if(!bookmarked) {
                    moment.isBookmarked = false
                    mPresenter.createMoment(moment)
                    break
                }
            }
        }
        mViewPod.setNewData(mMomentList)
    }

    @Throws(WriterException::class)
    fun textToImageEncode(Value: String?): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                Value,
                BarcodeFormat.QR_CODE,
                100, 100, null
            )
        } catch (IllegalArgumentException: IllegalArgumentException) {
            return null
        }
        val bitMatrixWidth = bitMatrix.width
        val bitMatrixHeight = bitMatrix.height
        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)
        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth
            for (x in 0 until bitMatrixWidth) {
                pixels[offset + x] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
        bitmap.setPixels(pixels, 0, 100, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }

    override fun showError(error: String) {
        Toast.makeText(requireActivity(),error, Toast.LENGTH_SHORT).show()
    }

    private fun setUpDateOfBirth(dialogBinding: DialogEditProfileBinding) {
        val dayAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, getDays())
        val monthAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, getMonths())
        val yearAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, getYears())

        dialogBinding.spinnerDay.adapter = dayAdapter
        dialogBinding.spinnerMonth.adapter = monthAdapter
        dialogBinding.spinnerYear.adapter = yearAdapter

        setUpDateOfBirthSpinner(dialogBinding)

    }

    private fun setUpDateOfBirthSpinner(dialogBinding: DialogEditProfileBinding) {
        dialogBinding.spinnerDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapter: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position > 0) {
                    day = adapter?.getItemAtPosition(position).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        dialogBinding.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapter: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position > 0) {
                    month = adapter?.getItemAtPosition(position).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        dialogBinding.spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapter: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position > 0) {
                    year = adapter?.getItemAtPosition(position).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

        private fun getYears(): List<String> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = mutableListOf<String>("Year")
        for (year in 1900..currentYear) {
            years.add(year.toString())
        }
        return years
    }

    private fun getMonths(): List<String> {
        val months = mutableListOf<String>("Month")
        for (i in 1..12) {
            months.add(i.toString())
        }
        return months
    }

    private fun getDays(): List<String> {
        val days = mutableListOf<String>("Day")
        for (i in 1..31) {
            days.add(i.toString())
        }
        return days
    }

    private fun setUpGender(dialogBinding: DialogEditProfileBinding) {
        dialogBinding.rbMale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                gender = "Male"
            }
        }

        dialogBinding.rbFemale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                gender = "Female"
            }
        }

        dialogBinding.rbOther.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                gender = "Other"
            }
        }
    }



}