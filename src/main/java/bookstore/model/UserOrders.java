package bookstore.model;

import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import javax.persistence.*;

@EnableJpaRepositories
@Entity
@Table(name = "user_orders")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserOrders {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "book_title_order")
    private String bookTitleOrder;

    @Column(name = "book_author_order")
    private String bookAuthorOrder;

    @Column(name = "price_order")
    private String priceOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;
}
