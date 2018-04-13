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



    @Bean
    CommandLineRunner createDummydata(CourseRepository courseRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        return (args) -> {

            Course course1 = new Course("javak18");
            courseRepository.save(course1);
            Course course2 = new Course("csharpk18");
            courseRepository.save(course2);

            User user1 = new User("uediACnXUXezVoHjJIrpzqXoQfU2","admin");
            user1.addNewCourse(course1);
            user1.addNewCourse(course2);
            user1.setUserRole("teacher");
            userRepository.save(user1);

            User user2 = new User("fakeID2","Sami Nykänen");
            userRepository.save(user2);
            User user3 = new User("fakeID3","Veli-Pekka Nurmi");
            userRepository.save(user3);
            User user4 = new User("fakeID4","Satu Ylikoski");
            userRepository.save(user4);
            User user5 = new User("fakeID5","Toni Sarro");
            userRepository.save(user5);

            Ticket ticket1 = new Ticket("Issues with GIT pull!",
                    "Tried to pull, but ended up pushing. Is fixing it with force push a good idea? Help!",
                    "Utö");
            ticket1.setCourse(course1);
            ticket1.setUser(user2);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("Streams are not working, lambda expression issues.",
                    "Tried to google the solution without success. Don't know which way the arrow should be pointing!",
                    "Suomen Leijona");
            ticket1.setCourse(course1);
            ticket1.setUser(user3);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("Run from cmd?",
                    "I can't remember how to get my program to run from cmd? Need help ASAP! Presentation in 10min!",
                    "Classroom");
            ticket1.setCourse(course1);
            ticket1.setUser(user2);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("NullPointerException that i can't solve",
                    "There should not be any empty index, but I still get this error. Tried to google it, but no result.",
                    "Porkkala");
            ticket1.setCourse(course1);
            ticket1.setUser(user3);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("I have a problem creating a .jar file!",
                    "I don't know how to make a .jar file with IntelliJ. I need help now!",
                    "Suomen Leijona");
            ticket1.setCourse(course1);
            ticket1.setUser(user4);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("Lambda is not a function- message",
                    "This happens when the handler is within serverless.yml.",
                    "Porkkala");
            ticket1.setCourse(course1);
            ticket1.setUser(user4);
            ticketRepository.save(ticket1);

            ticket1 = new Ticket("My computer is dead. Is C# the reason for this?",
                    "Coded with C# and all of the sudden everything died. Don't know what to do! Is there a 'undo everything'-command in this language?",
                    "Porkkala");
            ticket1.setCourse(course2);
            ticket1.setUser(user5);
            ticketRepository.save(ticket1);

        };
   }
}
