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
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private TicketRepository ticketRepository;

    public TicketController(@Autowired CourseRepository courseRepository, @Autowired UserRepository userRepository, @Autowired TicketRepository ticketRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    // LIST ALL TICKETS
    @GetMapping("/tickets")
    public Iterable<?> getTickets() {
        return ticketRepository.findAll();
    }

    // LIST ALL TICKETS FROM DEFINED COURSE
    @GetMapping("/tickets/{courseId}")
    public ResponseEntity getCourseTickets(@PathVariable(name = "courseId") Integer courseId) {

        List<Ticket> tickets = ticketRepository.findByCourseId(courseId);
        if (tickets.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tickets);
    }

    // CREATE NEW TICKET
    @PostMapping("/tickets/createticket")
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

    // MAKE TICKET PASSIVE AKA. REMOVE TICKET FROM ACTIVE STATE
    @Transactional
    @PutMapping("/tickets/setpassive/{ticketId}")
    public ResponseEntity<?> setTicketAsPassive(@PathVariable(name = "ticketId") Integer ticketId) {
        ticketRepository.setPassive(ticketRepository.findById(ticketId).get());
        return ResponseEntity.ok("Ticket set as passive");
    }
}
