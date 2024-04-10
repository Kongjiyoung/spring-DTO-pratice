package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository ;

    public UserResponse.SaveDTO Save(UserRequest.SaveDTO reqDTO){
        User user=userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.SaveDTO(user);
    }


    public User login(UserRequest.LoginDTO reqDTO) {
        User user=userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword()).orElseThrow(() -> new Exception401("인증이 되지 않았습니다"));
        return user;
    }


    public User update(int userId,UserRequest.UpdateDTO reqDTO) {
        User user=userJPARepository.findById(userId).orElseThrow(() -> new Exception403("해당 유저는 존재하지 않습니다"));
        user.update(reqDTO);
        return user;
    }
}
