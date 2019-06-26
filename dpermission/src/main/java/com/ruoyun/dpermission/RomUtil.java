package com.ruoyun.dpermission;

import android.os.Build;
import com.ruoyun.dpermission.manufacturer.*;

import java.util.HashMap;


/**
 * 判断手机类型
 */
public class RomUtil {

    private static HashMap<String, Class<? extends IRomStrategy>> romStrategyHashMap = new HashMap<>();

    public static void addRomStrategy(String name, Class<? extends IRomStrategy> clazz) {
        romStrategyHashMap.put(name, clazz);
    }

    static {
        romStrategyHashMap.put(HUAWEI.MANUFACTURER, HUAWEI.class);
        romStrategyHashMap.put(MEIZU.MANUFACTURER, MEIZU.class);
        romStrategyHashMap.put(OPPO.MANUFACTURER, OPPO.class);
        romStrategyHashMap.put(VIVO.MANUFACTURER, VIVO.class);
        romStrategyHashMap.put(XIAOMI.MANUFACTURER, XIAOMI.class);
    }

    public static IRomStrategy get() {
        IRomStrategy iRomStrategy;
        Class<? extends IRomStrategy> aClass = romStrategyHashMap.get(Build.MANUFACTURER.toUpperCase());
        if (null != aClass) {
            try {
                iRomStrategy = aClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                iRomStrategy = new DefaultRom();
            }
        } else {
            iRomStrategy = new DefaultRom();
        }
        return iRomStrategy;
    }
}