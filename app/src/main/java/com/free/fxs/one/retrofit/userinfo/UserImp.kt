package com.free.fxs.one.retrofit.userinfo

import com.free.fxs.one.model.remote.User
import com.free.fxs.one.retrofit.RetrofitHelper
import io.reactivex.Single

object UserImp {

    private val iUser: IUser?
        get() = RetrofitHelper.getInstance()?.retrofit?.create(IUser::class.java)

    fun test(name: String): Single<MutableList<User>> {
        return iUser!!.test(name)
    }
}