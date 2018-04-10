package fi.academy.rest.Controller;

import fi.academy.rest.Entity.Course;
import fi.academy.rest.Entity.Ticket;
import fi.academy.rest.Entity.User;
import fi.academy.rest.Repository.CourseRepository;
import fi.academy.rest.Repository.TicketRepository;
import fi.academy.rest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private TicketRepository ticketRepository;

    public UserController(@Autowired CourseRepository courseRepository, @Autowired UserRepository userRepository, @Autowired TicketRepository ticketRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    // LIST ALL USERS
    @GetMapping("/users")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // CREATE NEW USER
    @PostMapping("/users/createuser")
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {

        // if there allready is a user - do nothing
        Optional<User> isThereAUser = userRepository.findById(user.getFirebaseUserId());
        if (isThereAUser.isPresent()) {
            return ResponseEntity.ok("User allready exists");
        }

        userRepository.save(user);
        URI location = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("api/users/{id}")
                .buildAndExpand(user.getFirebaseUserId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //ADD NEW COURSE TO USER
    @PutMapping("/users/addcourse/{user}")
    public ResponseEntity addCourse(@PathVariable(name = "user") String user, @RequestBody Course course) {
        if (courseRepository.findByName(course.getCourseName()) != null) {
            Course c = courseRepository.findByName(course.getCourseName());
            User u = userRepository.findById(user).get();
            System.out.println(c.getCourseName());
            System.out.println(u.getFirebaseUserId());
            u.addNewCourse(c);
            System.out.println(u.getCourses().get(0).getCourseName());
            userRepository.save(u);
            return ResponseEntity.ok("1 new course");
        }
        return ResponseEntity.notFound().build();
    }

    // GET USER BY ID
    @GetMapping("/users/{userId}")
    public ResponseEntity getUsersById(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(userRepository.findById(userId));
    }

    // TOGGLE USER ROLE
    @PutMapping("/users/togglerole/{userId}")
    public ResponseEntity toggleUserRole(@PathVariable(name = "userId") String userId) {

        // if there is no user return not found
        if (!userRepository.findById(userId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // else toggle user role
        User user = userRepository.findById(userId).get();

        if (user.getUserRole().equals("student")) {
            user.setUserRole("teacher");
            System.out.println("Vaihdettu teacheriksi");
        } else {
            user.setUserRole("student");
            System.out.println("Vaihdettu studentiksi");

        }
        userRepository.save(user);

        return ResponseEntity.ok("User role changed");
    }

}
