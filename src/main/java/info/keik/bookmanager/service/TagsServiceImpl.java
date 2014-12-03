package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.domain.TagsRepository;
import info.keik.bookmanager.model.Book;
import info.keik.bookmanager.model.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public List<Tag> findAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public Tag addTagToBook(Integer bookId, Tag tag) {
        Tag t = tagsRepository.findByName(tag.getName());
        Book book = booksRepository.findOne(bookId);

        if (t == null) {

            // Create a new tag
            t = tag;
            t.addBook(book);
            return tagsRepository.save(t);
        }

        if (t.getBooks().contains(book)) {

            // Already a book is tagged, nothing to update
            return null;
        } else {

            // Add a tag to a book
            t.addBook(book);
            return tagsRepository.save(t);
        }
    }

    @Override
    public Boolean deleteTagFromBook(Integer bookId, Integer tagId) {
        Tag tag = tagsRepository.findOne(tagId);
        Book book = booksRepository.findOne(bookId);

        if (tag == null) {

            // No tag to delete
            return false;
        }

        if (!tag.getBooks().contains(book)) {

            // A book isn't tagged, nothing to update
            return false;
        } else {

            // Delete a tag from a book
            tag.removeBook(book);
            tagsRepository.save(tag);
            return true;
        }
    }
}
