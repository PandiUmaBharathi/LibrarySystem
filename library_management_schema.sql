CREATE DATABASE library_management;
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    year INT,
    quantity INT
);
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(15)
);
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    book_id INT,
    issue_date DATE,
    return_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
SHOW TABLES;
INSERT INTO books (title, author, publisher, year, quantity) 
VALUES ('Java Programming', 'John Doe', 'TechPublisher', 2023, 10);
-- Insert into users
INSERT INTO users (name, email, phone)
VALUES ('John Doe', 'john.doe@example.com', '9876543210');
-- Insert into transactions (assuming user_id = 2 and book_id = 1)
INSERT INTO transactions (user_id, book_id, issue_date)
VALUES (2, 1, NOW());
SELECT * FROM users;
INSERT INTO transactions (user_id, book_id, issue_date)
VALUES (1, 1, NOW());
INSERT INTO transactions (user_id, book_id, issue_date)
VALUES (1, 1, NOW());


