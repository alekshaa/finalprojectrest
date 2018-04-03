package fi.academy.rest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer userId;
    private String userName;

    @JsonIgnore // tarkistetaan vielä tarvitseeko
    @OneToMany (mappedBy = "user")
    private Ticket ticket;

    // salasanat ja muut Tonin ratkaisun mukaan

    public User() {
    }

    public User(String userName, Ticket ticket) {
        this.userName = userName;
        this.ticket = ticket;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
