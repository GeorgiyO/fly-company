package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class User {
    private int id;
    private String login;
    private String password;
    private Role role;
}
