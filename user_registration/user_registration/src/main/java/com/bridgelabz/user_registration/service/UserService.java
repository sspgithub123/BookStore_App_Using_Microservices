package com.bridgelabz.user_registration.service;

import com.bridgelabz.user_registration.dto.LoginDTO;
import com.bridgelabz.user_registration.dto.UserDTO;
import com.bridgelabz.user_registration.entity.User;
import com.bridgelabz.user_registration.exception.PasswordException;
import com.bridgelabz.user_registration.exception.UserException;
import com.bridgelabz.user_registration.repository.UserRepository;
import com.bridgelabz.user_registration.util.EmailSenderService;
import com.bridgelabz.user_registration.util.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repo;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private EmailSenderService sender;

    //taking user from controller and saving into database
    @Override
    public User registerUserToDB(UserDTO dto) {
        User user = new User(dto);
        repo.save(user);
        String token = tokenUtil.createToken(user.getUserId());
        sender.sendEmail(user.getEmail(), "User successfully registered", "for User : \n" + user +
                "\n click on following link "
                + "to retrieve data : \n http://localhost:8086/user/findbyid/" + token );
        return user;
    }

    //to log in into book store app
    @Override
    public User loginUser(LoginDTO dto) {
        Optional<User> user = repo.findByEmail(dto.getEmail());
        if(user.get().equals(null)) {
            throw new UserException("There are no users with given id");
        }
        if(!dto.getPassword().equals(user.get().getPassword())) {
            throw new PasswordException("Invalid password");
        }
        sender.sendEmail(user.get().getEmail(), "User successfully login", "someone just logged in,in your account");
        return user.get();
    }

    //to log in into book store app
    @Override
    public User changePassword(UserDTO dto) {
        Optional<User> user = repo.findByEmail(dto.getEmail());
        if(user.equals(null)) {
            throw new UserException("There are no users with given id");
        }
        user.get().setPassword(dto.getPassword());
        sender.sendEmail(user.get().getEmail(), "password successfully changed", "Your Password Has Changed");
        return user.get();
    }

    //to retrieve User using UserID
    @Override
    public User getUserByID(String token) {
        Integer userId = tokenUtil.decodeToken(token);
        Optional<User> user = repo.findById(userId);
        if(user.isEmpty()) {
            throw new UserException("There are no users with given id");
        }
        sender.sendEmail(user.get().getEmail(), "User successfully retrieved", "for User : \n" + user +
                "\n click on following link "
                + "to retrieve data : \n http://USER-CLIENT/user/findbyid/" + token );
        return user.get();
    }

    //to retrieve user using custom .findByEmail query
    @Override
    public User getUserByEmail(String email) {
        User user = repo.findByMail(email);
        if(user.equals(null)) {
            throw new UserException("There are no users with given id");
        }
        sender.sendEmail(user.getEmail(), "User successfully retrieved", "for User : \n" + user +
                "\n click on following link "
                + "to retrieve data : \n http://USER-CLIENT/user/findbyemail/" + user.getEmail() );
        return user;
    }

    @Override
    public List<User> retrieveUsers() {
        List<User> list = repo.findAll();
        if(list.isEmpty()) {
            throw new UserException("There are no users added");
        }
        else {
            return list;
        }
    }

    @Override
    public User updateById(String token, UserDTO dto) {
        Integer userId = tokenUtil.decodeToken(token);
        Optional<User> user = repo.findById(userId);
        if(user.isPresent()) {
            User newUser = new User(userId,dto);
            repo.save(newUser);
            sender.sendEmail(user.get().getEmail(), "User successfully retrieved", "for User : \n" + user +
                    "\n click on following link "
                    + "to retrieve data : \n http://USER-CLIENT/user/findbyid/" + token );
            return newUser;
        }
        else {
            throw new UserException("Employee not found");
        }
    }

    @Override
    public User deleteById(String token) {
        Integer userId =tokenUtil.decodeToken(token);
        Optional<User> user = repo.findById(userId);
        if(user.isEmpty()) {
            throw new UserException("Invalid token..please input valid token");
        }
        sender.sendEmail(user.get().getEmail(), "User successfully deleted", "User : \n" + user.get());
        repo.deleteById(userId);
        return user.get();

    }

    //-------------------------------services for Rest Template API-----------------------------------//
    @Override
    public User getByIdAPI(Integer userId) {
        Optional<User> user = repo.findById(userId);
        if(user.isEmpty()) {
            throw new UserException("There are no users with given id");
        }
        return user.get();
    }
}
