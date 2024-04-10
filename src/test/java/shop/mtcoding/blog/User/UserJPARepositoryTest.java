package shop.mtcoding.blog.User;



import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserJPARepository;

import java.util.Optional;

@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void save_test() {
        // given
        int id = 1;

        // when
        Optional<User> userOP = userJPARepository.findById(id);

        if (userOP.isPresent()) {
            User user = userOP.get();
            System.out.println("findById_test : " + user.getUsername());
        }

        // then


    }

}
