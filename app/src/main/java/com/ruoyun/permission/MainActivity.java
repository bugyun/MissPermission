package com.ruoyun.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.ruoyun.dpermission.DPermission;
import com.ruoyun.dpermission.PermissionRequest;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //测试
        //        test(Manifest.permission.WRITE_CALENDAR);

        //        testDPermission();

        String manufacturer = android.os.Build.MANUFACTURER;
        Log.e("zyh", "制造商" + manufacturer);

    }

    private void testDPermission() {
        DPermission.getInstance()//
                .with()//
                .setPermissionListener(new PermissionRequest.PermissionListener() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void rationale() {

                    }

                    @Override
                    public void granted() {

                    }

                    @Override
                    public void denied(List<String> deniedList) {

                    }
                }).addPermission("")//
                .addActivity(this)//
                .create()//
                .start();
    }


    public void test(String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {//如果小于23的话
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

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户点击了接受，可以进行相应处理
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    //用户点击了拒绝，可以进行相应处理
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
