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
public class PhoneCheck implements IChecker {

//    CallLogChecker

    public final String[] NEED_PERMISSION = {
            Manifest.permission.READ_PHONE_STATE,//
            Manifest.permission.READ_PHONE_NUMBERS,//
            Manifest.permission.CALL_PHONE,//
            Manifest.permission.CALL_PHONE,//
            Manifest.permission.ANSWER_PHONE_CALLS,//
            Manifest.permission.WRITE_CALL_LOG,//
            Manifest.permission.ADD_VOICEMAIL,//
            Manifest.permission.USE_SIP,//
            Manifest.permission.PROCESS_OUTGOING_CALLS,//
    };

    @Override
    public boolean isCheckEnable(Context context, MissHelperConfiguration configuration) {
        return false;
    }
}




