package fi.academy.rest.Controller;

import fi.academy.rest.Entity.Course;
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
public class CourseController {

    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private TicketRepository ticketRepository;

    public CourseController(@Autowired CourseRepository courseRepository, @Autowired UserRepository userRepository, @Autowired TicketRepository ticketRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    // LIST ALL COURSES
    @GetMapping("/courses")
    public ResponseEntity getCourses() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    // CREATE NEW COURSE
    @PostMapping("/courses/createcourse")
    public ResponseEntity createTicket(@RequestBody Course course) throws URISyntaxException {
        if (courseRepository.findByName(course.getCourseName()) == null) {
            courseRepository.save(course);
            URI location = UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host("localhost")
                    .port(8080)
                    .path("api/courses/{id}")
                    .buildAndExpand(course.getCourseId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.ok("This course already exists");
    }

}
