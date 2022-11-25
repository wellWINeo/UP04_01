package su.uspenskit.calculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import su.uspenskit.calculator.models.Author;
import su.uspenskit.calculator.models.Book;
import su.uspenskit.calculator.repos.AuthorRepository;
import su.uspenskit.calculator.repos.BookRepository;

@Controller
public class BookController {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/books/all")
    public String getAll(Model model) {
        model.addAttribute("authors", this.authorRepository.findAll());
        model.addAttribute("books", this.bookRepository.findAll());
        return "books-list";
    }

    @GetMapping("/books/search")
    public String search(Model model, @RequestParam String query,
                         @RequestParam(value = "isExact", required = false, defaultValue = "false")
                         boolean isExact) {
        var books = isExact
                ? this.bookRepository.findByQueryExact(query)
                : this.bookRepository.findByQueryInexact(query);
        model.addAttribute("books", books);
        return "books-list";
    }

    @GetMapping("/books/create")
    public String createIndex(@RequestParam(required = false) Long id, Model model) {
        var entity = id != null ? this.bookRepository.findById(id) : new Book();
        model.addAttribute("book", entity);
        model.addAttribute("authors", this.authorRepository.findAll());
        model.addAttribute("author");
        return "create-book";
    }

    @PostMapping("/books/create")
    public String create(@ModelAttribute("book") Book book,
                         @ModelAttribute("author") Author author,
                         Model model) {
        this.bookRepository.save(book);
        var books = this.bookRepository.findAll();
        model.addAttribute("books", books);
        return "books-list";
    }

    @GetMapping("/books/delete")
    @ResponseBody()
    public  String delete(@RequestParam() Long id) {
        this.bookRepository.deleteById(id);
        return "Deleted!";
    }
}
