package com.ruoyun.dpermission;

import java.util.List;

/**
 * Created by fanpu on 2017/8/7.
 */

public class PermissionException extends Exception {

    public static final int ERROR_TYPE = 0;
    public static final int DENIED_TYPE = 1;

    private int errorType = ERROR_TYPE;
    private List<String> deniedList;

    public PermissionException(String message) {
        super(message);
        this.errorType = ERROR_TYPE;
    }


    public PermissionException(List<String> deniedList) {
        super("权限被拒绝");
        this.errorType = DENIED_TYPE;
        this.deniedList = deniedList;

    }

    public int getErrorType() {
        return errorType;
    }

    public List<String> getDeniedList() {
        return deniedList;
    }
}
