package fi.academy.rest;

import fi.academy.rest.Entity.Course;
import fi.academy.rest.Entity.Ticket;
import fi.academy.rest.Entity.User;
import fi.academy.rest.Repository.CourseRepository;
import fi.academy.rest.Repository.TicketRepository;
import fi.academy.rest.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @Before
    public void setup() {
            Course course1 = new Course("Java-kurssi");
            courseRepository.save(course1);
            Course course2 = new Course("React-kurssi");
            courseRepository.save(course2);

            User user1 = new User("Samu");
            userRepository.save(user1);
            User user2 = new User("Tommi");
            userRepository.save(user2);

            Ticket ticket1 = new Ticket("Ongelma GIT pull:in kanssa",
                    "Koitin pullata, mutta kävikin push. Pyrin korjaamaan asian force puillilla, mutta tein vahingossa mergen ja sain konfliktin aikaan.",
                    "Utö");
            ticket1.setCourse(course1);
            ticket1.setUser(user1);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("En osaa käyttää Streameja",
                    "Etsin googlesta apua, mutta en löytänyt. MInulla saattaa olla ongelma myön internetyhteyden kanssa.",
                    "Suomen Leijona");
            ticket1.setCourse(course1);
            ticket1.setUser(user2);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("Tietokoneeni ei käynnisty",
                    "Koitin painaa nappulaa todella lujaa. APUA!",
                    "Suomen Leijona");
            ticket1.setCourse(course2);
            ticket1.setUser(user2);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("Minulla oli ongelma",
                    "Minulla on aktiivinen ongelma",
                    "Suomen Leijona");
            ticket1.setCourse(course2);
            ticket1.setUser(user1);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("Vanhin tiketti",
                    "Wanhus",
                    "Suomen Leijona");
            ticket1.setCourse(course2);
            ticket1.setUser(user1);
            ticketRepository.save(ticket1);
    }

    @Test
    public void testListAllTickets() {
        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets",null,Ticket.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testListAllNotPassiveTickets() {
        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets/notpassive",null,Ticket.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testGetAllCourseTicketsFromExistingCourse() {
        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets/1",null,Ticket.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testGetTicketsFromNotExistingCourse() {
        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets/3",null,Ticket.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testCreateNewTicket() {
        Ticket ticket = new Ticket();
        ticket.setTicketTitle("Testauksen testaus");
        ticket.setTicketDescription("Yritetään saada testiä toimimaan");
        ticket.setLocation("Utö");

        ResponseEntity<Ticket> response = restTemplate.postForEntity("/api/tickets/createticket",ticket,Ticket.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//        String location = response.getHeaders().get("location").get(0);
//        String locationPolku = URI.create(location).getPath();
//        System.out.println(locationPolku);
//
//        response = restTemplate.getForEntity(locationPolku,Ticket.class);
//        assertEquals("Testauksen testaus",response.getBody().getTicketTitle());
//        assertEquals("Yritetään saada testiä toimimaan",response.getBody().getTicketDescription());
//        assertEquals("Utö",response.getBody().getLocation());
    }

    @Test
    public void testChangeTicketStatusToPassive() {
        ResponseEntity<?> response = restTemplate.exchange("/api/tickets/setpassive/6",HttpMethod.PUT,null,String.class);
        assertEquals("Ticket set as passive",response.getBody());
    }
}
