package cc.serfer.ws.user.repository;

import cc.serfer.ws.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

   Optional<UserEntity>  findByEmail(String email);
   Optional<UserEntity>  findByUserId(String userId);

}
