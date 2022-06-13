package com.bridgelabz.user_registration.service;

import com.bridgelabz.user_registration.dto.LoginDTO;
import com.bridgelabz.user_registration.dto.UserDTO;
import com.bridgelabz.user_registration.entity.User;


import javax.validation.Valid;
import java.util.List;

public interface IUserService {

    User registerUserToDB(UserDTO dto);

    User getUserByID(String token);

    List<User> retrieveUsers();

    User updateById(String token, @Valid UserDTO dto);

    User deleteById(String token);

    User getUserByEmail(String email);

    User loginUser(LoginDTO dto);

    User changePassword(UserDTO dto);

    User getByIdAPI(Integer userId);

}