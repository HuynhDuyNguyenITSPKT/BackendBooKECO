package book.eco.hdn.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/profile")
    public String profile(Authentication authentication) {
        return "Hello User: " + authentication.getName();
    }
}