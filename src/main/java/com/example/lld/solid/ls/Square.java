package com.example.lld.solid.ls;

public class Square extends Rectangle {
    public Square(int size) {
        super(size, size);
    }

    @Override
    public void setHeight(int w) {
        width = height = w;
    }

    public void setWidth(int h) {
        width = height = h;
    }
}
