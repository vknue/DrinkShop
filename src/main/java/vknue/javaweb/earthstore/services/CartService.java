package vknue.javaweb.earthstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vknue.javaweb.earthstore.models.Drink;
import vknue.javaweb.earthstore.models.Transaction;
import vknue.javaweb.earthstore.models.TransactionItem;
import vknue.javaweb.earthstore.models.User;
import vknue.javaweb.earthstore.models.cart.Cart;
import vknue.javaweb.earthstore.models.cart.CartItem;
import vknue.javaweb.earthstore.repositories.IDrinkRepository;
import vknue.javaweb.earthstore.repositories.ITransactionItemRepository;
import vknue.javaweb.earthstore.repositories.ITransactionRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {


    private final Cart cart;


    @Autowired
    public CartService(Cart cart, UserDetailService userDetailService, IDrinkRepository drinkRepository, ITransactionRepository transactionRepository, ITransactionItemRepository transactionItemRepository) {
        this.cart = cart;
        this.userDetailService = userDetailService;
        this.drinkRepository = drinkRepository;
        this.transactionRepository = transactionRepository;
        this.transactionItemRepository = transactionItemRepository;
    }

    private final UserDetailService userDetailService;
    private final IDrinkRepository drinkRepository;
    private final ITransactionRepository transactionRepository;
    private final ITransactionItemRepository transactionItemRepository;

    public void addToCart(CartItem cartItem) {

        boolean exists = false;

        for (CartItem ci : cart.getCart()) {
            if (Objects.equals(ci.getDrink().getId(), cartItem.getDrink().getId())) {
                exists = true;
                break;
            }
        }

        if (exists) {
            cart.getCart().forEach(ci -> {
                if (Objects.equals(ci.getDrink().getId(), cartItem.getDrink().getId())) {
                    ci.setQuantity(ci.getQuantity() + cartItem.getQuantity());
                    ci.setTotalPrice(ci.getQuantity() * ci.getDrink().getPrice());
                }
            });
        } else {
            cartItem.setTotalPrice(cartItem.getDrink().getPrice());
            cart.getCart().add(cartItem);
        }

        cart.setTotalPrice(getTotalPriceOfItemsInCart());

    }

    public List<CartItem> listCartItems() {
        return cart.getCart();
    }



    public double getTotalPriceOfItemsInCart() {

        double price = 0;


        for (CartItem cartItem : cart.getCart()) {
            price += cartItem.getDrink().getPrice() * cartItem.getQuantity();
        }

        return price;
    }

    public String getProductNamesInCsv() {

        String productNames = "";
        for (CartItem cartItem : cart.getCart()) {
            productNames += cartItem.getDrink().getName() + ", ";
        }

        return productNames;
    }


    public void removeFromCart(long itemId, int quantity) {
        Iterator<CartItem> iterator = cart.getCart().iterator();
        while (iterator.hasNext()) {
            CartItem ci = iterator.next();
            if (ci.getDrink().getId() == itemId) {
                if (ci.getQuantity() > quantity) {
                    ci.setQuantity(ci.getQuantity() - quantity);
                    ci.setTotalPrice(ci.getQuantity() * ci.getDrink().getPrice());
                } else {
                    iterator.remove();
                }
            }
        }
        cart.setTotalPrice(getTotalPriceOfItemsInCart());
    }

    public void removeAllFromCart(long itemId) {
        Iterator<CartItem> iterator = cart.getCart().iterator();
        while (iterator.hasNext()) {
            CartItem ci = iterator.next();
            if (ci.getDrink().getId() == itemId) {
                    iterator.remove();
            }
        }
        cart.setTotalPrice(0.0);
    }





    public void clearCart() {
        cart.getCart().clear();
    }


    public long commitTransaction(String type) {

        User user = new User();
        long id = userDetailService.GetUsersIdByName();
        String username = userDetailService.getUserName();
        user.setId(id);
        user.setUsername(username);


        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(type);
        transaction.setPurchaseDate(new java.util.Date());
        transaction.setItems(new ArrayList<>());
        transaction.setFinalPrice(cart.getTotalPrice());
        transactionRepository.save(transaction);
        for (CartItem cartItem : cart.getCart()) {

            Drink drink = new Drink();

            drinkRepository.findById(cartItem.getDrink().getId()).ifPresent(i -> {
                drink.setId(i.getId());
                drink.setName(i.getName());
                drink.setCategory(i.getCategory());
            });

            TransactionItem transactionItem = new TransactionItem();
            transactionItem.setTransaction(transaction);


            transactionItem.setDrink(drink);
            transactionItem.setQuantity(cartItem.getQuantity());
            transaction.getItems().add(transactionItem);

            transactionItemRepository.save(transactionItem);
        }
        clearCart();
        return transaction.getId();
    }

}