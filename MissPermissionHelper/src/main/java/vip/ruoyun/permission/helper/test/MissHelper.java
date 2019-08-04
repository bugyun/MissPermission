package vip.ruoyun.permission.helper.test;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import vip.ruoyun.permission.core.MissPermission;
import vip.ruoyun.permission.core.PermissionException;
import vip.ruoyun.permission.core.PermissionRequest;

public class MissHelper {

    private MissHelper() {
    }

    //        ArrayList<Item> permissions = new ArrayList<>();
//        permissions.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_memory))
//        MissPermissionHelper.with(this)
//                .showprompt(true)//是否显示提示
//                .title("亲爱的上帝")
//                .msg("为了保护世界的和平，开启这些权限吧！\n你我一起拯救世界！")
//                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//图标的颜色
//                .style(R.style.PermissionBlueStyle)
//                .ischeck(true)//是否要进行后续的检查权限
//                .permission("权限", "权限名称", "图片")
//                .permission("权限", "权限名称", "图片")
//                .permissions(permissions)
//                .listener(new MissPermissionHelper.DoActionWrapper() {
//                    @Override
//                    public void onSuccess(Context context) {
//                        Log.e("zyh", "onSuccess");
//                    }
//
//                    @Override
//                    public void onFailure(Context context) {
//                        Log.e("zyh", "onFailure");
//                    }
//                })
//                .go();

//    private PermissionGroup permissionGroup;

    public static Builder with(Fragment fragment) {
        return new Builder(fragment.getActivity());
    }

    public static Builder with(Activity activity) {
        return new Builder(activity);
    }

    private static class Builder {
        private boolean showprompt;
        private String title;
        private String msg;
        private int mFilterColor = 0;
        private int mStyleResId = -1;
        private boolean ischeck;
        private MissPermission.Builder missPermissionBuilder;
        private List<PermissionItem> permissionItems = new ArrayList<>();
        private DoAction doAction;

        private IAction baseAction = new TDefaultAction();


        private Builder(Activity activity) {
            missPermissionBuilder = MissPermission.with(activity);
        }

        public Builder showprompt(boolean showprompt) {
            this.showprompt = showprompt;
            return this;
        }

        public Builder style(String title) {
            this.title = title;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder mFilterColor(int mFilterColor) {
            this.mFilterColor = mFilterColor;
            return this;
        }

        public Builder mStyleResId(int mStyleResId) {
            this.mStyleResId = mStyleResId;
            return this;
        }

        public Builder ischeck(boolean ischeck) {
            this.ischeck = ischeck;
            return this;
        }

        public Builder missPermission(boolean ischeck) {
            this.ischeck = ischeck;
            return this;
        }


        public Builder permission(PermissionItem permissionItem) {
            this.permissionItems.add(permissionItem);
            return this;
        }

        public Builder lisenter(DoAction doAction) {
            this.doAction = doAction;
            return this;
        }

        public void go() {
            for (PermissionItem permissionItem : permissionItems) {
                missPermissionBuilder.addPermission(permissionItem.Permission);
            }
            missPermissionBuilder.checkPermission(new PermissionRequest.PermissionListener() {
                @Override
                public int onChecked(PermissionRequest request) {
                    List<PermissionGroup> permissionGroups = new ArrayList<>();
                    permissionGroups.add(new PermissionGroup());
                    baseAction.checkedAction(request, permissionGroups);
                    return MissPermission.WAIT_STEP;
                }

                @Override
                public void onDenied(PermissionRequest request) {
                    baseAction.deniedAction(request);
                    doAction.onFailure(request.getContext());
                }

                @Override
                public void onSuccess(PermissionRequest request) {

                }

                @Override
                public void onFailure(PermissionException exception) {

                }
            });
        }


    }


    public interface DoAction {
        void onSuccess(Context context);

        void onFailure(Context context);
    }

    public abstract static class DoActionWrapper implements DoAction {

        @Override
        public void onFailure(Context context) {

        }
    }

}
