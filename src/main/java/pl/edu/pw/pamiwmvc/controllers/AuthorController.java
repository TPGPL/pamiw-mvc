package pl.edu.pw.pamiwmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pw.pamiwmvc.services.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public String get(Model model) {
        var authors = service.getAll();
        model.addAttribute("authors", authors);
        return "authors/index";
    }
}
