package vip.ruoyun.permission.helper.check;

import android.Manifest;
import android.content.Context;

import vip.ruoyun.permission.helper.core.IChecker;
import vip.ruoyun.permission.helper.core.MissHelperConfiguration;

/**
 * Created by ruoyun on 2019-07-05.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class CallLogChecker implements IChecker {

    public final String[] NEED_PERMISSION = {
            Manifest.permission.READ_CALL_LOG,  //必选
            Manifest.permission.WRITE_CALL_LOG,  //必选
            Manifest.permission.PROCESS_OUTGOING_CALLS,  //必选
    };


    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }
}
