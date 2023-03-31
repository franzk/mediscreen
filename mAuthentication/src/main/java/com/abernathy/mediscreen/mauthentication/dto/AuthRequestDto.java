package com.abernathy.mediscreen.mauthentication.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthRequestDto {

    private String username;
    private String password;

}
