package com.ruoyun.dpermission;

/**
 * Created by fanpu on 2017/8/4.
 */

public interface SystemType {
    int NORMAL_6_UNDER = 1;//android 6.0 以下不需要权限
    int ABNORMALITY_6_UNDER = 2;//android 6.0 以下需要权限
    //    int NORMAL_6_ABOVE = 3;//android 6.0以上需要系统权限
    int ABNORMALITY_6_ABOVE = 4;//android 6.0以上不正常情况，不走系统的权限
    int ABNORMALITY_ALL = 5;//都不正常
    int NORMAL_ALL = 6;//都正常
    //是否显示弹出框


    /**
     * Build.MANUFACTURER
     */
     static final String MANUFACTURER_VIVO = "vivo";
     static final String MANUFACTURER_OPPO = "OPPO";
     static final String MANUFACTURER_MEIZU = "Meizu";//魅族
    //     static final String MANUFACTURER_ONEPLUS = "OnePlus";//一加
//     static final String MANUFACTURER_HTC = "HTC";//HTC
     static final String MANUFACTURER_XIAOMI = "Xiaomi";//小米
     static final String MANUFACTURER_SAMSUNG = "samsung";//三星
     static final String MANUFACTURER_HUAWEI = "Huawei";//华为


     static final String MANUFACTURER_SONY = "Sony";//索尼
     static final String MANUFACTURER_LG = "LG";
     static final String MANUFACTURER_LETV = "Letv";//乐视
     static final String MANUFACTURER_ZTE = "ZTE";//中兴
     static final String MANUFACTURER_YULONG = "YuLong";//酷派
     static final String MANUFACTURER_LENOVO = "LENOVO";//联想
}



