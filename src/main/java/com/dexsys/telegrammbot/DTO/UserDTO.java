package com.dexsys.telegrammbot.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String birthDay;
    private String chatId;
    private String firstName;
    private String id;
    private boolean male;
    private String middleName;
    private String phone;
    private String secondName;
}
