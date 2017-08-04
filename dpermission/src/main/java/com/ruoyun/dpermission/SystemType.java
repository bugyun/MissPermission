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
}



