package bookstore.repo;

import bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//public interface UserRepo extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//}
@Repository

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
