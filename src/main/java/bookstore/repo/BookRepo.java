package bookstore.repo;

import bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);
    Book findBookById(Long id);
    Book findByBookTitle(String bookTitle);
//   List<Book> findByBookTitle(String bookTitle);
//    Book findById(Long id);
}