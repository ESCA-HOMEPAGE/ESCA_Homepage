package com.esca.escahp.board.entity;

import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.user.entity.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FreeBoardLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "freeboard_id", nullable = false)
    private FreeBoard freeBoard;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public FreeBoardLike(long id, FreeBoard freeBoard, User user){
        this.id = id;
        this.freeBoard = freeBoard;
        this.user = user;
    }

}
