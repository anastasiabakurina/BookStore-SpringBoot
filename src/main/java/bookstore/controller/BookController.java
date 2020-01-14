package bookstore.controller;

import bookstore.model.Book;
import bookstore.model.User;
import bookstore.model.UserOrders;
import bookstore.repo.BookRepo;
import bookstore.repo.UserOrdersRepo;
import bookstore.repo.UserRepo;
import bookstore.service.BookService;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class BookController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserOrdersRepo userOrdersRepository;

    @Autowired
    private BookRepo bookRepository;

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"/", "/homePage"})
    public String home(@RequestParam(name = "name", required = false, defaultValue = "people") String name, Model model) {
        model.addAttribute("name", name);
        return "homePage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/booksAdmin")
    public String adminPage(Model model, @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        bookService.booksFindAllPagination(model, pageable);
        return "booksAdmin";
    }

    @GetMapping("/booksAdmin/add")
    public String addBook() {
        return "addBook";
    }

    @PostMapping("/booksAdmin/add")
    public String addBookPost(@RequestParam String bookTitle,
                              @RequestParam String bookAuthor, @RequestParam String price, @RequestParam String date,
                              Model model, @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        val book = Book.builder().bookTitle(bookTitle).bookAuthor(bookAuthor).price(price).date(date).build();
        bookRepository.save(book);
        Page<Book> books = bookRepository.findAll(pageable);
        model.addAttribute("books", books);
        return "redirect:/booksAdmin";
    }

    @GetMapping("/booksAdmin/remove/{id}")
    public String removeBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booksAdmin";
    }

    @GetMapping("/booksAdmin/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("books", bookRepository.findBookById(id));
        return "editBook";
    }

    @PostMapping("/booksAdmin/edit/{id}")
    public String editBookPost(@PathVariable("id") Long id, @ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booksAdmin";
    }

    @GetMapping("/booksUser")
    public String booksUser(Model model, @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        bookService.booksFindAllPagination(model, pageable);
        return "booksUser";
    }

    @PostMapping("/booksUser")
    public String bookUserOrdersSave(@RequestParam String bookTitle, Model model,
                                     @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        bookService.saveUsersOrders(bookTitle, model);
        model.addAttribute("made", "Order is made!");
        bookService.booksFindAllPagination(model, pageable);
        return "booksUser";
    }

    @GetMapping("/booksUser/userOrders")
    public String userOrders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Iterable<UserOrders> userOrders1 = userOrdersRepository.findAllByName(authentication.getName());
        model.addAttribute("userOrders", userOrders1);
        return "userOrders";
    }

    @GetMapping("/booksAdmin/userOrders")
    public String showUsersOrdersToAdmin(Model model, @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("url", "/booksUser");
        Page<UserOrders> userOrders = userOrdersRepository.findAll(pageable);
        model.addAttribute("userOrders", userOrders);
        return "showUsersOrdersToAdmin";
    }
}
