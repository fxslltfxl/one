package com.free.fxs.one.retrofit.userinfo

import com.free.fxs.one.model.remote.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IUser {
    @GET("user/load/{name}")
    fun test(@Path("name") name:String):Single<MutableList<User>>
}