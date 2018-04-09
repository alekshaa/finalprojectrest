package fi.academy.rest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Integer courseId;
    private String courseName;

    @JsonIgnore // tarkistetaan vielä tarvitseeko
    @OneToMany(mappedBy = "course")
    private List<Ticket> courseTickets;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<User> user;

    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
        this.courseTickets  = new ArrayList<>();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Ticket> getCourseTickets() {
        return courseTickets;
    }

    public void setCourseTickets(List<Ticket> courseTickets) {
        this.courseTickets = courseTickets;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
