package vknue.javaweb.earthstore.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import vknue.javaweb.earthstore.services.CartService;

@Component
public class AddToCartListener {

    private final CartService cartService;

    public AddToCartListener(CartService cartService) {
        this.cartService = cartService;
    }

    @EventListener
    public void onApplicationEvent(AddToCartEvent event) {
        cartService.addToCart(event.getCartItem());
    }
}