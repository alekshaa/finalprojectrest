package fi.academy.rest.Entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue // autoincrement
    private Integer ticketId;
    private LocalDateTime timestamp;
    //    private Integer courseId;
    //    private String courseName;
    private String ticketTitle;
    private String ticketDescription;
    private String ticketStatus;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    public Ticket() {
        this.timestamp = LocalDateTime.now();
        this.ticketStatus = "active";
    }

    public Ticket(String ticketTitle, String ticketDescription, String location) {
        this.timestamp = LocalDateTime.now();
        this.ticketStatus = "active";
        this.user = user;
        this.course = course;

        this.ticketTitle = ticketTitle;
        this.ticketDescription = ticketDescription;
        this.location = location;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

