package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepository ;

    public UserResponse.SaveDTO Save(UserRequest.SaveDTO reqDTO){
        User user=userJPARepository.save(reqDTO.toEntity());
        return new UserResponse.SaveDTO(user);
    }

//    public UserResponse.LoginDTO findByUsernameAndPassword(UserRequest.LoginDTO reqDTO) {
//        User user=
//        return new UserResponse.LoginDTO(user);
//    }
}
