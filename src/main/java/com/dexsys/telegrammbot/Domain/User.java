package com.dexsys.telegrammbot.Domain;


import com.dexsys.telegrammbot.Services.UserStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Data
@Builder
@Component
@Scope("prototype")
public class User {
    private long chatId;
    private String userName;
    private String birthDate;
    private UserStatus userStatus;

}
