package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Tag;

import java.util.List;

public interface TagsService {

    public List<Tag> findAllTags();

    public void addTagToBook(Integer bookId, Tag tag);

    public void deleteTagFromBook(Integer bookId, Tag tag);

}
