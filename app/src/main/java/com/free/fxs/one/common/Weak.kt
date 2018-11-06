package com.free.fxs.one.common

import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class Weak<T : Any?>(init: () -> T?) {

    constructor() : this({
        null
    })

    private var weakReference = WeakReference(init())

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return weakReference.get()
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        weakReference = WeakReference(value)
    }
}