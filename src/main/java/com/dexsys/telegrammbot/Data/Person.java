package com.dexsys.telegrammbot.Data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Person {
    private String firstName;
    private String lastName;
    private int age;
}
