package com.colin.bean

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.colin.ygst.R

/**
Date:2022-02-24
Time:14:26
author:colin
 * 用于登陆界面，填写手机号码的时候自动显示用户头像
 *
 *{
 *     "success": true,
 *     "code": 10000,
 *     "message": "获取头像成功",
 *     "data": "https://images.sunofbeaches.com/content/2021_12_02/915973725776510976.png"
 * }
 */
class UserAvaterBean {
    var success = false
    var code = 0
    var message: String? = null
    var data: String? = null


    override fun toString(): String {
        return "UserAvaterBean(success=$success, code=$code, message=$message, data=$data)"
    }


    companion object {
        @JvmStatic
        @BindingAdapter("setImgUrl")
        fun loadImage(imageView: ImageView, url: String?) {
            Glide.with(imageView.context)
                .load(url)
                .placeholder(R.drawable.icon_ygst) //加载成功前显示的图片
                .fallback(R.drawable.icon_ygst) //url为空的时候,显示的图片
                .into(imageView)
        }
    }

}