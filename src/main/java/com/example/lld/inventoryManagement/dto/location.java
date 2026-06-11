package com.example.lld.inventoryManagement.dto;

public class location {
    private Long pincode;
    private String area;
    private String city;

    public location(Long pincode, String area, String city) {
        this.pincode = pincode;
        this.area = area;
        this.city = city;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
