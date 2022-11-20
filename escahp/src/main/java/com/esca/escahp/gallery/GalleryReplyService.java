package com.esca.escahp.gallery;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.gallery.dto.GalleryReplyRequest;
import com.esca.escahp.gallery.dto.GalleryReplyResponse;
import com.esca.escahp.gallery.entity.GalleryBoard;
import com.esca.escahp.gallery.entity.GalleryReply;
import com.esca.escahp.gallery.repository.GalleryReplyRepository;
import com.esca.escahp.gallery.repository.GalleryRepository;
import com.esca.escahp.user.entity.User;
import com.esca.escahp.user.exception.UserExceptions;
import com.esca.escahp.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GalleryReplyService implements I_GalleryReplyService {

    private final GalleryReplyRepository galleryReplyRepository;
    private final GalleryRepository galleryRepository;
    private final UserRepository userRepository;

    public GalleryReplyService(GalleryReplyRepository galleryReplyRepository,
        GalleryRepository galleryRepository, UserRepository userRepository) {
        this.galleryReplyRepository = galleryReplyRepository;
        this.galleryRepository = galleryRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<GalleryReplyResponse> getGalleryReplyList(long postId) {
        getGalleryBoard(postId);

        return galleryReplyRepository.findByGalleryBoardIdAndDeleteYn(postId, "N")
            .stream()
            .map(GalleryReplyResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long postGalleryReply(long postId, long userId, GalleryReplyRequest gr) {
        GalleryBoard board = getGalleryBoard(postId);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EscaException(UserExceptions.NOT_FOUND_USER));

        return galleryReplyRepository.save(gr.toEntity(board, user)).getId();
    }

    @Transactional
    @Override
    public void deleteGalleryReply(long id, long userId) {
        GalleryReply reply = galleryReplyRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_REPLY));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EscaException(UserExceptions.NOT_FOUND_USER));

        if (reply.getUser().equals(user)) {
            reply.delete();
        } else {
            throw new EscaException(BoardExceptions.FORBIDDEN_DELETE_REPLY);
        }
    }

    GalleryBoard getGalleryBoard(long postId) {
        GalleryBoard galleryBoard = galleryRepository.findById(postId)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));
        if (galleryBoard.getDeleteYn().equals("Y")) {
            throw new EscaException(BoardExceptions.NOT_FOUND_BOARD);
        }

        return galleryBoard;
    }
}
