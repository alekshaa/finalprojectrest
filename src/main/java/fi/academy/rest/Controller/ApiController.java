package fi.academy.rest.Controller;

import fi.academy.rest.Entity.Ticket;
import fi.academy.rest.Repository.CourseRepository;
import fi.academy.rest.Repository.TicketRepository;
import fi.academy.rest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
    public ResponseEntity getCourseTickets(@PathVariable(name = "courseId") Integer courseId) {

        List<Ticket> tickets = ticketRepository.findByCourseId(courseId);
        if (tickets.size() ==0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/tickets/createTicket")
    public ResponseEntity createTicket(@RequestBody Ticket ticket) throws URISyntaxException {
        ticketRepository.save(ticket);
        URI location = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("api/tickets/{id}")
                .buildAndExpand(ticket.getTicketId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
