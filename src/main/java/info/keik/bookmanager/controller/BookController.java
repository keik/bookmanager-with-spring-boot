package info.keik.bookmanager.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.keik.bookmanager.model.Book;
import info.keik.bookmanager.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model, @RequestParam(required = false) String q) {
        logger.info("show");
        if (q != null) {
            model.addAttribute("books", bookService.findBooksByTitle(q));
        } else {
            model.addAttribute("books", bookService.findAllBooks());
        }
        return "books-list.html";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Model model,
                         @RequestParam("title") String title,
                         @RequestParam("author") String author,
                         @RequestParam("publisher") String publisher
                         ) {
        logger.trace("create");
        bookService.addBook(new Book(title, author, publisher));
        return "redirect:/books";
    }

    @RequestMapping(method = RequestMethod.POST, params = "method=put")
    public String update() {
        logger.trace("update");
        // TODO
        return "update book";
    }

    @RequestMapping(method = RequestMethod.POST, params = "method=delete")
    public String deleteWithPost(Model model, @RequestParam("id") int id) {
        logger.trace("deleteWithPost");
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable("id") int id) {
        logger.trace("delete");
        bookService.deleteBook(id);
    }

}
