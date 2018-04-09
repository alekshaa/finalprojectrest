package fi.academy.rest.Repository;

import fi.academy.rest.Entity.Ticket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.course.id = :courseId")
    List<Ticket> findByCourseId(@Param("courseId") Integer courseId);

    @Query("SELECT t FROM Ticket t WHERE t.course.courseName = :courseName")
    List<Ticket> findByCourseName(@Param("courseName") String courseName);

    @Query("SELECT t FROM Ticket t WHERE t.ticketStatus ='queue' OR t.ticketStatus = 'active'")
    List<Ticket> findAllNotPassiveTickets();

    @Modifying
    @Query("UPDATE Ticket t SET t.ticketStatus = 'passive' WHERE t = :ticket")
    public void setPassive(@Param("ticket") Ticket ticket);

    @Modifying
    @Query("UPDATE Ticket t SET t.ticketStatus = 'active' WHERE t = :ticket")
    public void setActive(@Param("ticket") Ticket ticket);


    @Query("SELECT t FROM Ticket t WHERE t.course.id = :courseId AND t.ticketStatus ='queue' OR t.ticketStatus = 'active'")
    List<Ticket> findNotPassiveTicketsByCourseId(@Param("courseId") Integer courseId);
}
