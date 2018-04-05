package fi.academy.rest.Repository;

import fi.academy.rest.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
