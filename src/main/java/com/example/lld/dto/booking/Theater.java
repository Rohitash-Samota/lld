package com.example.lld.dto.booking;

public class Theater {
    private String theaterId;
    private String name;
    private Address address;
    private Screen[] screens;

    public Theater(String name, Address address, Screen[] screens) {
        this.name = name;
        this.address = address;
        this.screens = screens;
        this.theaterId = createTheaterId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Screen[] getScreens() {
        return screens;
    }

    public void setScreens(Screen[] screens) {
        this.screens = screens;
    }

    public String createTheaterId() {
        return "TH-" + getName();
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }
}
