package su.uspenskit.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import su.uspenskit.calculator.models.Author;
import su.uspenskit.calculator.repos.AuthorRepository;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors/all")
    public String getAll(Model model) {
        var authors = this.authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors-list";
    }

    @GetMapping("/authors/search")
    public String getAll(Model model, @RequestParam String query,
                         @RequestParam(value = "isExact", required = false, defaultValue = "false")
                         boolean isExact) {
        var authors = isExact
                ? this.authorRepository.findByQueryExact(query)
                : this.authorRepository.findByQueryInexact(query);
        model.addAttribute("authors", authors);
        return "authors-list";
    }

    @GetMapping("/authors/create")
    public String createIndex(Model model) {
        model.addAttribute("author", new Author());
        return "create-author";
    }

    @PostMapping("/authors/create")
    public String create(@ModelAttribute("author") Author author, Model model) {
        this.authorRepository.save(author);
        var authors = this.authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors-list";
    }

}
