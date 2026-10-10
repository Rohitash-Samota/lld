package com.example.lld.designPatterns.creational.abstractFactory;

// Concrete Factory for North America Cars
class NorthAmericaCarFactory implements CarFactory {
    @Override
    public Car createCar() { return new Sedan(); }

    @Override
    public CarSpecification createSpecification()
    {
        return new NorthAmericaSpecification();
    }
}