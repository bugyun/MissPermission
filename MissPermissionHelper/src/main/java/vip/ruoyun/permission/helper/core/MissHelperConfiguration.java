package vip.ruoyun.permission.helper.core;

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
