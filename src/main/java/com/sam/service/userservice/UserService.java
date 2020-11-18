package com.sam.service.userservice;

import com.sam.pojo.UserEntity;

import java.util.Map;

public interface UserService {
    public UserEntity LoginValid(String Email, String Password);
    public UserEntity Register(String email, String username, String password, double height, double weight, double age, double targetWeight, int targetDate, String sex);
    public boolean ResetPassword(String email,  String newpassword);
    public Map githublogin(String code);

}
