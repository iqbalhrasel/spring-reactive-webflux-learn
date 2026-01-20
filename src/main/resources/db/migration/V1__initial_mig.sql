create table categories(
    id serial,
    name varchar(255),
    primary key(id)
);

create table products(
    id serial,
    category_id integer,
    name varchar(255),
    price decimal(10,2),
    primary key(id),
    foreign key(category_id) references categories(id)
);