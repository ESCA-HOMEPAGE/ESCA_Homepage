package com.esca.escahp.study;

import com.esca.escahp.common.exceptions.BoardExceptions;
import com.esca.escahp.exception.EscaException;
import com.esca.escahp.study.dto.StudyReplyRequest;
import com.esca.escahp.study.dto.StudyReplyResponse;
import com.esca.escahp.study.entity.StudyBoard;
import com.esca.escahp.study.entity.StudyReply;
import com.esca.escahp.study.repository.StudyReplyRepository;
import com.esca.escahp.study.repository.StudyRepository;
import com.esca.escahp.user.entity.User;
import com.esca.escahp.user.exception.UserExceptions;
import com.esca.escahp.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudyReplyService implements I_StudyReplyService {

    private final StudyReplyRepository studyReplyRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    public StudyReplyService(StudyReplyRepository studyReplyRepository,
        StudyRepository studyRepository,
        UserRepository userRepository) {
        this.studyReplyRepository = studyReplyRepository;
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<StudyReplyResponse> getStudyReplyList(long postId) {
        getStudyBoard(postId);

        return studyReplyRepository.findByStudyBoardIdAndDeleteYn(postId, "N")
            .stream()
            .map(StudyReplyResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long postStudyReply(long postId, long userId, StudyReplyRequest sr) {
        StudyBoard board = getStudyBoard(postId);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EscaException(UserExceptions.NOT_FOUND_USER));

        return studyReplyRepository.save(sr.toEntity(board, user)).getId();
    }

    @Transactional
    @Override
    public void deleteStudyReply(Long id, long userId) {
        StudyReply reply = studyReplyRepository.findById(id)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_REPLY));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EscaException(UserExceptions.NOT_FOUND_USER));

        if (reply.getUser().equals(user)) {
            reply.delete();
        } else {
            throw new EscaException(BoardExceptions.FORBIDDEN_DELETE_REPLY);
        }
    }

    StudyBoard getStudyBoard(long postId) {
        StudyBoard studyBoard = studyRepository.findById(postId)
            .orElseThrow(() -> new EscaException(BoardExceptions.NOT_FOUND_BOARD));

        if (studyBoard.getDeleteYn().equals("Y")) {
            throw new EscaException(BoardExceptions.NOT_FOUND_BOARD);
        }

        return studyBoard;
    }
}
