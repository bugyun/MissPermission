package vip.ruoyun.permission.helper;

import android.os.Build;

import java.util.HashMap;

import vip.ruoyun.permission.helper.manufacturer.DefaultRom;
import vip.ruoyun.permission.helper.manufacturer.HUAWEI;
import vip.ruoyun.permission.helper.manufacturer.IRomStrategy;
import vip.ruoyun.permission.helper.manufacturer.MEIZU;
import vip.ruoyun.permission.helper.manufacturer.OPPO;
import vip.ruoyun.permission.helper.manufacturer.VIVO;
import vip.ruoyun.permission.helper.manufacturer.XIAOMI;


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

    private static IRomStrategy iRomStrategy;

    public static IRomStrategy get() {
        if (iRomStrategy == null) {
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
        }
        return iRomStrategy;
    }
}