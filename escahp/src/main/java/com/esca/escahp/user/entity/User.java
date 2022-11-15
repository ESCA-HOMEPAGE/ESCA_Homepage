package com.esca.escahp.user.entity;

import com.esca.escahp.user.dto.UserProfileRequest;
import com.esca.escahp.user.dto.UserRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int generation;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String name;

    @Column
    private int rank;

    @Column(nullable = false)
    private String email;

    @Column
    private String profileImg;

    @Column
    private String pr;

    public User(UserRequest auth) {
        this.id = auth.getId();
        this.userId = auth.getUserId();
        this.password = auth.getPassword();
        this.generation = auth.getGeneration();
        this.nickname = auth.getNickname();
        this.name = auth.getName();
        this.rank = auth.getRank();
        this.email = auth.getEmail();
        this.profileImg = auth.getProfileImg();
        this.pr = auth.getPr();
    }

    @Builder
    public User(String userId, String password, int generation, String nickname, String name,
                int rank, String email, String profileImg, String pr) {

        this.userId = userId;
        this.password = password;
        this.generation = generation;
        this.nickname = nickname;
        this.name = name;
        this.rank = rank;
        this.email = email;
        this.profileImg = profileImg;
        this.pr = pr;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateRank(int rank) {
        this.rank = rank;
    }

    public void updateUserProfile(UserProfileRequest request) {
        this.nickname = request.getNickname();
        this.email = request.getEmail();
        this.profileImg = request.getProfileImg();
        this.pr = request.getPr();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && generation == user.generation && rank == user.rank && userId.equals(
                user.userId) && password.equals(user.password) && nickname.equals(user.nickname)
                && name.equals(user.name) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, generation, nickname, name, rank, email);
    }
}
