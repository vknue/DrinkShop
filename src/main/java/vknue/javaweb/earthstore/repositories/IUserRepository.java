package vknue.javaweb.earthstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vknue.javaweb.earthstore.models.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}