create table user_orders
(
    id              int not null auto_increment primary key,
    login           varchar(45),
    bookTitleOrder  varchar(80),
    bookAuthorOrder varchar(80),
    priceOrder      int
)
    engine = InnoDb;

