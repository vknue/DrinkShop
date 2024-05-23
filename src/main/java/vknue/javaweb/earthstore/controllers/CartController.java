package vknue.javaweb.earthstore.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vknue.javaweb.earthstore.events.AddToCartEvent;
import vknue.javaweb.earthstore.models.Drink;
import vknue.javaweb.earthstore.models.cart.CartItem;
import vknue.javaweb.earthstore.repositories.IDrinkRepository;
import vknue.javaweb.earthstore.services.CartService;
import vknue.javaweb.earthstore.services.UserDetailService;

import java.util.ArrayList;
import java.util.List;

@Controller
@EnableWebSecurity
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {
    private final CartService cartService;
    private final UserDetailService userDetailService;
    private final ApplicationEventPublisher eventPublisher;
    private final IDrinkRepository drinkRepository;

    public CartController(CartService cartService, UserDetailService userDetailService, ApplicationEventPublisher eventPublisher, IDrinkRepository drinkRepository) {
        this.cartService = cartService;
        this.userDetailService = userDetailService;
        this.eventPublisher = eventPublisher;
        this.drinkRepository = drinkRepository;
    }

    @GetMapping("/myCart")
    public String showCart(Model model) {
        List<CartItem> cartItems = cartService.listCartItems();
        double totalPrice = cartService.getTotalPriceOfItemsInCart();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    @PostMapping("/removeOne")
    public String removeOne(@RequestParam("drinkId") long drinkId) {

        cartService.removeFromCart(drinkId, 1);
        return "redirect:/cart/myCart";
    }

    @PostMapping("/removeAll")
    public String removeAll(@RequestParam("drinkId") long drinkId) {

        cartService.removeAllFromCart(drinkId);
        return "redirect:/cart/myCart";
    }

    @PostMapping("/addOne")
    public String addItemToCart(@RequestParam("drinkId") long drinkId)
    {

        CartItem cartItem = new CartItem();
        Drink drink = drinkRepository.findById(drinkId).get();
        cartItem.setDrink( drink );
        cartItem.setQuantity(1);
        eventPublisher.publishEvent(new AddToCartEvent(this, cartItem));
        return "redirect:/cart/myCart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {

        long id = cartService.commitTransaction("cash");
        model.addAttribute("id", id);


        cartService.clearCart();

        return "paymentSuccess";
    }

}
