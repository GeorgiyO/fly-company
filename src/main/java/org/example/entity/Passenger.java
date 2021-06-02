package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Passenger {
    private int id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String docType;
    private String docNumber;
}
