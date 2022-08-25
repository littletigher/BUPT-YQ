package teleDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class htmlController {

    @GetMapping("/html")
    public String getHtml(Model model){
        model.addAttribute("msg", "welcome you!");
        return "index";
    }
}
