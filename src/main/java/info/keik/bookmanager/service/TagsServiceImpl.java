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

        // Sync
        Tag t = tagsRepository.findByName(tag.getName());
        Book book = booksRepository.findOne(bookId);

        if (t == null) {

            // Create new tag
            t = tag;
        } else if (t.getBooks().contains(book)) {

            // Already tagged with the book.
            return null;
        }
        t.addBook(book);
        return tagsRepository.save(t);
    }

    @Override
    public Boolean deleteTagFromBook(Integer bookId, Tag tag) {

        // Sync
        Tag t = tagsRepository.findOne(tag.getId());
        Book book = booksRepository.findOne(bookId);
        if (t == null) {
            return false;
        } else if (!t.getBooks().contains(book)) {
            return false;
        }
        t.removeBook(booksRepository.findOne(bookId));
        tagsRepository.save(t);
        return true;
    }
}
