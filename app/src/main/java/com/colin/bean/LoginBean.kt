package com.colin.bean

/**
Date:2022-02-24
Time:12:29
author:colin
 *
 * {"success":true,"code":10000,"message":"登录成功","data":null}
 */
class LoginBean {

    var success: Boolean = false;
    var code: Int? = null;
    var message: String? = null;
    var data: String? = null;
    override fun toString(): String {
        return "LoginBean(success=$success, code=$code, message=$message, data=$data)"
    }


}