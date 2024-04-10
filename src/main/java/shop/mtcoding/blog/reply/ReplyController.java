package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.board.BoardRequest;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService ;
    private final HttpSession session;

    @PostMapping("/board/{boardId}/reply/save")
    public String save(@PathVariable Integer boardId, ReplyRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        ReplyResponse.SaveDTO respDTO=replyService.Save(sessionUser.getId(), boardId, reqDTO);
        return "redirect:/board/"+boardId;
    }

    @PostMapping("/reply/{id}/delete")
    public String update(@PathVariable Integer id){
        replyService.delete(id);
        return "redirect:/";
    }
}
