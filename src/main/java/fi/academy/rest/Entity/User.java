package fi.academy.rest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    //@GeneratedValue
    //private Integer userId;
    //private String userName;

    @JsonIgnore // tarkistetaan vielä tarvitseeko
    @OneToMany(mappedBy = "user")
    private List<Ticket> userTickets;

    // salasanat ja muut Tonin ratkaisun mukaan

    //@Column(unique = true)
    @Id
    private String firebaseUserId;
    private String userRole;

    public User() {
        this.userRole = "student";
        this.userTickets = new ArrayList<>();
    }

//    public User(String userName) {
//        this.userName = userName;
//        this.userTickets = new ArrayList<>();
//    }

    //FOR FIREBASE ACCOUNTS
    public User(String firebaseUserId) {
        this.firebaseUserId = firebaseUserId;
        this.userRole = "student";
        this.userTickets = new ArrayList<>();
    }

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    public List<Ticket> getUserTickets() {
        return userTickets;
    }

    public void setUserTickets(List<Ticket> userTickets) {
        this.userTickets = userTickets;
    }

    public String getFirebaseUserId() {
        return firebaseUserId;
    }

    public void setFirebaseUserId(String firebaseUserId) {
        this.firebaseUserId = firebaseUserId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firebaseUserId, user.firebaseUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firebaseUserId);
    }
}
