package com.example.smartstudytool;

public class Config {
    private static Config instance;
    private int workDuration;
    private int breakDuration;

    private Config() {
        workDuration = 25;
        breakDuration = 5;
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public int getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(int workDuration) {
        this.workDuration = workDuration;
    }

    public int getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }
}
