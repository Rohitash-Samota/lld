package com.example.lld.decoratorPattern;

import org.springframework.stereotype.Service;

@Service("plainPizza")
public class PlainPizza implements PizzaInterface {
    @Override
    public String createBase(String base){
        return base;
    }

    @Override
    public double getCost(){
        return 100.00;
    }

    @Override
    public String getDescription(){
        return "Base Pizza Successfully";
    }
}
