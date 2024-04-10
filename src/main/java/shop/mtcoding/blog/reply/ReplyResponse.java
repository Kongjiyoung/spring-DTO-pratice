package shop.mtcoding.blog.reply;

import shop.mtcoding.blog.board.Board;

public class ReplyResponse {
    public static class SaveDTO {
        private int id;
        private int userId;
        private int boardId;
        private String comment;

        public SaveDTO(Reply reply) {
            this.id = reply.getId();
            this.userId = reply.getUser().getId();
            this.boardId = reply.getBoard().getId();
            this.comment = reply.getComment();
        }
    }

}
