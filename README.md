# MissPermission
android 权限库，超级简单好用！！
---
注：原 MissPermissionHelper 不在维护，但是可以继续使用，可以升级到 pro 版本

## MissPermission pro版
简化操作帮助类，很少的代码就可以获取权限。

不混淆 30K 代码体积！👍~

混淆之后 18K 代码体积！👍~

![](https://github.com/bugyun/MissPermission/blob/e39e20e4a748c1d8e0a333096287d95ddad1ab80/art/0dcc4-xa8pr1.gif?raw=true)

### 下载apk

![](https://github.com/bugyun/MissPermission/blob/290070eb42ae59c46b15cb46c7d8f0f5e10b4315/art/down.png?raw=true)

### 配置
```xml
implementation 'vip.ruoyun.permission:miss-pro:1.0.1'
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

### 使用

#### PermissionRequest 方法说明

```java
request.getPermissionList();//此请求的所有权限
request.getDeniedPermissionList();//拒绝的权限
request.getAgreePermissionList();//同意的权限
request.getContext();//上下文
request.isAlwaysDenied();//是否总是拒绝
request.isOver23();//sdk 是否超过 23 (6.0)
request.nextStep();//下一步请求权限。实质是请求权限
request.requestPermissionsAgain();//再次请求权限
```

#### 单纯检查是否有权限
```java
boolean isHasReadCalendarPermission = MissPermission.check(this, new String[]{Manifest.permission.READ_CALENDAR});
if (isHasReadCalendarPermission) {
    //有权限
} else {
    //没有权限
}
```

真实的检测权限，实际调用该权限对应的功能来确定是否可以使用该权限。
```java
boolean isHasPermission = MissPermission.realCheck(this, Manifest.permission.READ_SMS);
if (isHasPermission) {
    //有权限
} else {
    //没有权限
}
```



#### 回调方法
```java
MissPermission.with(this)
            .permission(Manifest.permission.SEND_SMS)//
            .permission(Manifest.permission.RECEIVE_SMS)//
            .permission(Manifest.permission.READ_SMS)//
            .permission(Manifest.permission.ACCESS_FINE_LOCATION)//
            .permission(Manifest.permission.CAMERA)//
            .permission(Manifest.permission.READ_CONTACTS)//
            .permission(Manifest.permission.WRITE_CALENDAR)//
            .permission(Manifest.permission.READ_CALL_LOG)//
            .permission(Manifest.permission.READ_CONTACTS)//
            .permission(Manifest.permission.RECORD_AUDIO)//
            .permission(Manifest.permission.BODY_SENSORS)//
            .permission(Manifest.permission.SEND_SMS)//
            .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)//
            .prompt(true)//展示提示
            .msg("为了您正常使用应用,需要以下权限")
            .title("亲爱的用户")
            .style(R.style.MissPermissionDefaultNormalStyle)//设置样式
            .check(new PermissionListener() {
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

![](https://github.com/bugyun/MissPermission/blob/develop/art/Screenshot_20190816-233942.jpg?raw=true)
![](https://github.com/bugyun/MissPermission/blob/develop/art/Screenshot_20190816-233958.jpg?raw=true)

#### 样式
字段说明
```xml
<resources>
    <attr name="MissPermissionTitleColor" format="color" />           <!--标题文字颜色-->
    <attr name="MissPermissionMsgColor" format="color" />             <!--描述文字颜色-->
    <attr name="MissPermissionItemTextColor" format="color" />        <!--权限文字颜色-->
    <attr name="MissPermissionButtonTextColor" format="color" />      <!--按钮文字颜色-->
    <attr name="MissPermissionButtonBackground" format="reference" /> <!--按钮背景-->
    <attr name="MissPermissionBackground" format="reference" />       <!--对话框背景-->
    <attr name="MissPermissionBgFilterColor" format="color" />        <!--背景过滤色-->
    <attr name="MissPermissionIconFilterColor" format="color" />      <!--图标颜色-->
</resources>
```

默认样式
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="MissPermissionDefaultNormalStyle">
        <item name="MissPermissionTitleColor">@android:color/black</item>
        <item name="MissPermissionMsgColor">@android:color/black</item>
        <item name="MissPermissionItemTextColor">@android:color/black</item>
        <item name="MissPermissionButtonBackground">@drawable/miss_permission_shape_btn_next</item>
        <item name="MissPermissionBackground">@drawable/miss_permission_shape_bg_white</item>
        <item name="MissPermissionButtonTextColor">@android:color/white</item>
        <item name="MissPermissionIconFilterColor">@android:color/black</item>
    </style>
</resources>
```

自定义样式, 继承 parent="MissPermissionDefaultNormalStyle"
```xml
<style name="MyStyle" parent="MissPermissionDefaultNormalStyle">
        <item name="MissPermissionTitleColor">@android:color/black</item>
        <item name="MissPermissionMsgColor">@android:color/black</item>
        <item name="MissPermissionItemTextColor">@android:color/black</item>
        <item name="MissPermissionButtonBackground">@drawable/miss_permission_shape_btn_next</item>
        <item name="MissPermissionBackground">@drawable/miss_permission_shape_bg_white</item>
        <item name="MissPermissionButtonTextColor">@android:color/white</item>
        <item name="MissPermissionIconFilterColor">@android:color/black</item>
</style>
```

## 自定义弹出效果

DefaultAction 为默认的弹框操作,如果你想监听打开权限界面之后,再回到界面的 onActivityResult ,那么只要重写这个方法即可.
代码如下

```java
MissPermission.with(this)
    .action(new DefaultAction() {//添加显示样式
        @Override
        public void onActivityResult(int resultCode, Intent data) {

        }
    })
    ...
```

如果你不想使用本库的弹出效果,那么你可以自定义 IAction,本库使用了 https://github.com/bugyun/AvoidOnResultHelper 来简化操作.
```java
public interface IAction extends AvoidOnResultHelper.ActivityCallback {

    /**
     * 检测权限后,触发的事件
     * @param request 权限请求
     * @param permissionGroups 需要请求的权限集合
     */
    void checkedAction(PermissionRequest request, Set<PermissionGroup> permissionGroups);

    /**
     * 拒绝触发的事件
     * @param request 权限请求
     */
    void deniedAction(PermissionRequest request);
}
```


## 版本变化

- 1.0.0 : 优化代码

---

## MissPermission
MissPermission 是精简版本。只有请求权限的简单功能。

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


