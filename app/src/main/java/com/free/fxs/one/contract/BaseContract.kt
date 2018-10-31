package com.free.fxs.one.contract

interface BaseContract {
    interface BasePresenter<T> {
        /**
         * start
         *
         * @param view view
         */
        fun start(view: T)

        /**
         * detachView
         */
        fun detachView()
    }

    interface BaseView {
        fun showError(error: String)

        fun showInfo()
    }
}
