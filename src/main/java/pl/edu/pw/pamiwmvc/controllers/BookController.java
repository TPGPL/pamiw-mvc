package pl.edu.pw.pamiwmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.pamiwmvc.dtos.BookDto;
import pl.edu.pw.pamiwmvc.services.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String get(Model model) {
        var books = service.getAll();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);

        return "redirect:/books";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("book", new BookDto());

        return "books/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute BookDto dto) {
        service.create(dto);

        return "redirect:/books";
    }
}
