package fi.academy.rest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @JsonIgnore // tarkistetaan vielä tarvitseeko
    @OneToMany(mappedBy = "user")
    private List<Ticket> userTickets;

    // salasanat ja muut Tonin ratkaisun mukaan

    //@Column(unique = true)
    @Id
    private String firebaseUserId;
    private String userRole;
    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
    private List<Course> courses;

    public User() {
        this.userRole = "student";
        this.userTickets = new ArrayList<>();
    }

    //FOR FIREBASE ACCOUNTS
    public User(String firebaseUserId, String username) {
        this.firebaseUserId = firebaseUserId;
        this.userRole = "student";
        this.userTickets = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.username = username;
    }

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addNewCourse(Course course) {
        if (this.courses.contains(course)) {
            return;
        }
        this.courses.add(course);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

