package com.free.fxs.one

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.free.fxs.one.retrofit.userinfo.UserImp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val subscribe = UserImp.test("fxs")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                it.forEach {user->
                    Log.e("test", user.name)
                }
            }
            .subscribe(
                {
                    it.forEach {user->
                        Log.e("test", user.name)
                    }
                }, { t ->
                    Log.e("test", t.message)
                }
            )

        CompositeDisposable().add(subscribe)
    }
}
