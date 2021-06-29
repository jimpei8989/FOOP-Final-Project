package model.utils;

public class CoolDown {
    private final int interval;
    private int current = 0;
    private int stepSize = 1;

    public CoolDown(int interval) {
        this.interval = interval;
    }

    public CoolDown(int stepSize, int interval) {
        this.stepSize = stepSize;
        this.interval = interval;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public int getCurrent() {
        return this.current;
    }

    public int getInterval() {
        return this.interval;
    }

    public boolean available() {
        return this.current <= 0;
    }

    public int getStepSize() {
        return this.stepSize;
    }

    public void reset() {
        this.current = this.interval;
    }

    public void reset(int specialInterval) {
        this.current = specialInterval;
    }

    public void update() {
        this.current -= this.stepSize;
    }

    public void update(int specialUpdate) {
        this.current -= specialUpdate;
    }

    public double getPercent() {
        return (this.interval - this.current) / (double) this.interval;
    }

    @Override
    public String toString() {
        return String.format("%d / %d", current, interval);
    }
}
