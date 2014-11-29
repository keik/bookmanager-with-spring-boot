package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.domain.CommentsRepository;
import info.keik.bookmanager.model.Comment;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    @Transactional
    public void addCommentToBook(Integer bookId, Comment comment) {
        comment.setBook(booksRepository.findOne(bookId));
        commentsRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(Comment comment) {
        Comment target = commentsRepository.findOne(comment.getId());
        if (target == null) {

            // TODO
            throw new RuntimeException("TODO");
        }
        comment.setBook(target.getBook());
        commentsRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Integer id) {
        commentsRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteComments(List<Integer> ids) {
        commentsRepository.deleteByIdIn(ids);
    }

}
