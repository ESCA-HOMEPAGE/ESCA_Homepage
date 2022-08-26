package com.esca.escahp.keeper.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Keeper {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

}