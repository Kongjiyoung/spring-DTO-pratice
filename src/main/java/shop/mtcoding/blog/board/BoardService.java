package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository ;

    public List<BoardResponse.BoardDTO> findboards(){
        List<Board> boards=boardJPARepository.findAll();
        return boards.stream().map(BoardResponse.BoardDTO::new).toList();
    }
}
