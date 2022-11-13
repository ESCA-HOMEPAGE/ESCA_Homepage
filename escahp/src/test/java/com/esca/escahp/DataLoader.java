package com.esca.escahp;

import com.esca.escahp.auth.config.JwtTokenProvider;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.repository.StudyRepository;
import com.esca.escahp.user.entity.User;
import com.esca.escahp.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class DataLoader implements CommandLineRunner {
    public static final User user = new User("yjahn", "pw", 19, "rs", "ahn",4, "email@mail.com", "img", "this is pr!");
    public static final User user1 = User.builder()
        .userId("testId1")
        .password("test1234!")
        .email("test1@test.com")
        .generation(20)
        .nickname("nickOne")
        .name("test")
        .profileImg("profile1.jpg")
        .pr("hello")
        .rank(2)
        .build();
    public static final User user2 = User.builder()
        .userId("testId2")
        .password("test1234!")
        .email("test2@test.com")
        .generation(19)
        .nickname("nickTwo")
        .name("test")
        .profileImg("profile2.jpg")
        .pr("hi")
        .rank(1)
        .build();

    public static String token;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public DataLoader(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void run(String... args){
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
        token = jwtTokenProvider.createToken(user.getUserId());
        System.out.println("=============" + token);
    }
}
