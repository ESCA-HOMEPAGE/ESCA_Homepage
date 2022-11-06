package com.esca.escahp.gallery.entity;

import com.esca.escahp.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class GalleryBoardLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GalleryBoard galleryBoard;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @Builder
    public GalleryBoardLike(Long id, GalleryBoard galleryBoard, User user){
        this.id = id;
        this.galleryBoard = galleryBoard;
        this.user = user;
    }

    //todo
    //public void countLikes() {this.likes = this.likes+1;}
}
