package com.ruoyun.dpermission;

/**
 * Created by fanpu on 2017/8/7.
 */
public interface RequestCode {
    int PHONE = 0x00;//电话
    int LOCATION = 0x01;//位置
    int CAMERA = 0x02;//相机
    int AUDIO = 0x04;//语音
    int EXTERNAL = 0x08;//存储
    int CONTACTS = 0x12;//联系人
    int MORE = 0x10;//多个
}
