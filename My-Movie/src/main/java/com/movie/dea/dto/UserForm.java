package com.movie.dea.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserForm {

    private Integer user_id;
    @NotBlank(message = "Username is required!")
    private String username;
    @NotBlank(message = "Password is required!")
    private String password;
    private String phone_number;
    private Integer age;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
