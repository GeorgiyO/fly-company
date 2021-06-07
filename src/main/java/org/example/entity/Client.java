package org.example.entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Client {
    private String passportNumber;
    private String name;
    private String phone;
    private String address;
    private boolean delivery;
    private boolean credit;
    private PaymentType paymentType;
    private Product product;
}
