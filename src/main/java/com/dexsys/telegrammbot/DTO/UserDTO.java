package com.dexsys.telegrammbot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
