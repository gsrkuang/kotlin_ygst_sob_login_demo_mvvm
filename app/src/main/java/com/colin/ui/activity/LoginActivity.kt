package com.colin.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.colin.ui.fragment.LoginFragment
import com.colin.ygst.R

/**
Date:2022-02-24
Time:16:39
author:colin
 */
class LoginActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //使得状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().setStatusBarColor(this.getResources().getColor(R.color.black));
        supportActionBar!!.hide()

        showLoginFragment()

    }

    fun showLoginFragment(){
        supportFragmentManager.beginTransaction().add(R.id.fl_denglu,LoginFragment())
            .commit()
    }

    override fun onResume() {
        super.onResume()
    }

}