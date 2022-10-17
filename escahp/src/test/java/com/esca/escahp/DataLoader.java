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
    public static final StudyBoard board1 = new StudyBoard("title", "content", "writer", "category", "file");
    public static final User user = new User("yjahn", "pw", 19, "rs", "ahn",4, "email@mail.com", "img", "this is pr!");

    public static String token;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public DataLoader(StudyRepository studyRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void run(String... args){
        studyRepository.save(board1);
        userRepository.save(user);
        token = jwtTokenProvider.createToken(user.getUserId());
        System.out.println("=============" + token);
    }
}
