package vknue.javaweb.earthstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vknue.javaweb.earthstore.models.Drink;
import vknue.javaweb.earthstore.models.UserLog;

public interface IUserLogRepository extends JpaRepository<UserLog, Long> {
}
