package com.sam.dao.User;

import com.sam.pojo.UserEntity;

public interface UserDao {
    public UserEntity insert(String email, String username, String password, double height, double weight, double age, double targetWeight, int targetDate, String sex);
    public UserEntity queryByEmailandPassword(String Email, String Password);
    public boolean UpdateUserPassword(String Email,String NewPassword);
    public UserEntity queryByEmail(String Email);

}
