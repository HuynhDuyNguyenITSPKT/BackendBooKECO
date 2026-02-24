package book.eco.hdn.Controller;

import org.springframework.web.bind.annotation.RestController;

import book.eco.hdn.Dto.Reponse.ApiReponse;
import book.eco.hdn.Dto.Reponse.AuthResponse;
import book.eco.hdn.Dto.Request.LoginRequest;
import book.eco.hdn.Service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiReponse login(@RequestBody LoginRequest request) {
        return new ApiReponse(1,"Login Success",authService.login(request));
    }

    @PostMapping("/refresh")
    public ApiReponse refresh(@RequestParam String refreshToken) {
        return new ApiReponse(1,"Take resfresh Token Success",authService.refresh(refreshToken));
    }

    
}
