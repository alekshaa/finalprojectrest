package fi.academy.rest.Controller;

import fi.academy.rest.Entity.Course;
import fi.academy.rest.Entity.Ticket;
import fi.academy.rest.Entity.User;
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
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity getTickets() {
        setOldestTicketActiveToAllCourses();
        return ResponseEntity.ok(ticketRepository.findAll());
    }

    // LIST ALL TICKETS
    @GetMapping("/tickets/notpassive")
    public Iterable<?> getNotPassiveTickets() {

        setOldestTicketActiveToAllCourses();
        return ticketRepository.findAllNotPassiveTickets();
    }

    // LIST ALL TICKETS FROM DEFINED COURSE
    @GetMapping("/tickets/{courseId}")
    public ResponseEntity getCourseTickets(@PathVariable(name = "courseId") Integer courseId) {

        List<Ticket> tickets = ticketRepository.findByCourseId(courseId);
        if (tickets.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        setOldestTicketActiveToAllCourses();
        return ResponseEntity.ok(tickets);
    }

    // CREATE NEW TICKET
    @PostMapping("/tickets/createticket")
//    public ResponseEntity createTicket(@RequestBody Ticket ticket) throws URISyntaxException {
    public ResponseEntity createTicket(@RequestBody RequestWrapper requestWrapper) throws URISyntaxException {

        Ticket ticket = requestWrapper.getTicket();
        User user = requestWrapper.getUser();
        Course course = courseRepository.findByName(requestWrapper.getCourse().getCourseName()); // hakee kurssinimellä toistaiseksi, vaihtaa ID:ksikin...

        // ONGELMIA TULEE JOS KURSSIA TAI KÄYTTÄJÄÄ EI OLE LUOTU ENNEN KUN TIKETTIÄ LUODAAN!
        ticket.setUser(user);
        ticket.setCourse(course);
        ticketRepository.save(ticket);


//        //ticket.setUser(userRepository.findById(ticket.getApuUser()));
//        Integer apuInteger = ticket.getApuUser();
//        User testi = userRepository.findById();
//        ticket.setUser();
//        ticketRepository.save(ticket);

        URI location = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("api/tickets/{id}")
                .buildAndExpand(ticket.getTicketId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // MAKE TICKET PASSIVE AKA. REMOVE TICKET FROM ACTIVE OR QUEUE STATE
    @Transactional
    @PutMapping("/tickets/setpassive/{ticketId}")
    public ResponseEntity<?> setTicketAsPassive(@PathVariable(name = "ticketId") Integer ticketId) {

        // if there is no ticket, return 404
        if (!ticketRepository.findById(ticketId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        ticketRepository.setPassive(ticketRepository.findById(ticketId).get());
        return ResponseEntity.ok("Ticket set as passive");
    }
    // SET TICKET STATUS TO ACTIVE
    @Transactional
    @PutMapping("/tickets/setactive/{ticketId}")
    public ResponseEntity<?> setTicketAsActive(@PathVariable(name = "ticketId") Integer ticketId) {
        ticketRepository.setActive(ticketRepository.findById(ticketId).get());
        return ResponseEntity.ok("Ticket set as active");
    }

    //SET THE ACTIVE status to oldest ticket in the course that is not passive
    public void setOldestTicketActiveToAllCourses() {

        // Get all courses
        List<Course> allCourses = courseRepository.findAllToList();

        for (int c = 0; c < allCourses.size(); c++) {
            Course course = allCourses.get(c);

            // Get all tickets in queue from that course
            List<Ticket> tickets = ticketRepository.findNotPassiveTicketsByCourseId(course.getCourseId());

            // Loop trough tickets and find the latest in queue
            if (tickets.size() > 0) {
                Ticket latest = tickets.get(0);
                for (int i = 0; i < tickets.size(); i++) {
                    if (tickets.get(i).getTimestamp().isBefore(latest.getTimestamp()) || tickets.get(i).getTimestamp().isEqual(latest.getTimestamp())) {
                        latest = tickets.get(i);
                    }
                }
                // save the active status of the ticket
                latest.setTicketStatus("active");
                ticketRepository.save(latest);
            }
        }
    }


}

