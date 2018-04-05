package fi.academy.rest;

import fi.academy.rest.Entity.Ticket;
import fi.academy.rest.Repository.TicketRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testListAllTickets() {
        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets/",null,Ticket.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testListAllNotPassiveTickets() {
        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets/notpassive/",null,Ticket.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testGetAllCourseTicketsFromExistingCourse() {
        ResponseEntity<Ticket> response = restTemplate.getForEntity("/api/tickets/2",null,Ticket.class);
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
    }

    @Test
    public void testChangeTicketStatusToPassive() {
        ResponseEntity<?> response = restTemplate.exchange("/api/tickets/setpassive/5",HttpMethod.PUT,null,String.class);
        assertEquals("Ticket set as passive",response.getBody());
    }
}
