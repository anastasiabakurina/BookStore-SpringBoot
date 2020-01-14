package bookstore.service;

import bookstore.model.Book;
import bookstore.model.User;
import bookstore.model.UserOrders;
import bookstore.repo.BookRepo;
import bookstore.repo.UserOrdersRepo;
import bookstore.repo.UserRepo;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Service
public class BookService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserOrdersRepo userOrdersRepository;

    @Autowired
    private BookRepo bookRepository;

    public void saveUsersOrders(String bookTitle, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userFromDb = userRepo.findByUsername(username);
        model.addAttribute("username", username);

        String stringBooks = Arrays.asList(bookTitle).toString();
        String books = StringUtils.substringBetween(stringBooks, "[", "]");
        for (String oneBook : books.split(",")) {
            Book bookFind = bookRepository.findByBookTitle(oneBook);
            val userOrders = UserOrders.builder().bookTitleOrder(bookFind.getBookTitle())
                    .bookAuthorOrder(bookFind.getBookAuthor())
                    .priceOrder(bookFind.getPrice())
                    .name(username).mail(userFromDb.getMail()).build();
            userOrdersRepository.save(userOrders);
        }
    }

    public void booksFindAllPagination(Model model, @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Book> page = bookRepository.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/booksUser");

        int allPages = page.getTotalPages() - 1;
        model.addAttribute("allPages", allPages);
        int active = page.getNumber();
        if (active < allPages) {
            model.addAttribute("nextPage", active + 1);
        } else model.addAttribute("nextPage", "#");
        model.addAttribute("previousPage", active - 1);
        model.addAttribute("active", active);
    }
}
