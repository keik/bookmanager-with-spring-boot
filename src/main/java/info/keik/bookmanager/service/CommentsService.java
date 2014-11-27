package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Comment;

import java.util.List;

public interface CommentsService {

    public void addCommentToBook(Integer bookId, Comment comment);

    public void updateComment(Comment comment);

    public void deleteComment(Integer id);

    public void deleteComments(List<Integer> ids);
}
