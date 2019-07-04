package vip.ruoyun.permission.helper.core;

import vip.ruoyun.permission.helper.manufacturer.IRomStrategy;

public class MissHelperConfiguration {

    private BaseAction action;

    private IRomStrategy romStrategy;

    public IRomStrategy getRomStrategy() {
        return romStrategy;
    }

    public BaseAction getAction() {
        return action;
    }
}
