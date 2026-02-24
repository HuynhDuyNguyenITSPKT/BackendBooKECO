package book.eco.hdn.Service;

import book.eco.hdn.Dto.Reponse.AuthResponse;
import book.eco.hdn.Dto.Request.LoginRequest;
import book.eco.hdn.Model.RefreshToken;
import book.eco.hdn.Model.User;
import book.eco.hdn.Repository.RefreshTokenRepository;
import book.eco.hdn.Repository.UserRepository;
import book.eco.hdn.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        refreshTokenRepository.deleteByUser(user);

        RefreshToken tokenEntity = RefreshToken.builder()
                .user(user)
                .token(refreshToken)
                .deviceName(request.getDevice())
                .expirationDateTime(OffsetDateTime.now().plusDays(7))
                .build();

        refreshTokenRepository.save(tokenEntity);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refresh(String refreshToken) {

        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (token.getExpirationDateTime().isBefore(OffsetDateTime.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        String newAccess = jwtService.generateAccessToken(token.getUser());

        return new AuthResponse(newAccess, refreshToken);
    }
}