package fi.academy.rest.Repository;

import fi.academy.rest.Entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
