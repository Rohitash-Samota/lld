package com.example.lld.solid.is;

import java.util.Arrays;
import java.util.List;

public class VegetarianMenu implements IVegetarianMenu {
    public List<String> getVegItems() {
        return Arrays.asList("Paneer Tikkka");
    }
}
