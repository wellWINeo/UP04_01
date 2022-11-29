package su.uspenskit.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import su.uspenskit.calculator.models.Author;
import su.uspenskit.calculator.repos.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String createIndex(@RequestParam(required = false) Long id, Model model) {
        var entity = id != null ? this.authorRepository.findById(id) : new Author();
        model.addAttribute("author", entity);
        return "create-author";
    }

    @PostMapping("/authors/create")
    public String create(@Valid @ModelAttribute("author") Author author,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) return "authors-list";
        this.authorRepository.save(author);
        var authors = this.authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors-list";
    }

    @GetMapping("/authors/delete")
    @ResponseBody()
    public String delete(@RequestParam() Long id) {
        this.authorRepository.deleteById(id);
        return "Deleted";
    }

}
