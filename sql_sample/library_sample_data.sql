-- LANGUAGES
INSERT INTO languages (name) VALUES ('EN');
INSERT INTO languages (name) VALUES ('AR');

-- PUBLISHERS
INSERT INTO publishers (name, address, contact_email) VALUES ('O\'Reilly Media', '1005 Gravenstein Hwy N', 'info@oreilly.com');
INSERT INTO publishers (name, address, contact_email) VALUES ('Dar Al-Maaref', 'Cairo, Egypt', 'contact@daralmaaref.eg');

-- CATEGORIES
INSERT INTO categories (name, parent_id) VALUES ('Technology', NULL);
INSERT INTO categories (name, parent_id) VALUES ('Programming', 1);

-- AUTHORS
INSERT INTO authors (name, bio) VALUES ('Martin Fowler', 'Expert in software architecture and design.');
INSERT INTO authors (name, bio) VALUES ('Robert C. Martin', 'Known for clean code practices.');

-- BOOKS
INSERT INTO books (
    title, isbn, edition, publication_year, summary, cover_image_url, language_id, publisher_id
) VALUES (
    'Refactoring', '9780201485677', '2nd', 2018, 'Refactoring existing code.', NULL, 1, 1
);

INSERT INTO books (
    title, isbn, edition, publication_year, summary, cover_image_url, language_id, publisher_id
) VALUES (
    'Clean Code', '9780132350884', '1st', 2008, 'A handbook of Agile software craftsmanship.', NULL, 1, 1
);

-- BOOK_AUTHORS
INSERT INTO book_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO book_authors (book_id, author_id) VALUES (2, 2);

-- BOOK_CATEGORIES
INSERT INTO book_categories (book_id, category_id) VALUES (1, 2);
INSERT INTO book_categories (book_id, category_id) VALUES (2, 2);

-- MEMBERS
INSERT INTO members (name, email, phone, address) VALUES ('abdo hussien', 'ahmed@example.com', '0123456789', 'Nasr City, Cairo');

-- USERS
INSERT INTO users (username, email, password, is_active) VALUES ('admin', 'admin@lib.com', 'hashed_pass', 1);
INSERT INTO users (username, email, password, is_active) VALUES ('librarian', 'lib@lib.com', 'hashed_pass', 1);

-- ROLES
INSERT INTO roles (name) VALUES ('ADMINISTRATOR');
INSERT INTO roles (name) VALUES ('LIBRARIAN');

-- USERS_ROLES
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- admin -> ADMINISTRATOR
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- librarian -> LIBRARIAN

-- BORROWINGS
INSERT INTO borrowings (member_id, book_id, borrow_date, due_date, return_date, status)
VALUES (1, 1, TO_DATE('2025-07-01', 'YYYY-MM-DD'), TO_DATE('2025-07-15', 'YYYY-MM-DD'), NULL, 'BORROWED');

-- USER_LOG
INSERT INTO user_log (username, ip_address, action_type, details)
VALUES ('admin', '192.168.1.1', 'LOGIN', 'Admin logged in');
