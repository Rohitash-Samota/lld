package com.example.lld.decoratorPattern;

public class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(PizzaInterface pizza){
        super(pizza);
    }

    @Override
    public String createBase(String base){
        return decoratedPizza.createBase(base) + "Pepperoni";
    }

    @Override
    public double getCost(){
        return decoratedPizza.getCost() + 12;
    }

    @Override 
    public String getDescription(){
        return decoratedPizza.getDescription() + "Pepperoni added";
    }
}
