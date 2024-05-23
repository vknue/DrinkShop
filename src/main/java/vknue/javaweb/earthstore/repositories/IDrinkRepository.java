package vknue.javaweb.earthstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vknue.javaweb.earthstore.models.Drink;

public interface IDrinkRepository extends JpaRepository<Drink, Long>
{

}
