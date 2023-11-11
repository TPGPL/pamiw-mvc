package pl.edu.pw.pamiwmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.pamiwmvc.dtos.AuthorDto;
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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);

        return "redirect:/authors";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("author", new AuthorDto());

        return "authors/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute AuthorDto dto) {
        service.create(dto);

        return "redirect:/authors";
    }

}
