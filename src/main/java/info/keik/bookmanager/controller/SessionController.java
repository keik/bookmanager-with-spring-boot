package info.keik.bookmanager.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/session")
public class SessionController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String isLogin(Principal principal) {
        return principal != null ? principal.toString() : null;
    }

}
