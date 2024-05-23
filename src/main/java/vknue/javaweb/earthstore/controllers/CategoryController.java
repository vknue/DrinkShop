package vknue.javaweb.earthstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vknue.javaweb.earthstore.models.cart.CartItem;
import vknue.javaweb.earthstore.repositories.ICategoryRepository;
import vknue.javaweb.earthstore.repositories.IDrinkRepository;
import vknue.javaweb.earthstore.services.CartService;
import vknue.javaweb.earthstore.models.*;

import java.util.List;

@Controller
@EnableWebSecurity
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryRepository categoryRepository;

    public CategoryController(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/index")
    public String showCart(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "addCategory";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("categoryId") long drinkId) {
        try {
            categoryRepository.deleteById(drinkId);
            return "redirect:/category/index";
        } catch (Exception e) {
            return "redirect:/category/index";
        }
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("name") String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return "redirect:/category/index";
    }
}
