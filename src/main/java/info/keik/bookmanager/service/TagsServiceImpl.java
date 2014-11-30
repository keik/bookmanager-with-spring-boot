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
    public void addTagToBook(Integer bookId, Tag tag) {
        Book book = booksRepository.findOne(bookId);
        tag.addBook(book);
        tagsRepository.save(tag);
    }

    @Override
    public void deleteTagFromBook(Integer bookId, Tag tag) {
        Tag fullAttrTag = tagsRepository.findOne(tag.getId());
        fullAttrTag.removeBook(booksRepository.findOne(bookId));
        tagsRepository.save(fullAttrTag);
    }
}
