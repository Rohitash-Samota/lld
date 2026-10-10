
package com.example.lld.designPatterns.creational.abstractFactory;

class EuropeCarFactory implements CarFactory {
    @Override
    public Car createCar() { return new Hatchback(); }

    @Override
    public CarSpecification createSpecification()
    {
        return new EuropeSpecification();
    }
}