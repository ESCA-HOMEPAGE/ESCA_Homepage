package com.esca.escahp.gallery.repository;

import com.esca.escahp.gallery.entity.GalleryBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<GalleryBoard,Long> {
}
