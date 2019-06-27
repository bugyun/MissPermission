package vip.ruoyun.permission.helper.manufacturer;

import android.content.Intent;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class HUAWEI implements IRomStrategy {

    public static final String MANUFACTURER = "HUAWEI";


    @Override
    public Intent settingIntent() throws Exception {
        return null;
    }

    @Override
    public boolean isNeedShowHint() {
        return true;
    }
}
