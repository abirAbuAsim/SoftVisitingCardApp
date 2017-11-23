package com.example.android.softvisitingcardapp.models;

/**
 * Created by Belal on 14/04/17.
 */

public class Feature {
    private int id;
    private String name;
    private String ram;
    private String rom;
    private String processor;
    private String os;
    private String frontCamera;
    private String backCamera;
    private String battery;
    private String cpu;
    private String gpu;
    private String sim;
    private String screen;
    private String warranty;

    public Feature(int id, String name, String ram, String rom, String processor, String os, String frontCamera, String backCamera, String battery, String cpu, String gpu, String sim, String screen, String warranty) {
        this.id = id;
        this.name = name;
        this.ram = ram;
        this.rom = rom;
        this.processor = processor;
        this.os = os;
        this.frontCamera = frontCamera;
        this.backCamera = backCamera;
        this.battery = battery;
        this.cpu = cpu;
        this.gpu = gpu;
        this.sim = sim;
        this.screen = screen;
        this.warranty = warranty;
    }

    public Feature(String name, String ram, String rom, String processor, String os, String frontCamera, String backCamera, String battery, String cpu, String gpu, String sim, String screen, String warranty) {
        this.name = name;
        this.ram = ram;
        this.rom = rom;
        this.processor = processor;
        this.os = os;
        this.frontCamera = frontCamera;
        this.backCamera = backCamera;
        this.battery = battery;
        this.cpu = cpu;
        this.gpu = gpu;
        this.sim = sim;
        this.screen = screen;
        this.warranty = warranty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRam() {
        return ram;
    }

    public String getRom() {
        return rom;
    }

    public String getProcessor() {
        return processor;
    }

    public String getOs() {
        return os;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public String getBackCamera() {
        return backCamera;
    }

    public String getBattery() {
        return battery;
    }

    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public String getSim() {
        return sim;
    }

    public String getScreen() {
        return screen;
    }

    public String getWarranty() {
        return warranty;
    }
}
