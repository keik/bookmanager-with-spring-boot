package info.keik.bookmanager.controller;

import info.keik.bookmanager.model.Comment;
import info.keik.bookmanager.service.CommentsService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/books/{bookId}/comments")
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    @RequestMapping(method = RequestMethod.POST)
    public String create(Model model, Comment comment,
            @PathVariable("bookId") Integer bookId) {
        commentsService.addCommentToItem(bookId, comment);
        return "redirect:/books/{bookId}";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = "method=put")
    public @ResponseBody void update(Comment comment) {
        commentsService.updateComment(comment);
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public @ResponseBody void destroy(@PathVariable("ids") String ids) {
        List<Integer> intIds = new ArrayList<Integer>();
        for (String strId : ids.split(",")) {
            intIds.add(Integer.valueOf(strId));
        }
        commentsService.deleteComments(intIds);
    }

}
