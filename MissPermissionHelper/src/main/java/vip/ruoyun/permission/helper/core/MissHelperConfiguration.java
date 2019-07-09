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

    public static class Builder {

        private MissHelperConfiguration missHelperConfiguration;

        public Builder() {
            missHelperConfiguration = new MissHelperConfiguration();
        }

        public Builder buildAction(BaseAction action) {
            missHelperConfiguration.action = action;
            return this;
        }

        public Builder addIRomStrategy(String name, Class<? extends IRomStrategy> clazz) {
            RomUtil.addRomStrategy(name, clazz);
            return this;
        }

        public MissHelperConfiguration build() {
            check();
            return missHelperConfiguration;
        }

        private void check() {
            if (missHelperConfiguration.action == null) {
                missHelperConfiguration.action = new DefaultAction();
            }
            missHelperConfiguration.romStrategy = RomUtil.get();
        }
    }


}
