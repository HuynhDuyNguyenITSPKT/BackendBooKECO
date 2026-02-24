package book.eco.hdn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import book.eco.hdn.Model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
}
