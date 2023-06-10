package com.techelevator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String slot;
    private String description;
    private double price;
    private String category;
    private int quantity;
}
