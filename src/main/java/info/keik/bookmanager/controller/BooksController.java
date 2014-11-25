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
import info.keik.bookmanager.service.BooksService;

@Controller
@RequestMapping("/books")
public class BooksController {

    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    BooksService booksService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "q", required = false) String q) {
        logger.info("index");
        if (q != null) {
            model.addAttribute("books", booksService.findBooksByTitle(q));
            model.addAttribute("q", q);
        } else {
            model.addAttribute("books", booksService.findAllBooks());
        }
        return "books-index.html";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, Model model) {
        logger.trace("show");
        Book book = booksService.findBookById(id);
        model.addAttribute("book", book);
        return "book-show.html";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String new_(Model model) {
        logger.trace("new_");
        return "book-new.html";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model) {
        logger.trace("edit");
        Book book = booksService.findBookById(id);
        model.addAttribute("book", book);
        return "book-edit.html";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Model model,
                         @RequestParam("title") String title,
                         @RequestParam("author") String author,
                         @RequestParam("publisher") String publisher
                         ) {
        logger.trace("create");
        booksService.addBook(new Book(title, author, publisher));
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = "method=put")
    public String update(Book book) {
        logger.trace("update: " + book.toString());
        booksService.updateBook(book);
        return "redirect:/books";
    }

    @RequestMapping(method = RequestMethod.POST, params = "method=delete")
    public String destroyWithPost(Model model, @RequestParam("id") Integer id) {
        logger.trace("destroyWithPost");
        booksService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public @ResponseBody void destroy(@PathVariable("ids") String ids) {
        logger.trace("destroy");
        List<Integer> intIds = new ArrayList<Integer>();
        for (String strId : ids.split(",")) {
            intIds.add(Integer.valueOf(strId));
        }
        booksService.deleteBooks(intIds);
    }

}
