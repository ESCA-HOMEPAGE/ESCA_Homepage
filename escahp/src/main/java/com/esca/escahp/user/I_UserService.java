package com.esca.escahp.user;

import com.esca.escahp.user.code.UserCode;
import com.esca.escahp.user.entity.User;

import javax.mail.MessagingException;

public interface I_UserService {

    User checkUserId(String userId);

    User addUser(User auth) throws MessagingException;

    void sendEmail(String email, UserCode code, String message) throws MessagingException;

    void resetPassword(String userId, String oldPassword, String password);

    void resetPassword(String userId) throws MessagingException;

    String randomPassword();

    void deleteUser(Long id);

    String findUserId(String name, String email);

    User findUserByLoginInfo(String userId, String password);

    User validateUser(Long id);
}
