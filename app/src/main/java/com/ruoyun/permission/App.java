package com.ruoyun.permission;

import android.app.Application;
import android.content.res.Resources;

import vip.ruoyun.screen.ScreenHelper;

public class App extends Application {

    @Override
    public Resources getResources() {
        return ScreenHelper.applyAdapt(super.getResources(), 375f, ScreenHelper.WIDTH_DP);
    }
}
