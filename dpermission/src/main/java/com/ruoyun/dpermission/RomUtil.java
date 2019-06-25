package com.ruoyun.dpermission;

import android.os.Build;
import android.util.Log;
import com.ruoyun.dpermission.manufacturer.*;

import java.util.HashMap;


/**
 * 判断手机类型
 */
public class RomUtil {
    /**
     * Build.MANUFACTURER
     */
    private static final String MANUFACTURER_VIVO = "vivo";
    private static final String MANUFACTURER_OPPO = "OPPO";
    private static final String MANUFACTURER_MEIZU = "Meizu";//魅族
    //    private static final String MANUFACTURER_ONEPLUS = "OnePlus";//一加
//    private static final String MANUFACTURER_HTC = "HTC";//HTC
    private static final String MANUFACTURER_XIAOMI = "Xiaomi";//小米
    private static final String MANUFACTURER_SAMSUNG = "samsung";//三星
    private static final String MANUFACTURER_HUAWEI = "Huawei";//华为


    private static final String MANUFACTURER_SONY = "Sony";//索尼
    private static final String MANUFACTURER_LG = "LG";
    private static final String MANUFACTURER_LETV = "Letv";//乐视
    private static final String MANUFACTURER_ZTE = "ZTE";//中兴
    private static final String MANUFACTURER_YULONG = "YuLong";//酷派
    private static final String MANUFACTURER_LENOVO = "LENOVO";//联想

    private static IRomStrategy iRomStrategy;

    private static HashMap<String, Class<? extends IRomStrategy>> romStrategyHashMap = new HashMap<>();

    static {
        romStrategyHashMap.put(HUAWEI.MANUFACTURER, HUAWEI.class);
        romStrategyHashMap.put(MEIZU.MANUFACTURER, MEIZU.class);
        romStrategyHashMap.put(OPPO.MANUFACTURER, OPPO.class);
        romStrategyHashMap.put(VIVO.MANUFACTURER, VIVO.class);
        romStrategyHashMap.put(XIAOMI.MANUFACTURER, XIAOMI.class);
        romStrategyHashMap.put(DefaultRom.MANUFACTURER, DefaultRom.class);
    }


    public static boolean isNeed() {
        boolean isShow = false;
        Class<? extends IRomStrategy> aClass = romStrategyHashMap.get(Build.MANUFACTURER.toUpperCase());
        if (null == aClass) {
            aClass = romStrategyHashMap.get(DefaultRom.MANUFACTURER);
        }

        try {
            if (aClass != null) {
                iRomStrategy = aClass.newInstance();
                isShow = iRomStrategy.isNeedShowHint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isShow;
    }


    /**
     * 此函数可以自己定义
     */
    public static boolean isNeedShowHint() {
        boolean isShow;
        switch (Build.MANUFACTURER) {
            case MANUFACTURER_OPPO:
            case MANUFACTURER_VIVO:
            case MANUFACTURER_XIAOMI:
            case MANUFACTURER_MEIZU:
                isShow = true;
                break;
            case MANUFACTURER_LG:
            case MANUFACTURER_LETV:
            case MANUFACTURER_HUAWEI:
            case MANUFACTURER_SONY:
            default:
                Log.e("goToSetting", "目前暂不支持此系统");
                isShow = false;
                break;
        }
        return isShow;
    }
}