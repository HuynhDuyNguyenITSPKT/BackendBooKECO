package book.eco.hdn.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/auth")
public class Auth {
    
    @GetMapping("/login")
    public String login() {
        return "Login Success";
    }
    
    @GetMapping("/register")
    public String register() {
        return "register Success";
    }

    @GetMapping("/refresh-token")
    public String refreshtoken() {
        return "refresh-token Success";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout Success";
    }

}
