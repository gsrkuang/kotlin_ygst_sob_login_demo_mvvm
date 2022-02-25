package com.colin.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.colin.ui.activity.MainActivity
import com.colin.viewmodel.UserLoginViewModel
import com.colin.ygst.R
import com.colin.ygst.databinding.AccountLoginFragmentBinding
import kotlinx.android.synthetic.main.title_bar_login.view.*

/**
Date:2022-02-24
Time:17:05
author:colin
 */
class LoginAccountFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var mBinding = DataBindingUtil.inflate<AccountLoginFragmentBinding>(inflater, R.layout.account_login_fragment, container, false)
        mBinding.userLoginViewModel = UserLoginViewModel(requireActivity())
        val view = mBinding.root;

        initEvent(view)
        return view
    }

    fun initEvent(view: View) {
        view.title_bar_jump.setOnClickListener(View.OnClickListener {

            //从DengluActivity切换到MianShiActivity 直接跳
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.setClass(requireActivity(), MainActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        })

        view.title_bar_register.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "请到官网注册哦~", Toast.LENGTH_SHORT).show()

        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}


