package vknue.javaweb.earthstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vknue.javaweb.earthstore.models.Category;
import vknue.javaweb.earthstore.models.Transaction;

public interface ICategoryRepository extends JpaRepository<Category, Long>
{
    Category findByName(String name);
}
