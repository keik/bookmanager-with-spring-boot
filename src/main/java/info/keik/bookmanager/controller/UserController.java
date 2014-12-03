package info.keik.bookmanager.controller;

import info.keik.bookmanager.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    UsersService usersService;

    // TODO integrate Spring Security #10
}
