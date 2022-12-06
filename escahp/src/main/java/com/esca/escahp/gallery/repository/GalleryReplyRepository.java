package com.esca.escahp.gallery.repository;

import com.esca.escahp.gallery.entity.GalleryReply;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryReplyRepository extends JpaRepository<GalleryReply, Long> {

    List<GalleryReply> findByGalleryBoardIdAndDeleteYn(Long boardId, String yn);
}
