package com.esca.escahp.user;

import com.esca.escahp.user.dto.ChangePasswordRequest;
import com.esca.escahp.user.dto.FindRequest;
import com.esca.escahp.user.dto.UserRequest;
import com.esca.escahp.user.dto.UserResponse;
import com.esca.escahp.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = {"User"})
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"*"})
public class UserController {

    private final UserService userService;

    private final String HTML_RESPONSE_HEAD = "<!doctype html>\n" +
            "<html> \n" +
            "    <head>\n";
    private final String HTML_RESPONSE_FOOT = "        </script>\n" +
            "    </head>\n" +
            "<script defer>\n" +
            "   window.close()" +
            "</script>\n" +
            "</html>";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "회원가입")
    @PostMapping
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest request) throws MessagingException {
        UserResponse result = new UserResponse(
                userService.addUser(
                        new User(request)
                )
        );
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "아이디 찾기")
    @GetMapping("/help")
    public ResponseEntity<String> findId(@RequestBody FindRequest request) {
        String result = userService.findUserId(request.getName(), request.getEmail());
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "사용자 자의에 의한 비밀번호 변경")
    @PutMapping("/help/myInfo")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.resetPassword(
                request.getUserId(),
                request.getOldPassword(),
                request.getOldPassword()
        );

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "분실한 비밀번호 재설정")
    @PutMapping("/help")
    public ResponseEntity<Void> resetPassword(@RequestBody String userId) throws MessagingException {
        userService.resetPassword(userId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "회원가입 인증")
    @GetMapping("/validate")
    public void validateUser(@RequestParam Long id, HttpServletResponse response) throws IOException {
        String script;

        if(userService.validateUser(id) != null) {
            script = "      <title>회원가입 인증</title>" +
                    "       <script> \n" +
                    "           alert(\"인증이 완료되었습니다.\");\n";
        } else {
            script = "      <title>ERROR</title>" +
                    "      <script> \n" +
                    "           alert(\"잘못된 접근입니다.\");\n";
        }
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(HTML_RESPONSE_HEAD + script + HTML_RESPONSE_FOOT);
    }
}
