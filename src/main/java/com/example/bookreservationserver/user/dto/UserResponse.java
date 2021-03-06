package com.example.bookreservationserver.user.dto;

import com.example.bookreservationserver.user.domain.aggregate.User;
import com.example.bookreservationserver.user.domain.aggregate.UserType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserResponse {

    private Long id;

    private String name;

    private String phoneNum;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType user_type;

    public UserResponse(User user){
        this.id = user.getUser_id();
        this.name = user.getName();
        this.phoneNum = user.getPhoneNum();
        this.email = user.getEmail();
        this.password = user.getPassword();
        user_type = user.getUserType();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhoneNum() { return phoneNum; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public UserType getUser_type() { return user_type; }
}
