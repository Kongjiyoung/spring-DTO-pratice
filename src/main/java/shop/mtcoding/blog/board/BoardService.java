package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.reply.ReplyJPARepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository ;
    private final ReplyJPARepository replyJPARepository;

    public List<BoardResponse.BoardTitleDTO> findboards(){
        List<Board> boards=boardJPARepository.findAll();
        return boards.stream().map(BoardResponse.BoardTitleDTO::new).toList();
    }

    @Transactional
    public BoardResponse.BoardSaveDTO Save(BoardRequest.SaveDTO reqDTO) {
        Board board=boardJPARepository.save(reqDTO.toEntity());
        return new BoardResponse.BoardSaveDTO(board);
    }

    public BoardResponse.BoardDTO findboard(Integer boardId) {
        Board board=boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("해당 페이지가 없습니다"));
        return new BoardResponse.BoardDTO(board);
    }

    @Transactional
    public BoardResponse.BoardDTO update(Integer boardId, BoardRequest.UpdateDTO reqDTO) {
        Board board=boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("해당 페이지가 없습니다"));
//        board.update(reqDTO);
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
        System.out.println(" board= " + new BoardResponse.BoardDTO(board));
        return new BoardResponse.BoardDTO(board);
    }

    public BoardResponse.BoardDetailDTO boardDetailIsOwner(Integer boardId, Integer userId) {
        Board board=boardJPARepository.findByBoardId(boardId).orElseThrow(() -> new Exception404("해당 페이지가 없습니다"));
        List<Reply> replies = replyJPARepository.findByBoardId(boardId);

        board.setBoardOwner(false);
        if (board.getUser().getId().equals(userId)){
            board.setBoardOwner(true);
        }

        return new BoardResponse.BoardDetailDTO(board, replies);
    }
    public BoardResponse.BoardDetailDTO boardDetail(Integer boardId) {
        Board board=boardJPARepository.findByBoardId(boardId).orElseThrow(() -> new Exception404("해당 페이지가 없습니다"));
        List<Reply> replies = replyJPARepository.findByBoardId(boardId);

        board.setBoardOwner(false);

        return new BoardResponse.BoardDetailDTO(board, replies);
    }

    @Transactional
    public void delete(Integer boardId) {
        Board board=boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("해당 페이지가 없습니다"));
        boardJPARepository.delete(board);
    }
}
