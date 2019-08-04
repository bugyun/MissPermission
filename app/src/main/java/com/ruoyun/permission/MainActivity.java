package com.ruoyun.permission;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionAdapter;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;
import me.weyye.hipermission.PermissionView;
import vip.ruoyun.permission.core.MissPermission;
import vip.ruoyun.permission.core.PermissionException;
import vip.ruoyun.permission.core.PermissionRequest;
import vip.ruoyun.permission.helper.MissPermissionHelper;
import vip.ruoyun.permission.helper.check.SMSChecker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2000;
    private static final String TAG = "zyh";


    Button buttonCamera;
    Button buttonAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //测试
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonAll = findViewById(R.id.buttonAll);
        buttonCamera.setOnClickListener(this);
        buttonAll.setOnClickListener(this);
        String manufacturer = android.os.Build.MANUFACTURER;
        Log.e("zyh", "制造商" + manufacturer);

//        tesHelper();

//        InstanceID.getInstance(this).getId();
        //        testHiPermisson();

//        Intent intent = new Intent();
//        AvoidOnResultManager.startActivityForResult(this, intent, new AvoidOnResultManager.ActivityCallback() {
//            @Override
//            public void onActivityResult(int resultCode, Intent data) {
        //新界面
        //val intent = Intent()
        //intent.putExtra("text",text.text.toString())
        //setResult(Activity.RESULT_OK,intent)
        //finish();

//            }
//        });
//        String[] permissions = {};
//        AvoidOnResultManager.requestPermissions(this, permissions, new AvoidOnResultManager.PermissionsCallBack() {
//            @Override
//            public void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
//
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCamera:
                if (MissPermissionHelper.check(this, SMSChecker.NEED_PERMISSION)) {
                    Log.e("zyh", "有权限");
                } else {
                    Log.e("zyh", "没有权限");
                }


//                MissPermissionHelper.checkSms(this, new MissPermissionHelper.DoActionWrapper() {
//                    @Override
//                    public void onSuccess(Context context) {
//                        Log.e("zyh", "onSuccess");
//                    }
//
//                    @Override
//                    public void onFailure(Context context) {
//                        Log.e("zyh", "onFailure");
//                    }
//                });


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
                        .showprompt(true)
                        .checkPermission(new vip.ruoyun.permission.helper.PermissionRequest.PermissionListener() {
                            @Override
                            public void onSuccess(vip.ruoyun.permission.helper.PermissionRequest request) {

                            }

                            @Override
                            public void onFailure(vip.ruoyun.permission.helper.PermissionRequest request) {

                            }
                        });
//                testDPermission();

                break;
            case R.id.buttonAll:
//                MissPermissionHelper.checkMorePermissions(this, new MissPermissionHelper.DoActionWrapper() {
//                    @Override
//                    public void onSuccess(Context context) {
//                        Log.e("zyh", "onSuccess");
//                    }
//
//                    @Override
//                    public void onFailure(Context context) {
//                        Log.e("zyh", "onFailure");
//                    }
//                }, MissPermissionHelper.PermissionType.CALENDAR, MissPermissionHelper.PermissionType.CAMERA, MissPermissionHelper.PermissionType.SMS);
                break;
        }

    }

    private void testHiPermisson() {
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA));
        permissionItems.add(new PermissionItem(Manifest.permission.SEND_SMS));
        permissionItems.add(new PermissionItem(Manifest.permission.RECEIVE_SMS));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_SMS));
        permissionItems.add(new PermissionItem(Manifest.permission.READ_CONTACTS));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
        HiPermission.create(MainActivity.this).permissions(permissionItems).checkMutiPermission(new PermissionCallback() {
            @Override
            public void onClose() {
                Log.i(TAG, "用户关闭权限申请");

            }

            @Override
            public void onFinish() {
                Log.i(TAG, "所有权限申请完成");

            }

            @Override
            public void onDeny(String permission, int position) {
                Log.i(TAG, "onDeny");
            }

            @Override
            public void onGuarantee(String permission, int position) {
                Log.i(TAG, "onGuarantee");
            }
        });


    }


    private void tesHelper() {
//        MissPermissionHelper.checkCamera(this, new MissPermissionHelper.DoActionWrapper() {
//            @Override
//            public void onSuccess(Context context) {
//                Log.e("zyh", "onSuccess");
//            }
//
//            @Override
//            public void onFailure(Context context) {
//                Log.e("zyh", "onFailure");
//            }
//        });
//        MissPermissionHelper.checkCalendar();
//        MissPermissionHelper.checkCallLog();
//        MissPermissionHelper.checkContacts();
//        MissPermissionHelper.checkLocation();
//        MissPermissionHelper.checkMicrophone();
//        MissPermissionHelper.checkPhone();
//        MissPermissionHelper.checkSensors();
//        MissPermissionHelper.checkSms();
//        MissPermissionHelper.checkStorage();

        boolean isHasReadCalendarPermission = MissPermissionHelper.check(this, new String[]{Manifest.permission.READ_CALENDAR});
        if (isHasReadCalendarPermission) {
            //有权限
        } else {
            //没有权限
        }
    }

    Dialog mDialog;

    private void testDPermission() {
        MissPermission.with(MainActivity.this)//
//                .addPermission(Manifest.permission.CAMERA)//
                .addPermission(Manifest.permission.SEND_SMS)//
                .addPermission(Manifest.permission.RECEIVE_SMS)//
                .addPermission(Manifest.permission.READ_SMS)//
                .setRequestCode(1000)//默认值 9898
//                .addPermission(Manifest.permission.READ_CONTACTS)//
//                .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)//
                .checkPermission(new PermissionRequest.PermissionListener() {
                    @Override
                    public int onChecked(final PermissionRequest request) {
                        Log.i("zyh", "onChecked: rejectList:" + request.getDeniedPermissionList().size() + "agree:" + request.getAgreePermissionList().size());
                        PermissionView contentView = new PermissionView(MainActivity.this);
                        contentView.setGridViewColum(3);
                        contentView.setTitle("标题");
                        contentView.setMsg("内容");
                        //这里没有使用RecyclerView，可以少引入一个库
                        List<PermissionItem> permissionItems = new ArrayList<>();
                        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
                        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
                        permissionItems.add(new PermissionItem(Manifest.permission.READ_CONTACTS, "通讯录", R.drawable.permission_ic_contacts));
                        contentView.setGridViewAdapter(new PermissionAdapter(permissionItems));
                        //用户没有设置，使用默认绿色主题
                        int mStyleId = R.style.PermissionDefaultNormalStyle;
                        int mFilterColor = getResources().getColor(R.color.permissionColorGreen);

                        contentView.setStyleId(mStyleId);
                        contentView.setFilterColor(mFilterColor);
                        contentView.setBtnOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mDialog != null && mDialog.isShowing()) {
                                    mDialog.dismiss();
                                }
                                request.next();
                            }
                        });
                        mDialog = new Dialog(MainActivity.this);
                        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        mDialog.setContentView(contentView);

                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                dialog.dismiss();
