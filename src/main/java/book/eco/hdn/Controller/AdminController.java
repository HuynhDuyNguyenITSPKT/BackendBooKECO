package book.eco.hdn.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome Admin Dashboard";
    }
    
    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "Deleted user with id: " + id;
    }
}