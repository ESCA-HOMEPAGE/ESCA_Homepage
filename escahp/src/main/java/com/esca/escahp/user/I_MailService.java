package com.esca.escahp.user;

import com.esca.escahp.user.code.UserCode;
import com.esca.escahp.user.entity.Mail;

import javax.mail.MessagingException;

public interface I_MailService {

    Mail createMail(UserCode code, String message, String toAddress);

    void sendMail(Mail mail) throws MessagingException;

}
