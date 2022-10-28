package com.esca.escahp.user;

import com.esca.escahp.user.code.UserCode;
import com.esca.escahp.user.entity.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MailService implements I_MailService {

    private final JavaMailSender mailSender;

    private static final String VALIDATE_TITLE = "ESCA 홈페이지 회원가입 이메일입니다.";
    private static final String TEMP_PASSWORD_TITLE = "ESCA 홈페이지 임시 비밀번호 발급 이메일입니다.";
    private static final String VALIDATE_MESSAGE = "<p>안녕하세요. ESCA 홈페이지 회원가입 안내 이메일입니다.</p>"
            + "\n" + "<p>아래의 버튼을 눌러 회원가입을 완료해주세요.</p>";
    private static final String TEMP_PASSWORD_MESSAGE = "<p>안녕하세요. ESCA 홈페이지 임시 비밀번호 발급 이메일입니다.</p>"
            + "\n" + "<p>아래의 임시 비밀번호로 로그인한 후 비밀번호를 재설정해주시기 바랍니다.</p>";

    private static final String FROM_ADDRESS = "wndmsdl0920@gmail.com";

    @Override
    public Mail createMail(UserCode code, String message, String toAddress) {
        String title, msg;
        if(code == UserCode.VALIDATE) {
            title = VALIDATE_TITLE;
            msg = VALIDATE_MESSAGE;
            message = "<div><a href='http://localhost:8080/users/validate?id=" + Integer.parseInt(message) + "' target='blank'> 회원가입 인증 </a></div>";
        } else {
            title = TEMP_PASSWORD_TITLE;
            msg = TEMP_PASSWORD_MESSAGE;
        }

        return Mail.builder()
                .toAddress(toAddress)
                .title(title)
                .message(msg + message)
                .fromAddress(FROM_ADDRESS)
                .build();
    }

    @Override
    public void sendMail(Mail mail) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
        helper.setTo(mail.getToAddress());
        helper.setSubject(mail.getTitle());
        helper.setText(String.format(mail.getMessage()), true);
        helper.setFrom(mail.getFromAddress());
        helper.setReplyTo(mail.getFromAddress());

        mailSender.send(mailMessage);
    }
}
