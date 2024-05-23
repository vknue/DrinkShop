package vknue.javaweb.earthstore.models.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vknue.javaweb.earthstore.models.Drink;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private Drink drink;
    private int quantity;
    private double totalPrice;
    @Id
    private Long id;

    @Override
    public String toString() {
        return "CartItem [drink=" + drink + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
    }


}
