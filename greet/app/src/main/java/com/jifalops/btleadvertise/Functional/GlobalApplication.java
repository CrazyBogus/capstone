package com.jifalops.btleadvertise.Functional;

import android.app.Activity;
import android.app.Application;

import com.jifalops.btleadvertise.Adapters.kakaoSDKAdapter;
import com.kakao.auth.KakaoSDK;

/**
 * Created by client on 2016. 4. 24..
 */
public class GlobalApplication extends Application {
    private static volatile GlobalApplication obj = null;
    private static volatile Activity currentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        obj = this;

        KakaoSDK.init(new kakaoSDKAdapter());
    }

    public static GlobalApplication getGlobalApplicationContext() {
        return obj;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }
}
