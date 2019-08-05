# MissPermission
android 权限库，超级简单好用！！
---

## MissPermissionHelper
简化操作帮助类，很少的代码就可以获取权限。

### 配置
```xml
implementation 'vip.ruoyun.permission:miss-helper:1.0.0'
```

### 使用

#### 单纯检查是否有权限
```java
boolean isHasReadCalendarPermission = MissPermissionHelper.check(this, new String[]{Manifest.permission.READ_CALENDAR});
if (isHasReadCalendarPermission) {
    //有权限
} else {
    //没有权限
}
```

#### 回调方法
```java
MissPermissionHelper.with(this)
            .addPermission(Manifest.permission.SEND_SMS)//
            .addPermission(Manifest.permission.RECEIVE_SMS)//
            .addPermission(Manifest.permission.READ_SMS)//
            .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)//
            .addPermission(Manifest.permission.CAMERA)//
            .addPermission(Manifest.permission.READ_CONTACTS)//
            .addPermission(Manifest.permission.WRITE_CALENDAR)//
            .addPermission(Manifest.permission.READ_CALL_LOG)//
            .addPermission(Manifest.permission.READ_CONTACTS)//
            .addPermission(Manifest.permission.RECORD_AUDIO)//
            .addPermission(Manifest.permission.BODY_SENSORS)//
            .addPermission(Manifest.permission.SEND_SMS)//
            .addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)//
            .msg("为了您正常使用应用,需要以下权限")
            .title("亲爱的用户")
            .showPrompt(true)//展示提示
            .styleResId(R.style.MissPermissionHelperDefaultNormalStyle)//设置样式
            .checkPermission(new PermissionListener() {
                @Override
                public void onSuccess(PermissionRequest request) {

                }

                @Override
                public void onFailure(PermissionRequest request) {

                }
            });
```

#### 说明

不需要添加回调方法，直接使用即可，开箱即用。

![](https://github.com/bugyun/MissPermission/blob/develop/art/Screenshot_20190805-165801.jpg?raw=true)


#### 样式
字段说明
```xml
<resources>
    <attr name="MissPermissionHelperTitleColor" format="color" />           <!--标题文字颜色-->
    <attr name="MissPermissionHelperMsgColor" format="color" />             <!--描述文字颜色-->
    <attr name="MissPermissionHelperItemTextColor" format="color" />        <!--权限文字颜色-->
    <attr name="MissPermissionHelperButtonTextColor" format="color" />      <!--按钮文字颜色-->
    <attr name="MissPermissionHelperButtonBackground" format="reference" /> <!--按钮背景-->
    <attr name="MissPermissionHelperBackground" format="reference" />       <!--对话框背景-->
    <attr name="MissPermissionHelperBgFilterColor" format="color" />        <!--背景过滤色-->
    <attr name="MissPermissionHelperIconFilterColor" format="color" />      <!--图标颜色-->
</resources>
```

默认样式
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="MissPermissionHelperDefaultNormalStyle">
        <item name="MissPermissionHelperTitleColor">@android:color/black</item>
        <item name="MissPermissionHelperMsgColor">@android:color/black</item>
        <item name="MissPermissionHelperItemTextColor">@android:color/black</item>
        <item name="MissPermissionHelperButtonBackground">@drawable/miss_permission_shape_btn_next</item>
        <item name="MissPermissionHelperBackground">@drawable/miss_permission_shape_bg_white</item>
        <item name="MissPermissionHelperButtonTextColor">@android:color/white</item>
        <item name="MissPermissionHelperIconFilterColor">@android:color/black</item>
    </style>
</resources>
```

自定义样式
```
<style name="MyStyle" parent="MissPermissionHelperDefaultNormalStyle">
        <item name="MissPermissionHelperTitleColor">@android:color/black</item>
        <item name="MissPermissionHelperMsgColor">@android:color/black</item>
        <item name="MissPermissionHelperItemTextColor">@android:color/black</item>
        <item name="MissPermissionHelperButtonBackground">@drawable/miss_permission_shape_btn_next</item>
        <item name="MissPermissionHelperBackground">@drawable/miss_permission_shape_bg_white</item>
        <item name="MissPermissionHelperButtonTextColor">@android:color/white</item>
        <item name="MissPermissionHelperIconFilterColor">@android:color/black</item>
</style>
```
---

## MissPermission
可以单独使用，如果配合 MissPermissionHelper 体验更佳~

### 配置
```xml
implementation 'vip.ruoyun.permission:miss-core:1.0.1'
```

### 准备工作
在 AndroidManifest.xml 文件中添加权限
```xml
<manifest>
    ...
    //添加自己要使用的权限，如果不添加，那么请求这个权限会一直不成功
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
    <uses-permission android:name="android.permission.SEND_SMS" />

    ...
</manifest>
```

### 使用方法

#### PermissionRequest 方法说明

```java
request.getPermissionList();//此请求的所有权限
request.getDeniedPermissionList();//拒绝的权限
request.getAgreePermissionList();//同意的权限
request.getContext();//上下文
request.isAlwaysDenied();//是否总是拒绝
request.isOver23();//sdk 是否超过 23 (6.0)
request.requestPermissionsAgain();//再次请求权限
```

#### 开始

```java
MissPermission.with(context)//
                .setRequestCode(9898)//默认值 9898
                .addPermission(Manifest.permission.CAMERA)//添加请求权限
                .addPermission(Manifest.permission.SEND_SMS)//添加请求权限
                .addPermission(Manifest.permission.RECEIVE_SMS)//添加请求权限
                .checkPermission(new PermissionRequest.PermissionListener() {
                    /**
                       为了适应每种机型弹框提示需要。
                       request.getAgreePermissionList();//同意的权限
                       request.getDeniedPermissionList();//拒绝的权限
                    */
                    @Override
                    public int onChecked(PermissionRequest request) {
                        //3 种返回方式，
                        //MissPermission.NEXT_STEP  直接下一步，不用等待
                        //MissPermission.STOP_STEP  直接停止，不执行下一步
                        //MissPermission.WAIT_STEP  可以做弹出框等操作，然后在按钮点击事件的时候，执行 request.next(); 进行后续操作
                        return MissPermission.WAIT_STEP;
                    }

                    /**
                       拒绝权限 可以进行提示操作
                       request.isOver23: SDK是否是大约 M
                       request.deniedPermissions: 拒绝的权限
                       request.alwaysDenied: 是否总是拒绝
                    */
                    @Override
                    public void onDenied(PermissionRequest request) {
                        if (request.isAlwaysDenied()) {//是否总是拒绝
                        }

                    }

                    @Override
                    public void onSuccess(PermissionRequest request) {
                        Log.i("zyh", "成功");
                    }

                    @Override
                    public void onFailure(PermissionException exception) {
                        Log.i("zyh", exception.getMessage());
                    }
                });
```

需要在 使用方法的 Activity 中重写如下方法:
```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    MissPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
}
```


