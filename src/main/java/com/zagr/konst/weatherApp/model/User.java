package com.zagr.konst.weatherApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
            // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
    //            message = "password should be stronger")
    private String password;

   // @NotEmpty
    @ManyToOne
    @JoinColumn(name="cityID",referencedColumnName = "cityID")
    private City cityID;

}
