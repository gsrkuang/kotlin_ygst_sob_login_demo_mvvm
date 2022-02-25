package com.colin.model

import com.colin.bean.LoginBean
import com.colin.bean.UserAvaterBean
import com.colin.bean.UserBean
import retrofit2.Call
import retrofit2.http.*

/**
Date:2022-02-24
Time:15:13
author:colin
 */
interface ServerApi {

    /**
     * 用户登录接口
     * @param headerKey
     * @param userBean
     * @param captcha
     * @return
     */
    @POST("uc/user/login/{captcha}")
    fun userLogin(
        @Header("l_c_i") headerKey: String?,
        @Body userBean: UserBean?,
        @Path("captcha") captcha: String?
    ): Call<LoginBean?>?

    @GET("uc/user/avatar/{phoneNum}")
    fun setUserAvater(
        @Path("phoneNum") phoneNum:String?
    ):Call<UserAvaterBean>

}