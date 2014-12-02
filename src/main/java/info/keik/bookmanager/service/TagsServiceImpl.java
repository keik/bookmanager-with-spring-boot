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
    public Boolean deleteTagFromBook(Integer bookId, Tag tag) {
        Tag t = tagsRepository.findOne(tag.getId());
        Book book = booksRepository.findOne(bookId);

        if (t == null) {

            // No tag to delete
            return false;
        }

        if (!t.getBooks().contains(book)) {

            // A book isn't tagged, nothing to update
            return false;
        } else {

            // Delete a tag from a book
            t.removeBook(booksRepository.findOne(bookId));
            tagsRepository.save(t);
            return true;
        }
    }
}
