package su.uspenskit.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/")
public class HomeController {

    OperationData data = new OperationData();

    @GetMapping()
    public String getIndexPage(Model model) {
        model.addAttribute("data", data);
        return "index";
    }

    @PostMapping()
    public String result(@ModelAttribute("data") OperationData pageData, Model model) throws Exception {
        var value = switch (pageData.getOp()) {
            case '+' -> pageData.getA() + pageData.getB();
            case '-' -> pageData.getA() - pageData.getB();
            case '*' -> pageData.getA() * pageData.getB();
            case '/' -> pageData.getA() / pageData.getB();
            default -> throw new Exception("invalid operation value");
        };

        model.addAttribute("result", value);

        return "result";
    }

}
