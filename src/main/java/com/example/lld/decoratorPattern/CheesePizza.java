package com.example.lld.decoratorPattern;

import org.springframework.stereotype.Service;

@Service
public class CheesePizza extends PizzaDecorator {
    public CheesePizza(PizzaInterface decoratedPizza){
        super(decoratedPizza);
    }

    @Override
    public String createBase(String base){
        return decoratedPizza.createBase(base) + " Cheese";
    }

    @Override 
    public double getCost(){
        return decoratedPizza.getCost()+10;
    }

    @Override
    public String getDescription(){
        return decoratedPizza.getDescription() + " Add cheese also in pizza";
    }
}
