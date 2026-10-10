package com.example.lld.designPatterns.creational.abstractFactory;

class EuropeSpecification implements CarSpecification {
    @Override
    public void display() {
        System.out.println("Europe Car Specification: Fuel efficiency and emissions compliant with EU standards.");
    }
}