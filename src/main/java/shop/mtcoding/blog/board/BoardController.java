package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRequest;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService ;
    private final HttpSession session;

    @GetMapping({ "/"})
    public String index(HttpServletRequest request) {
        List<BoardResponse.BoardTitleDTO> boards=boardService.findboards();
        request.setAttribute("boards", boards);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
        boardService.Save(reqDTO);
        return "redirect:/";
    }

    @GetMapping("/board/{boardId}")
    public String detail(@PathVariable Integer boardId, HttpServletRequest request) {  // int 를 쓰면 값이 없으면 0, Integer 를 넣으면 값이 없을 때 null 값이 들어옴.
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null) {
            BoardResponse.BoardDetailDTO boardDetail = boardService.boardDetailIsOwner(boardId, sessionUser.getId());
            request.setAttribute("board",boardDetail);
            System.out.println("boardDetail = " + boardDetail);
            return "board/detail";
        }
        BoardResponse.BoardDetailDTO boardDetail = boardService.boardDetail(boardId);
        request.setAttribute("board",boardDetail);
        System.out.println("boardDetail = " + boardDetail);
        return "board/detail";
    }

    @GetMapping("/board/{boardId}/update-form")
    public String updateForm(@PathVariable Integer boardId, HttpServletRequest request){
        BoardResponse.BoardDTO board = boardService.findboard(boardId);
        request.setAttribute("board", board);
        return "board/update-form";}

    @PostMapping("/board/{boardId}/update")
    public String update(@PathVariable Integer boardId, BoardRequest.UpdateDTO reqDTO){
        BoardResponse.BoardDTO respDTO=boardService.update(boardId,reqDTO);
        System.out.println("respDTO = " + respDTO);
        return "redirect:/board/"+boardId;
    }

    @PostMapping("/board/{boardId}/delete")
    public String delete(@PathVariable Integer boardId){
        boardService.delete(boardId);
        return "redirect:/";
    }
}
