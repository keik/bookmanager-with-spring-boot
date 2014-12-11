package info.keik.bookmanager.controller;

import info.keik.bookmanager.model.item.Book;
import info.keik.bookmanager.service.BooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, Model model) {
        Book book = booksService.findBookById(id);
        model.addAttribute("book", book);
        return "book-show.html";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model) {
        Book book = booksService.findBookById(id);
        model.addAttribute("book", book);
        return "book-edit.html";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = "method=put")
    public String update(Book book) {
        booksService.updateBook(book);
        return "redirect:/stocks";
    }

}
