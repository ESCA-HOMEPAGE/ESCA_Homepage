package com.esca.escahp.user;

import com.esca.escahp.exception.EscaException;
import com.esca.escahp.user.code.UserCode;
import com.esca.escahp.user.entity.Mail;
import com.esca.escahp.user.entity.User;
import com.esca.escahp.user.exception.MailExceptions;
import com.esca.escahp.user.exception.SignUpExceptions;
import com.esca.escahp.user.exception.UserExceptions;
import com.esca.escahp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Objects;
import java.util.Random;

@Service
public class UserService implements I_UserService {

    private final UserRepository userRepository;
    private final MailService mailService;

    public UserService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public User checkUserId(String userId) {
        User user = userRepository.findByUserId(userId);

        return user;
    }

    @Transactional
    @Override
    public User addUser(User auth) {
        User user = checkUserId(auth.getUserId());
        if (user != null && user.getRank() != 4) throw new EscaException(SignUpExceptions.ALREADY_SIGNED_UP);

        User nickname = userRepository.findByNicknameAndGeneration(
                auth.getNickname(),
                auth.getGeneration()
        );

        if (nickname != null) throw new EscaException(SignUpExceptions.ALREADY_EXIST_NICKNAME);

        if (user != null)
            auth.setId(user.getId());
        User result = userRepository.save(auth);

        sendEmail(auth.getEmail(), UserCode.VALIDATE, String.valueOf(result.getId()));

        return result;
    }

    @Override
    public void sendEmail(String email, UserCode code, String message) {
        Mail mail = mailService.createMail(code, message, email);
        try {
            mailService.sendMail(mail);
        } catch (MessagingException e) {
            throw new EscaException(MailExceptions.CANNOT_SEND_MAIL);
        }
    }

    @Transactional
    @Override
    public void resetPassword(String userId, String oldPassword, String newPassword) {
        User user = userRepository.findByUserId(userId);
        if (user == null || user.getRank() == 4) throw new EscaException(SignUpExceptions.NOT_FOUND_ID);
        if (!Objects.equals(user.getPassword(), oldPassword)) throw new EscaException(SignUpExceptions.NOT_FOUND_PASSWORD);

        user.updatePassword(newPassword);
    }

    @Transactional
    @Override
    public void resetPassword(String userId) {
        User user = userRepository.findByUserId(userId);
        String newPassword = randomPassword();
        user.updatePassword(newPassword);
        userRepository.save(user);

        sendEmail(user.getEmail(), UserCode.RESET_PASSWORD, newPassword);
    }

    @Override
    public String randomPassword() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EscaException(SignUpExceptions.NOT_EXIST_USER));

        userRepository.delete(user);
    }

    @Override
    public String findUserId(String name, String email) {
        User user = userRepository.findByNameAndEmail(name, email);
        if (user == null) throw new EscaException(SignUpExceptions.NOT_EXIST_USER);

        return user.getUserId();
    }

    @Override
    public User findUserByLoginInfo(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        if (user == null || !user.getPassword().equals(password))
            throw new EscaException(SignUpExceptions.NOT_FOUND_DATA);
        return user;
    }

    public User validateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EscaException(SignUpExceptions.NOT_EXIST_USER));

        if(user.getRank() == 4){
            user.updateRank(2);
        } else {
            throw new EscaException(UserExceptions.ALREADY_RANK_UP_USER);
        }

        userRepository.save(user);

        return user;
    }
}
