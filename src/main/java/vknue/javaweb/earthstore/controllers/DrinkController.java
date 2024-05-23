package vknue.javaweb.earthstore.controllers;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vknue.javaweb.earthstore.models.Drink;
import vknue.javaweb.earthstore.models.Category;
import vknue.javaweb.earthstore.repositories.ICategoryRepository;
import vknue.javaweb.earthstore.repositories.IDrinkRepository;

import java.util.List;
import java.util.Optional;

@Controller
@EnableWebSecurity
@RequestMapping("/drink")
public class DrinkController {

    private final IDrinkRepository drinkRepository;
    private final ICategoryRepository categoryRepository;

    public DrinkController(IDrinkRepository drinkRepository, ICategoryRepository categoryRepository) {
        this.drinkRepository = drinkRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/index")
    public String index(Model model) {
        List<Drink> drinks = drinkRepository.findAll();
        model.addAttribute("drinks", drinks);
        return "drinks";
    }

    @GetMapping("/get/add")
    public String add(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "addDrink";
    }

    @GetMapping("/get/edit")
    public String edit(@RequestParam("drinkId") long drinkId,
            Model model) {
        Drink drink = drinkRepository.getOne(drinkId);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("drink",drink);
        return "editDrink";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("drinkId") long drinkId) {
        try {
            drinkRepository.deleteById(drinkId);
            return "redirect:/drink/index";
        } catch (Exception e) {
            return "redirect:/drink/index";
        }
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("alcoholPercentage")double alcoholPercentage,
            @RequestParam("price")double price,
            @RequestParam("categoryId")long categoryId,
            @RequestParam("pictureUrl")String pictureUrl) {

        Category category = categoryRepository.getOne(categoryId);

        Drink drink = new Drink();
        drink.setName(name);
        drink.setType(type);
        drink.setAlcoholPercentage(alcoholPercentage);
        drink.setPrice(price);
        drink.setCategory(category);
        drink.setPictureUrl(pictureUrl);
        drinkRepository.save(drink);

        return "redirect:/drink/index";
    }

    @PostMapping("/edit")
    public String edit(
            @RequestParam("drinkId") long drinkId,
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("alcoholPercentage")double alcoholPercentage,
            @RequestParam("price")double price,
            @RequestParam("categoryId")long categoryId,
            @RequestParam("pictureUrl")String pictureUrl) {

        try {
            Category category = categoryRepository.findById(categoryId).get();
            Drink drink = drinkRepository.findById(drinkId).get();

            drink.setName(name);
            drink.setType(type);
            drink.setAlcoholPercentage(alcoholPercentage);
            drink.setPrice(price);
            drink.setCategory(category);
            drink.setPictureUrl(pictureUrl);
            drinkRepository.save(drink);
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return "redirect:/drink/index";
    }
}