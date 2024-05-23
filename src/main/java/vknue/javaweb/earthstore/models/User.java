package vknue.javaweb.earthstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="\"user\"" )
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User() {
    }

    private String username;
    private String password;
    private String privilege;


    public User(String username, String password, String privilege) {
        this.username = username;
        this.password = password;
        this.privilege = privilege;
    }



}