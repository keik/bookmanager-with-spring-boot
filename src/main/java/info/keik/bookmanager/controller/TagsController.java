package info.keik.bookmanager.controller;

import info.keik.bookmanager.model.Tag;
import info.keik.bookmanager.service.TagsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody ResponseEntity<Tag> creteRelationWithBook(Model model,
            @PathVariable("bookId") Integer bookId, Tag tag) {
        Tag added = tagsService.addTagToBook(bookId, tag);
        if (added != null) {
            return new ResponseEntity<Tag>(added, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Tag>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/books/{bookId}/tags/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<String> destroyRelationWithBook(
            Model model, @PathVariable("bookId") Integer bookId,
            @PathVariable("id") Integer tagId) {
        if (tagsService.deleteTagFromBook(bookId, tagId)) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

}
