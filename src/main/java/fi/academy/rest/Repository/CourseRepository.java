package fi.academy.rest.Repository;

import fi.academy.rest.Entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Query("SELECT c FROM Course c")
    List<Course> findAllToList();
}
