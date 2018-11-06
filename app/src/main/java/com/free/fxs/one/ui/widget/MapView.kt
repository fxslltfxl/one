package com.free.fxs.one.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RawRes
import com.free.fxs.one.R
import javax.xml.parsers.DocumentBuilderFactory

class MapView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {


    @RawRes
    private var rawResId: Int = 0

    init {
        val openRawResource = context.resources.openRawResource(R.raw.china)
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val document = builder.parse(openRawResource)
        val nodeList = document.documentElement.getElementsByTagName("path")
        for (i: Int in 0..nodeList.length) {

        }
    }

}

