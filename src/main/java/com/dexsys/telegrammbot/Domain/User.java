package com.dexsys.telegrammbot.Domain;


import com.dexsys.telegrammbot.Services.UserStatus;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class User {
    private long chatId;
    private String userName;
    private String birthDate;
    private String phone;
    private UserStatus userStatus;

}
