package com.example.viewpagerdemo.app;

import android.app.Application;

/**
 * Application class of ViewPagerDemo app
 *
 * @author pranit
 * @version 1.0
 * @since 24/3/17
 */

public class ViewPagerDemoApplication extends Application {
    private static ViewPagerDemoApplication sInstance;

    public ViewPagerDemoApplication(){
        sInstance = this;
    }

    public static ViewPagerDemoApplication getInstance(){
        if(sInstance == null){
            throw new IllegalStateException();
        }
        return sInstance;
    }
}
