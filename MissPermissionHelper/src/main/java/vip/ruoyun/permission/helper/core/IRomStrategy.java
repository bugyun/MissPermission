package vip.ruoyun.permission.helper.core;

import android.content.Intent;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public interface IRomStrategy {

    String MANUFACTURER = "";

    Intent settingIntent() throws Exception;

    boolean isNeedShowHint();

    boolean isNeedCheck();

    String getManufacturerName();

}
