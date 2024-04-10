package shop.mtcoding.blog.board;

public class BoardResponse {
    public static class BoardDTO {
        private int id;
        private String title;

        public BoardDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
