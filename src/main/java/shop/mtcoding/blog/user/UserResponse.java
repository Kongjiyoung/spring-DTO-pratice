package shop.mtcoding.blog.user;

import lombok.Data;

public class UserResponse {
    @Data
    public static class SaveDTO{
        private String username;
        private String password;
        private String email;

        public SaveDTO(User user) {
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.email = user.getEmail();
        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

}
