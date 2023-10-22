drop table if exists products cascade;
drop table if exists categories cascade;
drop sequence if exists products_seq cascade;

create sequence if not exists products_seq
    start with 1 increment by 10;

create table if not exists products
(
    code        bigint not null,
    description varchar(255),
    name        varchar(255),
    primary key (code)
);

create table if not exists categories
(
    product_id bigint       not null,
    category   varchar(255) not null,
    primary key (product_id, category),
    constraint categories_fk foreign key (product_id) references products
);

insert into products (code, name, description)
values (nextval('products_seq'), 'Product 1', 'Description of Product 1'),
       (nextval('products_seq'), 'Product 2', 'Description of Product 2');

insert into categories (product_id, category)
values (1, 'Category 1'),
       (11, 'Category 2');
