package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.board.BoardRequest;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService ;
    private final HttpSession session;

    @PostMapping("/board/{boardId}/reply/save")
    public String save(@PathVariable Integer boardId, ReplyRequest.SaveDTO reqDTO){
        replyService.Save(reqDTO);
        return "redirect:/";
    }
}
