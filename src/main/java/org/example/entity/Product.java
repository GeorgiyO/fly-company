package org.example.entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Product {
    private int code;
    private boolean available;
    private Date availabilityDate;
    private double price;
    private Model model;
    private Country country;
    private Brand brand;
    private Specification specification;
}
