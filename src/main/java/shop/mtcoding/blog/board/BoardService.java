package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository ;

    public List<BoardResponse.BoardTitleDTO> findboards(){
        List<Board> boards=boardJPARepository.findAll();
        return boards.stream().map(BoardResponse.BoardTitleDTO::new).toList();
    }

    public BoardResponse.BoardSaveDTO Save(BoardRequest.SaveDTO reqDTO) {
        Board board=boardJPARepository.save(reqDTO.toEntity());
        return new BoardResponse.BoardSaveDTO(board);
    }

    public BoardResponse.BoardDTO findboard(Integer boardId) {
        Board board=boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("해당 페이지가 없습니다"));
        return new BoardResponse.BoardDTO(board);
    }

    public BoardResponse.BoardDTO update(Integer boardId, BoardRequest.UpdateDTO reqDTO) {
        Board board=boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("해당 페이지가 없습니다"));
        board.update(reqDTO);

        return new BoardResponse.BoardDTO(board);
    }
}
