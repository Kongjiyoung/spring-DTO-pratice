package shop.mtcoding.blog.user;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService ;
    private final HttpSession session ;

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.SaveDTO reqDTO){
        userService.Save(reqDTO);
        return "redirect:/";
    }
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO,HttpServletRequest request){
        User user=userService.login(reqDTO);
        session.setAttribute("sessionUser", user);
        request.setAttribute("sessionUser", user);
        return "redirect:/";
    }
    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
