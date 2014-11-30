package info.keik.bookmanager.controller;

import info.keik.bookmanager.model.Tag;
import info.keik.bookmanager.service.TagsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class TagsController {

    @Autowired
    TagsService tagsService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("tags", tagsService.findAllTags());
        return "tags-index.html";
    }

    @RequestMapping(value = "/books/{bookId}/tags", method = RequestMethod.POST)
    public @ResponseBody String creteRelationWithBook(Model model,
            @PathVariable("bookId") Integer bookId, Tag tag) {
        tagsService.addTagToBook(bookId, tag);
        return null;
    }

    @RequestMapping(value = "/books/{bookId}/tags/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String destroyRelationWithBook(Model model,
            @PathVariable("bookId") Integer bookId, Tag tag) {
        tagsService.deleteTagFromBook(bookId, tag);
        return null;
    }

}
