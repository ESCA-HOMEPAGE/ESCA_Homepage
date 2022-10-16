package com.esca.escahp.user;

import com.esca.escahp.user.code.UserCode;
import com.esca.escahp.user.entity.User;

public interface I_UserService {

    User checkUserId(String userId);

    User addUser(User auth);

    void sendEmail(String email, UserCode code, String message);

    void resetPassword(String userId, String oldPassword, String password);

    void resetPassword(String userId);

    String randomPassword();

    void deleteUser(Long id);

    String findUserId(String name, String email);

    User findUserByLoginInfo(String userId, String password);

    User validateUser(Long id);
}
