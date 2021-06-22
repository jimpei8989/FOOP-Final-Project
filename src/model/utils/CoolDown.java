package model.utils;

public class CoolDown {
    private final int interval;
    private int current = 0;

    public CoolDown(int interval) {
        this.interval = interval;
    }

    public boolean available() {
        return this.current <= 0;
    }

    public void reset() {
        this.current = this.interval;
    }

    public void reset(int specialInterval) {
        this.current = specialInterval;
    }

    public void update() {
        this.current -= 1;
    }

    public void update(int specialUpdate) {
        this.current -= specialUpdate;
    }
}
