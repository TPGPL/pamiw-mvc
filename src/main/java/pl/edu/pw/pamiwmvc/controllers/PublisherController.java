package pl.edu.pw.pamiwmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.pamiwmvc.dtos.PublisherDto;
import pl.edu.pw.pamiwmvc.services.PublisherService;

@Controller
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService service;

    @Autowired
    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping
    public String get(Model model) {
        var publishers = service.getAll();
        model.addAttribute("publishers", publishers);
        return "publishers/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);

        return "redirect:/publishers";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pub", new PublisherDto());

        return "publishers/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute PublisherDto dto) {
        service.create(dto);

        return "redirect:/publishers";
    }
}
