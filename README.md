# Library Management System

- Design and implement a Library Management System 

## User management
  - user authentication: login system
  - role management

## Catalog Management
  - Add book: add new book to the catalog
  - Search books: search for books by title, author, pub date, category
  - List books: Display all books in the catalog (with options to filter)

```roomsql
CREATE TABLE member (
    member_id BIGSERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(258) NOT NULL,
    last_name VARCHAR(258) NOT NULL,
    joined_date DATE NOT NULL,
    active_status VARCHAR(20) NOT NULL,
    member_role VARCHAR(10) NOT NULL,
    library_number VARCHAR(8) NOT NULL
);
```

```roomsql
CREATE TABLE author (
    author_id UUID PRIMARY KEY NOT NULL,
    first_name VARCHAR(258) NOT NULL,
    last_name VARCHAR(258) NOT NULL
); 
```

```roomsql
CREATE TABLE category (
    category_id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(258) NOT NULL
);
```

```roomsql
CREATE TABLE book (
    book_id UUID PRIMARY KEY NOT NULL,
    title VARCHAR(258) NOT NULL,
    publication_date DATE NOT NULL,
    copies_owned BIGINT NOT NULL,
    category_id BIGINT REFERENCES category (category_id)  
);
```

```roomsql
CREATE TABLE book_author (
    book_id UUID REFERENCES book (book_id),
    author_id UUID REFERENCES author (author_id)
);
```

```roomsql
-- testing the join of book table and category table
SELECT 
    *
FROM book b
INNER JOIN category c
    ON b.category_id = c.category_id;    
```


```roomsql
SELECT 
    a.author_id, a.first_name, a.last_name
FROM author a
INNER JOIN book_author ba
    ON a.author_id = ba.author_id
WHERE ba.book_id = '5a49a3dd-8144-4c52-bd3a-e16ffebe9083';
```

