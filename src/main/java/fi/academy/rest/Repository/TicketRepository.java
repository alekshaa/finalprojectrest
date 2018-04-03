package fi.academy.rest.Repository;

import fi.academy.rest.Entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.course.id = :courseId")
    List<Ticket> findByCourseId(@Param("courseId") Integer courseId);
}
