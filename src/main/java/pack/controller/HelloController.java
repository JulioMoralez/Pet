package pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    String sayHello(Model model) {
        model.addAttribute("message","hello");
        return "index";
    }
}
