package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Comment;

import java.util.List;

public interface CommentsService {

    /**
     * Add a comment to a book
     * 
     * @param bookId
     *            Book Id for adding a comment
     * @param comment
     *            A comment to add
     */
    public void addCommentToBook(Integer bookId, Comment comment);

    /**
     * Update a comment
     * 
     * @param comment
     *            A comment to update
     */
    public void updateComment(Comment comment);

    /**
     * Delete a comment
     * 
     * @param id
     *            Comment ID to delete
     */
    public void deleteComment(Integer id);

    /**
     * Delete comments
     * 
     * @param ids
     *            Comment IDs to delete
     */
    public void deleteComments(List<Integer> ids);

}
