package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Tag;

import java.util.List;

public interface TagsService {

    /**
     * Return all tags
     * 
     * @return All tags
     */
    public List<Tag> findAllTags();

    /**
     * Add a tag to a book
     * 
     * @param bookId
     *            Book ID for adding a tag
     * @param tag
     *            A tag to add
     * @return The added tag, or null if the tag wasn't added
     */
    public Tag addTagToBook(Integer bookId, Tag tag);

    /**
     * Remove a tag from the book
     * 
     * @param bookId
     *            Book ID for removing the tag
     * @param tag
     *            A tag to remove
     * @return true for success, or false for fail
     */
    public Boolean deleteTagFromBook(Integer bookId, Tag tag);

}
