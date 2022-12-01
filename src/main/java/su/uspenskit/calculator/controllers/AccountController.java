package su.uspenskit.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import su.uspenskit.calculator.models.Account;
import su.uspenskit.calculator.repos.AccountRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class AccountController {
    private final AccountRepository repo;

    public AccountController(AccountRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("account", new Account());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute Account account, Model model){
        repo.save(account);
        return "login";
    }

    @GetMapping("/addAccount")
    public String addAccount(Model model){
        model.addAttribute("account", new Account());
        model.addAttribute("isAddAction", true);
        return "account";
    }

    @PostMapping("/addAccount")
    public String addAccount(@ModelAttribute() @Valid Account account, BindingResult result, Model model){

        if (result.hasErrors()) {
            model.addAttribute("account", account);
            model.addAttribute("isAddAction", true);
            return "account";
        }

        repo.save(account);
        var list = StreamSupport.stream(repo.findAll().spliterator(), false).toList();
        model.addAttribute("accounts", list);
        return "accounts";
    }

    @GetMapping("/account/update/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        Account account = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));

        model.addAttribute("account", account);
        model.addAttribute("isAddAction", false);
        return "account";
    }

    @PostMapping("/account/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Account account,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("account", account);
            model.addAttribute("isAddAction", false);
            return "account";
        }

        account.setId(id);
        repo.save(account);

        List<Account> list = StreamSupport.stream(repo.findAll().spliterator(), false).toList();
        model.addAttribute("accounts", list);
        return "accounts";
    }

    @GetMapping("/account/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        repo.deleteById(id);

        List<Account> list = StreamSupport.stream(repo.findAll().spliterator(), false).toList();
        model.addAttribute("accounts", list);
        return "accounts";
    }

    @GetMapping("/accounts")
    public String roles(Model model){
        List<Account> list = StreamSupport.stream(repo.findAll().spliterator(), false).toList();
        model.addAttribute("accounts", list);
        return "accounts";
    }
}