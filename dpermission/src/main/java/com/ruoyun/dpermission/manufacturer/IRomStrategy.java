package com.ruoyun.dpermission.manufacturer;

import android.content.Intent;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public interface IRomStrategy {

    String MANUFACTURER = "DEFAULT";

    Intent settingIntent() throws Exception;

    boolean isNeedShowHint();

}
