# MissPermission
android 权限库，超级简单好用！！
---
## MissPermission
可以单独使用，如果配合 PermissionHelper 体验更佳~

### 配置
```xml
implementation 'vip.ruoyun.permission:miss-core:1.0.0'
```

### 使用方法

```java
MissPermission.with(context)//
                .addPermission(Manifest.permission.CAMERA)//添加请求权限
                .addPermission(Manifest.permission.SEND_SMS)//添加请求权限
                .addPermission(Manifest.permission.RECEIVE_SMS)//添加请求权限
                .checkPermission(new PermissionRequest.PermissionListener() {
                    /**
                       为了适应每种机型弹框提示需要。
                       agreeList: 同意权限
                       rejectList: 拒绝的权限
                    */
                    @Override
                    public int onChecked(List<String> agreePermissions, List<String> deniedPermissions, PermissionRequest request) {
                        //3 种返回方式，
                        //MissPermission.NEXT_STEP  直接下一步，不用等待
                        //MissPermission.STOP_STEP  直接停止，不执行下一步
                        //MissPermission.WAIT_STEP  可以做弹出框等操作，然后在按钮点击事件的时候，执行 request.next(); 进行后续操作
                        return MissPermission.WAIT_STEP;
                    }

                    /**
                       拒绝权限 可以进行提示操作
                       isOver23: SDK是否是大约 M
                       deniedPermissions: 拒绝的权限
                       alwaysDenied: 是否总是拒绝
                    */
                    @Override
                    public void onDenied(List<String> deniedPermissions, boolean alwaysDenied, PermissionRequest request) {

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

---

## PermissionHelper
简化操作帮助类，很少的代码就可以获取权限。

### 配置
```xml
implementation 'vip.ruoyun.permission:miss-helper:1.0.0'
```

### 使用
```java
PermissionHelper.doOpenCamera(this, new PermissionHelper.DoActionWapper() {
    @Override
    public void doHandle(Context context) {

    }
});
```



