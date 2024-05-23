package vknue.javaweb.earthstore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vknue.javaweb.earthstore.models.Authority;
import vknue.javaweb.earthstore.models.Category;
import vknue.javaweb.earthstore.models.Drink;
import vknue.javaweb.earthstore.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final IDrinkRepository drinkRepository;
    private final IUserRepository userRepository;
    private final ICategoryRepository categoryRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DataLoader(IDrinkRepository drinkRepository, IUserRepository userRepository, ICategoryRepository categoryRepository) {
        this.drinkRepository = drinkRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadDrinks();
        loadUsers();


    }

 

    private void loadUsers() {

        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(passwordEncoder.encode("admin"));
        user1.setPrivilege("ADMIN");
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword(passwordEncoder.encode("user"));
        user2.setPrivilege("USER");

        userRepository.saveAll(
                Arrays.asList(user1, user2)
        );
    }

    private void loadDrinks() {
        List<Drink> drinks = new ArrayList<>();

        Category spirits = new Category("spirits");
        Category vodka = new Category("vodka");
        Category rum = new Category("rum");
        Category gin = new Category("gin");
        Category tequila = new Category("tequila");
        Category whiskey = new Category("whiskey");
        Category brandy = new Category("brandy");
        Category bourbon = new Category("bourbon");
        Category cognac = new Category("cognac");
        Category beer = new Category("beer");
        Category wine = new Category("wine");
        Category rakija = new Category("rakija");

        categoryRepository.saveAll(Arrays.asList(spirits, vodka, rum, gin, tequila, whiskey, brandy, bourbon, cognac, beer, wine,rakija));

        drinks.add(new Drink("Martini", "Cocktail", 15, 8.50, getOrCreateCategory("spirits"), "https://i.pinimg.com/originals/d9/3c/d3/d93cd3920386bc1203fe24b7bc0aa004.png"));
        drinks.add(new Drink("Mojito", "Cocktail", 15, 7.50, getOrCreateCategory("spirits"), "https://static.vecteezy.com/system/resources/previews/024/282/419/original/mojito-cocktail-watercolor-clipart-ai-generated-free-png.png"));
        drinks.add(new Drink("Cosmopolitan", "Cocktail", 15, 9.00, getOrCreateCategory("spirits"), "https://pngimg.com/d/cocktail_PNG110.png"));
        drinks.add(new Drink("Margarita", "Cocktail", 15, 8.00, getOrCreateCategory("spirits"), "https://i.pinimg.com/originals/75/29/40/75294085791920d59164b2bfd17cef53.png"));
        drinks.add(new Drink("Bloody Mary", "Cocktail", 10, 7.00, getOrCreateCategory("spirits"), "https://s3.amazonaws.com/newamsterdamspirits.com/mooseassets2/s3fs-public/NEWAM_BloodyMary_Mango_0.png?vZj.GE7v8qD4amKz3P2smu9nvadn3XzC"));
        drinks.add(new Drink("Piña Colada", "Cocktail", 10, 7.50, getOrCreateCategory("spirits"), "https://static.vecteezy.com/system/resources/previews/029/761/019/non_2x/homemade-cocktail-pina-colada-in-a-pub-ai-generative-free-png.png"));
        drinks.add(new Drink("Sex on the Beach", "Cocktail", 15, 8.50, getOrCreateCategory("spirits"), "https://www.saq.com/media/catalog/product/s/e/sex-on-the-beach-ec-1_1610405159.png?optimize=high&fit=bounds&height=&width=&format=jpeg"));
        drinks.add(new Drink("Tequila Sunrise", "Cocktail", 10, 8.00, getOrCreateCategory("spirits"), "https://static.vecteezy.com/system/resources/previews/034/922/908/original/ai-generated-glass-of-tequila-sunrise-cocktail-free-png.png"));
        drinks.add(new Drink("Daiquiri", "Cocktail", 15, 7.50, getOrCreateCategory("spirits"), "https://i.pinimg.com/originals/a1/58/7c/a1587c573db4cf4f84d2b7da8cb613bd.png"));
        drinks.add(new Drink("Long Island Iced Tea", "Cocktail", 20, 9.50, getOrCreateCategory("spirits"), "https://www.saq.com/media/catalog/product/l/o/long-island-iced-tea-ec-1_1610404239.png?optimize=high&fit=bounds&height=&width=&format=jpeg"));
        drinks.add(new Drink("Whiskey Sour", "Cocktail", 15, 8.00, getOrCreateCategory("spirits"), "https://en.cocktail.fabbri1905.com/imgpub/2034742/600/0/wiskey_sour.png"));
        drinks.add(new Drink("Blue Lagoon", "Cocktail", 10, 7.50, getOrCreateCategory("spirits"), "https://images.cocktailflow.com/v1/cocktail/w_300,h_540/cocktail_blue_lagoon-1.png"));
        drinks.add(new Drink("White Russian", "Cocktail", 15, 8.50, getOrCreateCategory("spirits"), "https://cocktail-story.com/wp-content/uploads/2019/04/9%D1%81.png"));
        drinks.add(new Drink("Gin and Tonic", "Cocktail", 10, 7.00, getOrCreateCategory("spirits"), "https://www.saq.com/media/catalog/product/g/i/gin-tonic-faible-alcool-ec-1_1698298383.png?optimize=high&fit=bounds&height=265&width=265&canvas=265:265&format=jpeg"));
        drinks.add(new Drink("Manhattan", "Cocktail", 15, 8.50, getOrCreateCategory("spirits"), "https://images.cocktailflow.com/v1/cocktail/w_300,h_540/cocktail_manhattan-1.png"));
        drinks.add(new Drink("Screwdriver", "Cocktail", 10, 7.00, getOrCreateCategory("spirits"), "https://icons.iconarchive.com/icons/miniartx/drinks/256/Cocktail-Screwdriver-Orange-icon.png"));
        drinks.add(new Drink("Sazerac", "Cocktail", 15, 8.00, getOrCreateCategory("spirits"), "https://assets.theblend.world/images/2019-11/cocktail-jim-beam-rye-sazerac_0.png"));
        drinks.add(new Drink("Tom Collins", "Cocktail", 10, 7.50, getOrCreateCategory("spirits"), "https://cocktailseeker.com/storage/recipes/tom%20collins.png"));
        drinks.add(new Drink("Old Fashioned", "Cocktail", 15, 8.50, getOrCreateCategory("spirits"), "https://www.thewhiskyexchange.com/media/rtwe/uploads/cocktail/17_CocktailImage.jpg?v=636741599425100000"));
        drinks.add(new Drink("Irish Coffee", "Cocktail", 15, 9.00, getOrCreateCategory("spirits"), "https://cdn.craft.cloud/224393fa-1975-4d80-9067-ada3cb5948ca/assets/detail_Irish_Coffee_2.png"));
        drinks.add(new Drink("Vodka", "Cocktail", 40, 20.00, getOrCreateCategory("vodka"), "https://i.pinimg.com/originals/98/ab/0c/98ab0cbad5b6503c5e1fdb7fb3f6849e.png"));
        drinks.add(new Drink("Rum", "Cocktail", 40, 20.00, getOrCreateCategory("rum"), "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Appleton_Estate_V-X_bottle.png/1200px-Appleton_Estate_V-X_bottle.png"));
        drinks.add(new Drink("Gin", "Cocktail", 40, 20.00, getOrCreateCategory("gin"), "https://upload.wikimedia.org/wikipedia/commons/a/af/Tanqueray_bottle_Gin.png"));
        drinks.add(new Drink("Tequila", "Cocktail", 40, 25.00, getOrCreateCategory("tequila"), "https://www.pngall.com/wp-content/uploads/6/Tequila-PNG.png"));
        drinks.add(new Drink("Whiskey", "Cocktail", 40, 25.00, getOrCreateCategory("whiskey"), "https://pngimg.com/d/whisky_PNG21.png"));
        drinks.add(new Drink("Brandy", "Cocktail", 40, 20.00, getOrCreateCategory("brandy"), "https://pngimg.com/d/bottle_PNG2077.png"));
        drinks.add(new Drink("Bourbon", "Cocktail", 40, 30.00, getOrCreateCategory("bourbon"), "https://shopbluebirddistilling.com/cdn/shop/files/SportsmanBourbon.png?v=1700583085"));
        drinks.add(new Drink("Cognac", "Cocktail", 40, 30.00, getOrCreateCategory("cognac"), "https://pngimg.com/d/cognac_PNG15148.png"));
        drinks.add(new Drink("Beer", "Beer", 5, 3.50, getOrCreateCategory("beer"), "https://static.vecteezy.com/system/resources/thumbnails/024/867/599/small_2x/beer-mug-with-foam-watercolor-hand-drawn-ai-generativev-png.png"));
        drinks.add(new Drink("Wine", "Wine", 13, 8.00, getOrCreateCategory("wine"), "https://png.pngtree.com/png-vector/20230902/ourmid/pngtree-wine-png-image_9862597.png"));

        drinkRepository.saveAll(drinks);
    }

    private Category getOrCreateCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            category = categoryRepository.save(category);
        }
        return category;
    }
}
