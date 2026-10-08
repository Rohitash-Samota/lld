package com.example.lld.solid.di;

public interface IVersionControl {
    void commit(String message);

    void push();

    void pull();
}