package vknue.javaweb.earthstore.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "transaction_item")
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;

    @Column(name = "quantity")
    private Integer quantity;

    public TransactionItem(Transaction transaction, Drink drink, Integer quantity) {
        this.transaction = transaction;
        this.drink = drink;
        this.quantity = quantity;
    }

    public TransactionItem() {
    }


}