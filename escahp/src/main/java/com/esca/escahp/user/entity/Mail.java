package com.esca.escahp.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    private String toAddress;
    private String title;
    private String message;
    private String fromAddress;
}
