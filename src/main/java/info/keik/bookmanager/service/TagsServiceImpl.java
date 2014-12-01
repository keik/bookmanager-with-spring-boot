package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.domain.TagsRepository;
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

        // Sync
        Tag t = tagsRepository.findByName(tag.getName());
        if (t == null) {

            // Create new tag
            t = tag;
        }
        t.addBook(booksRepository.findOne(bookId));
        tagsRepository.save(t);
    }

    @Override
    public void deleteTagFromBook(Integer bookId, Tag tag) {

        // Sync
        tag = tagsRepository.findOne(tag.getId());

        tag.removeBook(booksRepository.findOne(bookId));
        tagsRepository.save(tag);
    }
}
