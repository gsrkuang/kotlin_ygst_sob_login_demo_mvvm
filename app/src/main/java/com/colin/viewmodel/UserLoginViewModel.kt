package com.colin.viewmodel

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.colin.bean.LoginBean
import com.colin.bean.UserAvaterBean
import com.colin.bean.UserBean
import com.colin.model.ServerApi
import com.colin.request.RequestInterceptor
import com.colin.ui.activity.MainActivity
import com.colin.util.Constants
import com.colin.util.MD5Util
import com.colin.ygst.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
Date:2022-02-24
Time:17:10
author:colin

 * 更改为MVVM开发架构
 */
class UserLoginViewModel constructor(mContext: Activity) {

    var mContext = mContext;
    var imgUrl = ObservableField<String>()
    var edit_account = ObservableField<String>()
    var edit_password = ObservableField<String>()
    var edit_captcha = ObservableField<String>()
    var img_Captcha_url = ObservableField<String>()

    companion object {

        @JvmStatic
        @BindingAdapter("setCaptcha")
        fun loadCaptcha(imageView: ImageView, url: String?) {

            Glide.with(imageView.context)
                .load(
                    String.format(
                        Constants.api_main + "uc/ut/captcha?code=%s",
                        System.currentTimeMillis()
                    )
                )
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView)
        }
    }


    fun refreshCaptcha(view: View) {

        val imageView = view as AppCompatImageView
        Glide.with(view.context)
            .load(
                String.format(
                    Constants.api_main + "uc/ut/captcha?code=%s",
                    System.currentTimeMillis()
                )
            )
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imageView)
    }

    fun accChange(): TextWatcher {

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, p2: Int, p3: Int) {

                if (start == 10) {
                    getUserAvater(s.toString() + "")
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }

        return textWatcher


    }

    fun getUserAvater(account: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.api_main)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serverApi = retrofit.create(ServerApi::class.java)

        val call = serverApi.setUserAvater(account)

        call.enqueue(object : Callback<UserAvaterBean> {
            override fun onResponse(
                call: Call<UserAvaterBean>,
                response: Response<UserAvaterBean>
            ) {
                val userAvaterBean = response.body()
                val url = userAvaterBean.data
                imgUrl.set(url)
            }

            override fun onFailure(call: Call<UserAvaterBean>, throwable: Throwable) {}
        })
    }

    fun login(view: View) {


//        Log.e("++++test", userLoginView.getUserName());
        val acc = edit_account.get()
        var pass = edit_password.get()
        val captcha = edit_captcha.get()
        if (acc == null || pass == null || captcha == null) {
            return
        }

        //创建Retrofit对象

        //创建Retrofit对象
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.api_main) //设置网络请求的Url地址
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .build()

        val serverApi = retrofit.create(ServerApi::class.java)
        val userBean = UserBean()
        userBean.phoneNum = acc
        userBean.password = MD5Util.getMD5String(pass)


        Log.e("+sobCaptchaKey", RequestInterceptor.sobCaptchaKey.toString());

        val call =
            serverApi.userLogin(RequestInterceptor.sobCaptchaKey.toString(), userBean, captcha)

        call!!.enqueue(object : Callback<LoginBean?> {
            override fun onResponse(call: Call<LoginBean?>, response: Response<LoginBean?>) {
                //Response<LoginBean> responseBody  = call.execute();
                Log.e("+++login", response.headers().toString())

                //登陆成功后，获取sob_token
                val sob_token = response.headers()["sob_token"]

//                val sob_token = response.headers()["sob_token"]


                var loginBean: LoginBean

                loginBean = response.body()!!

                if (loginBean != null || !"".equals(loginBean)) {

                    if (loginBean != null) {
                        showTips(loginBean)
                    }

                }

                if (loginBean.success == true) {
                    //从DengluActivity切换到MianShiActivity 直接跳
                    val intent = Intent()
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.setClass(mContext, MainActivity::class.java)
                    mContext.startActivity(intent)
                    mContext.finish()
                }
            }

            override fun onFailure(call: Call<LoginBean?>, throwable: Throwable) {}
        })
    }


    fun showTips(loginBean: LoginBean) {
        Log.e("+++login", loginBean.toString())
        Toast.makeText(mContext, loginBean.message, Toast.LENGTH_SHORT).show()
    }
}