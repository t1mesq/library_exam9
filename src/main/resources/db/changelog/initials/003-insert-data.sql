INSERT INTO authorities (name) VALUES ('USER');
INSERT INTO authorities (name) VALUES ('ADMIN');


INSERT INTO users (surname, name, patronymic, address, passport_number, password, reader_ticket_number, enabled, authority_id)
VALUES
    ('Иванов', 'Иван', 'Иванович', 'Москва, ул. Пушкина, д. 1', '1234567890', '$2a$12$OUfWztvM.3gK4zfdXPacs.3SXx7R09qACsKGqeNkFNVIix7sgtU/u', 'RTN123456', true, 1), -- USER
    ('Петров', 'Петр', 'Петрович', 'Санкт-Петербург, ул. Ленина, д. 2', '0987654321', '$2a$12$sk2Z8ogmhD97GJPpBvctYu7sHgIIcSINd.uwpFBwPwju7VFxDJTVW', 'RTN654321', true, 1), -- USER
    ('Сидоров', 'Сидор', 'Сидорович', 'Екатеринбург, ул. Чехова, д. 3', '2345678901', '$2a$12$r9cPB5qmTsJMky0bEmFN5.9YKtAggdV689W90JN7ja6jNBnkOFj1G', 'RTN987654', true, 2); -- ADMIN

INSERT INTO categories (name) VALUES ('Фантастика');
INSERT INTO categories (name) VALUES ('Научная литература');
INSERT INTO categories (name) VALUES ('Детская литература');

INSERT INTO books (title, author, category_id, image, available) VALUES
                                                                     ('Тайна третьей планеты', 'Кира Булычёв', 1, 'image1.jpg', true),
                                                                     ('1984', 'Джордж Оруэлл', 1, 'image2.jpg', true),
                                                                     ('Краткая история времени', 'Стивен Хокинг', 2, 'image3.jpg', true),
                                                                     ('Волшебник Изумрудного города', 'Александр Волков', 3, 'image4.jpg', true);

INSERT INTO user_authorities (user_id, authority_id) VALUES
                                                         (1, 1),
                                                         (2, 1),
                                                         (3, 2);
