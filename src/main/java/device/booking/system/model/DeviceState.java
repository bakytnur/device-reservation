package device.booking.system.model;

public enum DeviceState {
    YES(true),
    NO(false);

    public static DeviceState getStateByValue(boolean state) {
        return state ? YES : NO;
    }

    public boolean getState() {
        return state;
    }

    private final boolean state;

    DeviceState(boolean state) {
        this.state = state;
    }

}
