package com.example.lld.solid.is;

import java.util.Arrays;
import java.util.List;

public class NonVegetarianMenu implements INonVegetarianMenu {

    @Override
    public List<String> getNonVegIteams() {
        return Arrays.asList("Non  veg");
    }

}
