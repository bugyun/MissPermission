package vip.ruoyun.permission.pro.check;

import android.Manifest;
import vip.ruoyun.permission.pro.R;

/**
 * Created by ruoyun on 2019-06-25.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CalendarChecker {

    public static final String[] NEED_PERMISSION = {
            Manifest.permission.READ_CALENDAR,  //必选
            Manifest.permission.WRITE_CALENDAR,  //必选
    };

    public static final String PERMISSION_NAME = "日历";

    public static final int PERMISSION_ICON_RES = R.drawable.miss_permission_ic_calendar;

}
