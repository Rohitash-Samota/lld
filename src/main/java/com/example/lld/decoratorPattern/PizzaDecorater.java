package com.example.lld.decoratorPattern;

import org.springframework.stereotype.Component;

@Component
abstract class PizzaDecorator implements PizzaInterface {
    protected PizzaInterface decoratedPizza;

    public PizzaDecorator(PizzaInterface pizzaInterface){
        this.decoratedPizza = pizzaInterface;
    }

    @Override
    public String createBase(String base){
        return decoratedPizza.createBase(base);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost();
    }
}