//                                request.stop();
                            }
                        });
                        mDialog.show();
                        return MissPermission.WAIT_STEP;
                    }

                    @Override
                    public void onDenied(final PermissionRequest request) {


                        StringBuilder sBuilder = new StringBuilder();
                        for (String deniedPermission : request.getDeniedPermissionList()) {
                            if (deniedPermission.equals(Manifest.permission.WRITE_CONTACTS)) {
                                sBuilder.append("联系人");
                                sBuilder.append(",");
                            }
                            if (deniedPermission.equals(Manifest.permission.READ_SMS)) {
                                sBuilder.append("短信");
                                sBuilder.append(",");
                            }
                        }
                        if (sBuilder.length() > 0) {
                            sBuilder.deleteCharAt(sBuilder.length() - 1);
                        }
                        //Toast.makeText(context, "获取" + sBuilder.toString() + "权限失败", Toast.LENGTH_SHORT).show();
                        if (request.isAlwaysDenied()) {
                            DialogUtil.showPermissionManagerDialog(MainActivity.this, sBuilder.toString());
                        } else {
                            new AlertDialog.Builder(MainActivity.this).setTitle("温馨提示").setMessage("我们需要这些权限才能正常使用该功能").setNegativeButton("取消", null).setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.M)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    request.requestPermissionsAgain();
                                }
                            }).setCancelable(false).show();
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
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void test(String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {//如果小于23的话

            /**
             * PERMISSION_GRANTED: 已授权
             * PERMISSION_DENIED: 没有被授权
             * PERMISSION_DENIED_APP_OP: 没有被授权
             */
            if (PermissionChecker.checkSelfPermission(this, permission) == PermissionChecker.PERMISSION_GRANTED) {
                //已授权
            } else {
                //未授权
            }
            return;
        }
        if (TextUtils.isEmpty(permission)) {
            return;
        }

        // 检测是否有权限
        int permissionCheck = ContextCompat.checkSelfPermission(this, permission);


        /**
         * 如果应用不具有此权限：PackageManager.PERMISSION_DENIED
         * 如果应用具有此权限：PackageManager.PERMISSION_GRANTED
         */
        if (PackageManager.PERMISSION_GRANTED == permissionCheck) {//有此权限

        }


        if (shouldShowRequestPermissionRationale(permission)) {


        }


        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            //如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。如果设备规范禁止应用具有该权限，此方法也会返回 false。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
    //requestPermissions(android.app.Activity, String[], int)

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MissPermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //        switch (requestCode) {
        //            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
        //                // If request is cancelled, the result arrays are empty.
        //                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        //                    //用户点击了接受，可以进行相应处理
        //                    // permission was granted, yay! Do the
        //                    // contacts-related task you need to do.
        //
        //                } else {
        //                    //用户点击了拒绝，可以进行相应处理
        //                    // permission denied, boo! Disable the
        //                    // functionality that depends on this permission.
        //                }
        //                return;
        //            }
        //
        //            // other 'case' lines to check for other
        //            // permissions this app might request
        //        }
    }

}
