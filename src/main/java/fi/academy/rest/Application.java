package fi.academy.rest;

import fi.academy.rest.Entity.Course;
import fi.academy.rest.Entity.Ticket;
import fi.academy.rest.Entity.User;
import fi.academy.rest.Repository.CourseRepository;
import fi.academy.rest.Repository.TicketRepository;
import fi.academy.rest.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    CommandLineRunner createDummydata(CourseRepository courseRepository, TicketRepository ticketRepository, UserRepository userRepository) {
//        return (args) -> {
//
//            Course course1 = new Course("Java-kurssi");
//            courseRepository.save(course1);
//            Course course2 = new Course("React-kurssi");
//            courseRepository.save(course2);
//
//            User user1 = new User("feikkiid1","Samu");
//            user1.addNewCourse(course1);
//            user1.addNewCourse(course2);
//            user1.addNewCourse(course1);
//            userRepository.save(user1);
//            User user2 = new User("feikkiid2","Tommi");
//            userRepository.save(user2);
//
//            Ticket ticket1 = new Ticket("Ongelma GIT pull:in kanssa",
//                    "Koitin pullata, mutta kävikin push. Pyrin korjaamaan asian force puillilla, mutta tein vahingossa mergen ja sain konfliktin aikaan.",
//                    "Utö");
//            ticket1.setCourse(course1);
//            ticket1.setUser(user1);
//            ticketRepository.save(ticket1);
//
//            ticket1 = new Ticket("En osaa käyttää Streameja",
//                    "Etsin googlesta apua, mutta en löytänyt. MInulla saattaa olla ongelma myön internetyhteyden kanssa.",
//                    "Suomen Leijona");
//            ticket1.setCourse(course1);
//            ticket1.setUser(user2);
//            ticketRepository.save(ticket1);
//
//            ticket1 = new Ticket("Tietokoneeni ei käynnisty",
//                    "Koitin painaa nappulaa todella lujaa. APUA!",
//                    "Suomen Leijona");
//            ticket1.setCourse(course2);
//            ticket1.setUser(user2);
//            ticketRepository.save(ticket1);
//
//            ticket1 = new Ticket("Minulla oli ongelma",
//                    "Minulla on aktiivinen ongelma",
//                    "Suomen Leijona");
//            ticket1.setCourse(course2);
//            ticket1.setUser(user1);
//            ticketRepository.save(ticket1);
//
//            ticket1 = new Ticket("Vanhin tiketti",
//                    "Wanhus",
//                    "Suomen Leijona");
//            ticket1.setCourse(course2);
//            ticket1.setUser(user1);
//            ticketRepository.save(ticket1);
//
//        };
//   }
}
