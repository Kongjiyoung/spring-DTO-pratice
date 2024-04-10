package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardResponse {

    public static class BoardDTO {
        private int id;
        private String title;
        private String content;

        public BoardDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content= board.getContent();;
        }
    }

    @Data
    public static class BoardTitleDTO {
        private int id;
        private String title;

        public BoardTitleDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    public static class BoardSaveDTO {
        private int id;
        private String title;
        private String content;

        public BoardSaveDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content= board.getContent();;
        }
    }
}
