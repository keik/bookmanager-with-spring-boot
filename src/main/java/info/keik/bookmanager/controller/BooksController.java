package info.keik.bookmanager.controller;

import info.keik.bookmanager.model.Book;
import info.keik.bookmanager.service.BooksService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model,
            @RequestParam(value = "q", required = false) String q) {
        if (q != null) {
            model.addAttribute("books", booksService.findBooksByQuery(q));
            model.addAttribute("q", q);
        } else {
            model.addAttribute("books", booksService.findAllBooks());
        }
        return "books-index.html";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, Model model) {
        Book book = booksService.findBookById(id);
        model.addAttribute("book", book);
        return "book-show.html";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String new_(Model model) {
        return "book-new.html";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model) {
        Book book = booksService.findBookById(id);
        model.addAttribute("book", book);
        return "book-edit.html";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Model model, Book book) {
        booksService.addBook(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = "method=put")
    public String update(Book book) {
        booksService.updateBook(book);
        return "redirect:/books";
    }

    @RequestMapping(method = RequestMethod.POST, params = "method=delete")
    public String destroyWithPost(Model model, @RequestParam("id") Integer id) {
        booksService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public @ResponseBody void destroy(@PathVariable("ids") String ids) {
        List<Integer> intIds = new ArrayList<Integer>();
        for (String strId : ids.split(",")) {
            intIds.add(Integer.valueOf(strId));
        }
        booksService.deleteBooks(intIds);
    }

}
