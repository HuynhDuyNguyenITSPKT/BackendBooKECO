package book.eco.hdn.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String deviceName;

    @Column(nullable = false, unique = true)
    private String token;

    private OffsetDateTime expirationDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}