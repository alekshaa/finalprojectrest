package fi.academy.rest.Controller;

import fi.academy.rest.Entity.Ticket;
import fi.academy.rest.Repository.CourseRepository;
import fi.academy.rest.Repository.TicketRepository;
import fi.academy.rest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {


    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private TicketRepository ticketRepository;

    public ApiController(@Autowired CourseRepository courseRepository, @Autowired UserRepository userRepository, @Autowired TicketRepository ticketRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/tickets")
    public Iterable<?> getTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/tickets/{courseId}")
    public ResponseEntity<?> getCourseTickets(@PathVariable(name = "courseId") Integer courseId) {
        Optional<Ticket> tickets = ticketRepository.findById(courseId);
        if (!tickets.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tickets.get());
    }
}
