package vknue.javaweb.earthstore.models.cart;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> cart = new ArrayList<>();
    private double TotalPrice = 0;
}
