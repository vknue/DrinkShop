package vknue.javaweb.earthstore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vknue.javaweb.earthstore.events.AddToCartEvent;
import vknue.javaweb.earthstore.models.Drink;
import vknue.javaweb.earthstore.models.Category;
import vknue.javaweb.earthstore.models.cart.CartItem;
import vknue.javaweb.earthstore.repositories.ICategoryRepository;
import vknue.javaweb.earthstore.repositories.IDrinkRepository;

import java.util.List;

@Controller
@RequestMapping("/shop")
@AllArgsConstructor
public class ShopController {

    @Autowired
    private IDrinkRepository drinkRepository;
    @Autowired
    private ICategoryRepository categoryRepository;


    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/drinks")
    public String getDrinks(Model model) {
        List<Drink> drinks = drinkRepository.findAll();
        model.addAttribute("cartItems", drinks);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "home";
    }

    @PostMapping("/addItemToCart")
    public String addItemToCart(@RequestParam("drinkId") long drinkId)
    {
        CartItem cartItem = new CartItem();
        Drink drink = drinkRepository.findById(drinkId).get();
        cartItem.setDrink( drink );
        cartItem.setQuantity(1);

        eventPublisher.publishEvent(new AddToCartEvent(this, cartItem));


        return "redirect:/cart/myCart";
    }
}


