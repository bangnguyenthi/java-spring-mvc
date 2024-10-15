package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello world from with hoidanit 1432!";
    }

    @GetMapping("/user")
    public String userPage() {
        return "Hello world from with user!";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Hello world from with admin!";
    }

}
