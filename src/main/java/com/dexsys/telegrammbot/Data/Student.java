package com.dexsys.telegrammbot.Data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@RequiredArgsConstructor
@ToString
public class Student {
    private final String firstName;
    private final String lastName;
    @Getter
    private final String course;
    private final int age;

}
