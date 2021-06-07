package org.example.entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Specification {
    private int id;
    private int doorsCount;
    private int seatsCount;
    private double engineWorkingVolume;
    private String enginePos;
    private BodyType bodyType;
    private EngineType engineType;
}
