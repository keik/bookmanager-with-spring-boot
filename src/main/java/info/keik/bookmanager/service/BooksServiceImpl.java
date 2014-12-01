package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.domain.TagsRepository;
import info.keik.bookmanager.model.Book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    @Transactional
    public void addBook(Book book) {
        booksRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Integer id) {
        booksRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteBooks(List<Integer> ids) {
        for (Integer id : ids) {
            booksRepository.findOne(id).getTags().clear();
        }
        booksRepository.deleteByIdIn(ids);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        Book target = booksRepository.findOne(book.getId());
        if (target == null) {

            // TODO
            throw new RuntimeException("TODO");
        }
        booksRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Book findBookById(Integer id) {
        return booksRepository.getOne(id);
    }

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public List<Book> findBooksByQuery(String query) {
        String[] queries = query.split(":");
        if (queries.length == 2 && queries[0].equals("tag")) {
            return booksRepository.findByTag2(tagsRepository
                    .findByName(queries[1]));
        } else {
            return booksRepository.findByTitleContaining(query);
        }

        // experiment...
        // List<String> titles = new ArrayList<String>();
        // titles.add("%Land of Lisp%");
        // titles.add("軽快なJava");
        // return booksRepository.findAll();
        // return booksRepository.findByTag(tags);
        // return booksRepository.findByTag2(tagsRepository.findOne(1));
        // return booksRepository.findByTag3(titles);
        // return booksRepository.findByTitleContaining(query);
    }
}
