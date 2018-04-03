package fi.academy.rest.Repository;

import fi.academy.rest.Entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.course.id = :courseId")
    List<Ticket> findByCourseId(@Param("courseId") Integer courseId);

}
