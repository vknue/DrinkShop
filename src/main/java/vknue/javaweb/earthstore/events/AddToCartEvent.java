package vknue.javaweb.earthstore.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import vknue.javaweb.earthstore.models.cart.CartItem;

@Getter
@Setter
public class AddToCartEvent extends ApplicationEvent {
    private CartItem cartItem;

    public AddToCartEvent(Object source, CartItem cartItem) {
        super(source);
        this.cartItem = cartItem;
    }

}