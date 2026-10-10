package com.example.lld.designPatterns.creational.abstractFactory;

public interface CarFactory {
    Car createCar();
    CarSpecification createSpecification();
}
