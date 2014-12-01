package info.keik.bookmanager.domain;

import info.keik.bookmanager.model.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {

    @Transactional
    void deleteByIdIn(List<Integer> ids);

}
