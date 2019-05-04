package no.oslomet.loginmicroservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roles;
    private String address;
    private long postalCode;
    private String postPlace;

    public User(String firstName, String lastName, String email, String password, String roles, String address, long postalCode, String postPlace) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.address = address;
        this.postalCode= postalCode;
        this.postPlace = postPlace;
    }
}


