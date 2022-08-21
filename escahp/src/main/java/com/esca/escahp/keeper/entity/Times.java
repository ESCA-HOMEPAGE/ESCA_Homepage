package com.esca.escahp.keeper.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class Times {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String time;
}
