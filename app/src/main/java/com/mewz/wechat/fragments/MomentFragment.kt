package com.mewz.wechat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mewz.wechat.R
import com.mewz.wechat.activities.moments.NewMomentActivity
import com.mewz.wechat.databinding.FragmentMomentBinding
import com.mewz.wechat.mvp.presenters.MomentPresenter
import com.mewz.wechat.mvp.presenters.impls.MomentPresenterImpl
import com.mewz.wechat.mvp.views.MomentView
import com.mewz.wechat.views.viewpods.MomentViewPod

class MomentFragment : Fragment(), MomentView {

    private lateinit var binding: FragmentMomentBinding

    private lateinit var mViewPod: MomentViewPod

    private lateinit var mPresenter: MomentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_moment, container, false)

        binding = FragmentMomentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

        setUpViewPod()
        setUpListeners()


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MomentPresenterImpl::class.java]
        mPresenter.initPresenter(this)
    }

    private fun setUpListeners() {
        binding.btnAddMoment.setOnClickListener {
            mPresenter.onTapNewMomentButton()
        }
    }

    private fun setUpViewPod() {
        mViewPod = binding.vpMoment.root
        mViewPod.setUpMomentViewPod()
    }

    override fun navigateToNewMomentScreen() {
        startActivity(context?.let { NewMomentActivity.newIntent(it) })
    }

    override fun showError(error: String) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
    }


}