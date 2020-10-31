package com.dexsys.telegrammbot.Domain;


import com.dexsys.telegrammbot.Services.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long chatId;
    private String userName;
    private String birthDate;
    private String phone;
    private UserStatus userStatus;

}
