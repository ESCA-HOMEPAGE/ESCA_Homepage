package com.esca.escahp.user.dto;

import com.esca.escahp.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileRequest {
    @ApiModelProperty(value = "닉네임")
    private String nickname;

    @ApiModelProperty(value = "이메일")
    private String email;

    @ApiModelProperty(value = "프로필 이미지 주소")
    private String profileImg;

    @ApiModelProperty(value = "자기소개")
    private String pr;

    public UserProfileRequest(User auth){
        this.nickname = auth.getNickname();
        this.email = auth.getEmail();
        this.profileImg = auth.getProfileImg();
        this.pr = auth.getPr();
    }
}
