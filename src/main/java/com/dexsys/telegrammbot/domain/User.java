package com.dexsys.telegrammbot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Audited
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Column(name = "chatid")
    private long chatId;
    @Column(name = "username")
    private String userName;
    @Column(name = "birthdate")
    private String birthDate;
    @Column(name = "phone")
    private String phone;
    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

}
