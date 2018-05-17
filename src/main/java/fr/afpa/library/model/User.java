package fr.afpa.library.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name="person_id")
public class User extends Person {

    private String email;
    private String password;
    private Role role;

    public User() {
        super();
    }

    @Email(regexp ="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z\\-\\.]+$" , message = "Incorrect email format")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 6, message = "Password must be at least 6 characters long")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
