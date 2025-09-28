package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello World with Hoi dan IT";
    }
    
    @GetMapping("/user")
    public String index2() {
        return "Hello World user";
    }

    @GetMapping("/admin")
    public String index3() {
        return "Hello World admin";
    }
    
}
