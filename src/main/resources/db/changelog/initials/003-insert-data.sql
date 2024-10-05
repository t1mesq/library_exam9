
INSERT INTO authorities (name) VALUES
                                   ('USER'),
                                   ('ADMIN');

INSERT INTO categories (name) VALUES
                                  ('Фантастика'),
                                  ('Роман'),
                                  ('Детектив'),
                                  ('Научная литература');

INSERT INTO users (surname, name, patronymic, address, passport_number, password, reader_ticket_number, enabled) VALUES
                                                                                                                     ('Иванов', 'Иван', 'Иванович', 'г. Москва, ул. Ленина, д. 1', '123456', '$2a$12$qEPFg9WppuycYRdsIsu58O//EfHN8v6XUikl7.9c2rxCsF8Xf4Fuq', 'RT123456', true),
                                                                                                                     ('Петров', 'Петр', 'Петрович', 'г. Санкт-Петербург, ул. Невского, д. 2', '654321', '$2a$12$nQjGijtgYxopmSKHMhY6Ruyz2xCDdH8v1rGCM3PVTZvpA3vUyWxbC', 'RT654321', true),
                                                                                                                     ('Сидоров', 'Сидор', 'Сидорович', 'г. Казань, ул. Мира, д. 3', '111222', '$2a$12$waP6bmq6YWh4AbjkDonkge6dEpi0pq/Tw5b/3dwDY7jrDFcH1EZ3a', 'RT111222', true);

INSERT INTO books (title, author, category_id, image, available) VALUES
                                                                     ('1984', 'Джордж Оруэлл', 1, '1984.jpg', true),
                                                                     ('Мастер и Маргарита', 'Михаил Булгаков', 2, 'master_and_margarita.jpg', true),
                                                                     ('Убийство в Восточном экспрессе', 'Агата Кристи', 3, 'murder_on_the_orient_express.jpg', true),
                                                                     ('Краткая история времени', 'Стивен Хокинг', 4, 'brief_history_of_time.jpg', true);

INSERT INTO book_requests (reader_ticket_number, book_id, user_id, return_date) VALUES
                                                                                    ('RT123456', 1, 1, '2024-10-10'),
                                                                                    ('RT654321', 2, 2, '2024-10-15'),
                                                                                    ('RT111222', 3, 3, '2024-10-20');
