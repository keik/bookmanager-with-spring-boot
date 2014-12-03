package info.keik.bookmanager.domain;

import info.keik.bookmanager.model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Integer> {

    Tag findByName(String name);
}
