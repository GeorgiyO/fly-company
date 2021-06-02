package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class ScheduleItem {
    private int id;
    private Date date;
    private double price;
    private Plane plane;
    private Address fromAddr;
    private Address toAddr;
}
