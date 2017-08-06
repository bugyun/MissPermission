# Permission
android 权限库


1.检测 check 权限
2.回调方法，回到参数包括permissionReqest(next/stop) ,同意的权限 ，拒绝的权限，是否总是拒绝，此方法是为了适应每种机型弹框提示需要。
3.如果同意,next方法，会继续执行下面的方法。如果不同意，那么就执行stop方法。如果什么都不执行
