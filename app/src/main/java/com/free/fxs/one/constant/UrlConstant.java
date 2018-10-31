package com.free.fxs.one.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
        UrlConstant.LocalUrl
})
public @interface UrlConstant {
    String LocalUrl = "http://192.168.31.47:8080/";
}
