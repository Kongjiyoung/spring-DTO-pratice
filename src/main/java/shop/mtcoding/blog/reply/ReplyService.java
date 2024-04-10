package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
import shop.mtcoding.blog.board.BoardRequest;
import shop.mtcoding.blog.board.BoardResponse;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserJPARepository;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJPARepository replyJPARepository ;
    private final UserJPARepository userJPARepository;
    private final BoardJPARepository boardJPARepository;

    public ReplyResponse.SaveDTO Save(int userId, int boardId, ReplyRequest.SaveDTO reqDTO) {
        User user = userJPARepository.findById(userId).orElseThrow();
        Board board = boardJPARepository.findById(boardId).orElseThrow();
        reqDTO.setUser(user);
        reqDTO.setBoard(board);
        Reply reply=replyJPARepository.save(reqDTO.toEntity());
        return new ReplyResponse.SaveDTO(reply);
    }
    public void delete(Integer replyId) {
        Reply reply=replyJPARepository.findById(replyId).orElseThrow(() -> new Exception404("해당 페이지가 없습니다"));
        replyJPARepository.delete(reply);
    }
}
