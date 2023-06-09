package com.mewz.wechat.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mewz.wechat.R
import com.mewz.wechat.activities.login.RegisterActivity
import com.mewz.wechat.databinding.DialogEditProfileBinding
import com.mewz.wechat.databinding.DialogQrCodeBinding
import com.mewz.wechat.databinding.FragmentProfileBinding
import com.mewz.wechat.databinding.ViewPodMomentBinding
import com.mewz.wechat.dialogs.EditProfileDialog
import com.mewz.wechat.dialogs.QrCodeDialog
import com.mewz.wechat.mvp.presenters.ProfilePresenter
import com.mewz.wechat.mvp.presenters.impls.ProfilePresenterImpl
import com.mewz.wechat.mvp.views.ProfileView
import com.mewz.wechat.utils.months
import com.mewz.wechat.views.viewpods.MomentViewPod
import java.util.*

class ProfileFragment : Fragment(), ProfileView {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var mViewPod: MomentViewPod

    private lateinit var mPresenter: ProfilePresenter

    private val PICK_IMAGE_REQUEST = 1111
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

    override fun showEditProfileDialog() {
        val dialogBinding = DialogEditProfileBinding.inflate(layoutInflater)
        val dialog = EditProfileDialog(requireActivity())

        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        setUpDateOfBirth(dialogBinding)
        setUpGender(dialogBinding)

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

    override fun showQrCode() {
        val dialogBinding = DialogQrCodeBinding.inflate(layoutInflater)
        val dialog = QrCodeDialog(requireActivity())

        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
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

    private fun setUpViewPod() {
        mViewPod = binding.vpProfileMoment.root
        mViewPod.setUpMomentViewPod()
    }


}