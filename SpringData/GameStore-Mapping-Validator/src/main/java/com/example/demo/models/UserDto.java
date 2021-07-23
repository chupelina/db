package com.example.demo.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

public class UserDto {
@Pattern(regexp = "\\w+@+.+", message = "Type valid Email(with @ and .)")
    private String email;
    @Length(min = 6, message = "Password should be 6 or more symbols long")
    @Pattern(regexp = "[A-Z]+[a-z]+[0-9]+", message = "Password must contains " +
            "1 digit, 1 Upper and 1 lower case")
    private String password;
    private String ConfirmPassword;
    private String fullName;


    public UserDto(String email, String password
            , String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.ConfirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public UserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
