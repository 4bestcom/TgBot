package com.dexsys.telegrammbot.Domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Builder
@Component
@Scope("prototype")
public class User {
    private long chatId;
    private String userName;
    @Setter
    private String birthDate;

}
