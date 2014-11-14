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

@Controller
@RequestMapping("/books")
public class BookController {

    private static final List<String> books = new ArrayList<String>() {{
            add("Spring3入門");
            add("計算幾プログラムの構造と解釈");
            add("軽快なJava");
        }};


    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("books", books);
        return "books-list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Model model, @RequestParam("title") String title) {
        books.add(title);
        return "redirect:/books";
    }

    @RequestMapping(method = RequestMethod.POST, params = "method=put")
    public String update() {
        // TODO
        return "update book";
    }

    @RequestMapping(method = RequestMethod.POST, params = "method=delete")
    public String deleteWithPost(Model model, @RequestParam("id") int id) {
        books.remove(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable("id") int id) {
        books.remove(id);
    }

}
