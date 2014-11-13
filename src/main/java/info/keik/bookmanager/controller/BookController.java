package info.keik.bookmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/books")
public class BookController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String show() {
        // TODO
        return "books-list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String create() {
        // TODO
        return "create book";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=put")
    public @ResponseBody String update() {
        // TODO
        return "update book";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=delete")
    public @ResponseBody String delete() {
        // TODO
        return "delete book";
    }

}
