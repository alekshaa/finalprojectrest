package fi.academy.rest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private List<Ticket> courseTickets = new ArrayList<>();

    public Course() {
    }

    public Course(String courseName, List<Ticket> courseTickets) {
        this.courseName = courseName;
        this.courseTickets = courseTickets;
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
}
