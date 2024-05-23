package vknue.javaweb.earthstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;
    private double alcoholPercentage;
    private double price;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    private String pictureUrl;

    public Drink(String name, String type, double alcoholPercentage, double price, Category category, String pictureUrl) {
        this.name = name;
        this.type = type;
        this.alcoholPercentage = alcoholPercentage;
        this.price = price;
        this.category = category;
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", alcoholPercentage=" + alcoholPercentage +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }

}
