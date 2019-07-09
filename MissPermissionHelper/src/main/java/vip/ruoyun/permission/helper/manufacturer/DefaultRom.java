package vip.ruoyun.permission.helper.manufacturer;

import android.content.Intent;

import vip.ruoyun.permission.helper.core.IRomStrategy;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class DefaultRom implements IRomStrategy {

    @Override
    public Intent settingIntent() throws Exception {
        return null;
    }

    @Override
    public boolean isNeedShowHint() {
        return false;
    }

    @Override
    public boolean isNeedCheck() {
        return false;
    }

    @Override
    public String getManufacturerName() {
        return "DEFAULT";
    }
}
