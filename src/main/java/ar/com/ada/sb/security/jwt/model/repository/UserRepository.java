package ar.com.ada.sb.security.jwt.model.repository;

import ar.com.ada.sb.security.jwt.model.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}
