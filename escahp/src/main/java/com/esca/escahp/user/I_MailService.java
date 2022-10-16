package com.esca.escahp.user;

import com.esca.escahp.user.code.UserCode;
import com.esca.escahp.user.entity.Mail;

public interface I_MailService {

    public Mail createMail(UserCode code, String message, String toAddress);

    public void sendMail(Mail mail);

}
