package bookstore.repo;

import bookstore.model.Book;
import bookstore.model.UserOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrdersRepo extends CrudRepository<UserOrders, Long> {
//    UserOrders findByName(String name);
    Iterable<UserOrders> findAllByName(String name);
//    Page<UserOrders> findAllByName(String name, Pageable pageable);
    Page<UserOrders> findAll(Pageable pageable);
}