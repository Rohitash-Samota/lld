package com.example.lld.designPatterns.creational.abstractFactory;

public class Sedan implements Car{
    @Override
    public void assemble(){
        System.out.println("assemble car");
    }
}