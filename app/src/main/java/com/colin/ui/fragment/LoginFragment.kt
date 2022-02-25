package com.colin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.colin.ui.activity.MainActivity
import com.colin.ygst.R
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*

/**
Date:2022-02-24
Time:16:48
author:colin
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.login_fragment,container,false)
        //bt01 点击跳转到登陆界面

        view.dl.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                //通过FragmentTransaction对象的replace()方法让OtherFragment
                // 把当前Fragment替换成输入手机号的Fragment，
                .replace(R.id.fl_denglu,LoginAccountFragment())
                .commit();
        }

        //bt02 点击体验
        view.ty.setOnClickListener{
            //从DengluActivity切换到MianShiActivity 直接跳
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.setClass(requireActivity(), MainActivity::class.java)
            requireActivity().finish()
            startActivity(intent)
        }




        return view
    }
}