package com.esca.escahp.gallery.repository;

import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.entity.GalleryBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryLikesRepository extends JpaRepository<GalleryBoardLike, Long> {

}
