package fi.academy.rest.Controller;

import fi.academy.rest.Entity.Course;
import fi.academy.rest.Entity.Ticket;
import fi.academy.rest.Entity.User;

public class RequestWrapper {
    Ticket ticket;
    User user;
    Course course;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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
