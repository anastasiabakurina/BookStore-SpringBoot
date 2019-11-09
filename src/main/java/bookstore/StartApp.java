package bookstore;

//import org.hibernate.cfg.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//@EnableJpaRepositories(repositoryFactoryBeanClass= UserOrdersImpl.class, repositoryBaseClass = ThisMyClass.class)
@SpringBootApplication
//@ComponentScan(bookstore)
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

}
