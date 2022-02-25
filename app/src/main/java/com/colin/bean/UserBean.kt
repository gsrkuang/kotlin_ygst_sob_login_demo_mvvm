package com.colin.bean

/**
Date:2022-02-24
Time:15:12
author:colin
用于登陆接口传入的bean
 */
class UserBean {

    var phoneNum:String?=null;
    var password:String?=null;
    override fun toString(): String {
        return "UserBean(phoneNum=$phoneNum, password=$password)"
    }


}