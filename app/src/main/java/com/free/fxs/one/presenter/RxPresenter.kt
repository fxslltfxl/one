package com.free.fxs.one.presenter

import com.free.fxs.one.contract.BaseContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class RxPresenter<T : BaseContract.BaseView> : BaseContract.BasePresenter<T> {
    var view: T? = null
    var mDisposable: CompositeDisposable? = null

    override fun start(view: T) {
        this.view = view
        mDisposable = CompositeDisposable()
    }

    override fun detachView() {
        view = null
        unSubscribe()
    }

    fun addDisposable(disposable: Disposable) {
        if (mDisposable == null) {
            mDisposable = CompositeDisposable()
        }
        mDisposable?.add(disposable)
    }

    private fun unSubscribe() {
        if (mDisposable != null) {
            mDisposable?.dispose()
        }
    }

}