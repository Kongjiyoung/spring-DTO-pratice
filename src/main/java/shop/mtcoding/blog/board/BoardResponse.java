package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.reply.Reply;

import java.util.List;

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

    public static class BoardDetailDTO {
        private int id;
        private String title;
        private String content;
        private String username;
        private Boolean isBoardOwner;
        private List<ReplyDTO> replies;

        public BoardDetailDTO(Board board, List<Reply> replies) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content= board.getContent();
            this.username=board.getUser().getUsername();
            this.isBoardOwner=board.isBoardOwner();
            this.replies=replies.stream().map(reply -> new ReplyDTO(reply)).toList();
        }

        public class ReplyDTO{
            private int id;
            private int userId;
            private String username;
            private String comment;

            public ReplyDTO(Reply reply) {
                this.id = reply.getId();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
                this.comment = reply.getComment();
            }
        }
    }
}
