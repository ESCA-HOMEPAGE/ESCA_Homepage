package com.esca.escahp.gallery;

import com.esca.escahp.gallery.dto.GalleryReplyRequest;
import com.esca.escahp.gallery.dto.GalleryReplyResponse;
import java.util.List;

public interface I_GalleryReplyService {

    List<GalleryReplyResponse> getGalleryReplyList(long postId);

    Long postGalleryReply(long postId, long userId, GalleryReplyRequest gr);

    void deleteGalleryReply(long id, long userId);
}
