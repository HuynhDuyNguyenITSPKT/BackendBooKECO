package book.eco.hdn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import book.eco.hdn.Model.User;

public interface UserRes extends JpaRepository<User,Long>{
    
}
