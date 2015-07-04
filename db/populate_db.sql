USE test

INSERT Author (last_name, first_name) VALUES ('Пушкин', 'Александр')
INSERT Author (last_name, first_name) VALUES ('Барто', 'Агния')
INSERT Author (last_name, first_name, [biography]) VALUES ('Солженицын', 'Александр', 'Нобелевский лауреат 1970 года.')
INSERT Author (last_name, first_name) VALUES ('Чуковский', 'Корней')

INSERT City (name) VALUES ('Москва')
INSERT City (name) VALUES ('Санкт-петербург')
INSERT City (name) VALUES ('Новосибирск')
INSERT City (name) VALUES ('Ростов-на-Дону')
INSERT City (name) VALUES ('Милан')

INSERT Genre (name) VALUES ('Сказка')
INSERT Genre (name) VALUES ('Фантастика')
INSERT Genre (name) VALUES ('Поэзия')
INSERT Genre (name) VALUES ('Проза')
INSERT Genre (name) VALUES ('Ода')

INSERT OrderStatus VALUES ('в обработке')
INSERT OrderStatus VALUES ('собран')
INSERT OrderStatus VALUES ('доставлен')

INSERT Publisher (city_id, name) VALUES (1, 'Мир')
INSERT Publisher (city_id, name) VALUES (3, 'СО РАН')
INSERT Publisher (city_id, name) VALUES (4, 'Феникс')
INSERT Publisher (city_id, name) VALUES (5, 'Milan Presse')
INSERT Publisher (city_id, name) VALUES (1, 'Вестник ЦНИИС')

INSERT Bookface ([type]) VALUES ('Мягкая')
INSERT Bookface ([type]) VALUES ('Твёрдая')
INSERT Bookface ([type]) VALUES ('Кожаная')

INSERT Book (title, publisher_id, price, quantity, bookface_type, [year], num_pages) VALUES ('Красная шапочка', 4, 120, 2, 'Мягкая', 1994, 20)
INSERT Book (title, publisher_id, price, quantity, bookface_type, [year], num_pages) VALUES ('Колобок', 3, 1201, 23, 'Мягкая', 1974, 25)
INSERT Book (title, publisher_id, price, quantity, bookface_type, [year], num_pages) VALUES ('Занимательная физика', 1, 12111, 12, 'Твёрдая', 1960, 350)
INSERT Book (title, publisher_id, price, quantity, bookface_type, [year], num_pages) VALUES ('Жюльетта', 3, 200, 2, 'Кожаная', 2001, 264)
INSERT Book (title, publisher_id, price, quantity, bookface_type, [year], num_pages) VALUES ('Властелин Колец', 5, 124, 2123, 'Твёрдая', 1974, 680)

INSERT [Book-to-Genre] (book_id, genre_id) VALUES (1, 1)
INSERT [Book-to-Genre] (book_id, genre_id) VALUES (2, 1)
INSERT [Book-to-Genre] (book_id, genre_id) VALUES (3, 2)
INSERT [Book-to-Genre] (book_id, genre_id) VALUES (4, 4)
INSERT [Book-to-Genre] (book_id, genre_id) VALUES (5, 4)

INSERT [Author-to-Book] (author_id, book_id) VALUES (1, 1)
INSERT [Author-to-Book] (author_id, book_id) VALUES (2, 2)
INSERT [Author-to-Book] (author_id, book_id) VALUES (4, 3)
INSERT [Author-to-Book] (author_id, book_id) VALUES (4, 4)
INSERT [Author-to-Book] (author_id, book_id) VALUES (3, 5)
INSERT [Author-to-Book] (author_id, book_id) VALUES (3, 3)

INSERT Client (last_name, first_name, city_id, street, phone, postal_code, login, password_hash, wing, house, privilege) VALUES ('Кольцов', 'Михаил', 1, 'Исаковского', '8-495-111-22-33', '123123', 'm.koltsov', 12345, '1', '24', 0)
INSERT Client (last_name, first_name, city_id, street, phone, postal_code, login, password_hash, wing, house, privilege) VALUES ('НеКольцов', 'НеМихаил', 1, 'Исаковского', '8-495-111-22-33', '123123', 'NEm.koltsov', 123456, '1', '24', 2)
INSERT Client (last_name, first_name, city_id, street, phone, postal_code, login, password_hash, wing, house, privilege) VALUES ('МКольцов', 'М', 1, 'Исаковского', '8-495-111-22-33', '123123', 'Mm.koltsov', 123, '1', '24', 1)

INSERT [Order] (when_made, client_id, description, orderstatus_name) VALUES ('2007-05-08 12:35:29.123', 2, 'До подъезда, пожалуйста', 'доставлен')
INSERT [Order] (when_made, client_id, description, orderstatus_name) VALUES ('2007-05-08 12:36:29.123', 1, NULL, 'доставлен')
INSERT [Order] (when_made, client_id, description, orderstatus_name) VALUES ('2007-05-08 12:37:29.123', 3, 'Очень срочно!', 'в обработке')
INSERT [Order] (when_made, client_id, description, orderstatus_name) VALUES ('2007-05-08 12:38:29.123', 2, 'До подъезда', 'собран')
INSERT [Order] (when_made, client_id, description, orderstatus_name) VALUES ('2007-05-08 12:39:29.123', 1, 'До свидания', 'собран')

INSERT [Book-to-Order] (book_id, quantity, order_id) VALUES (2, 2, 1)
INSERT [Book-to-Order] (book_id, quantity, order_id) VALUES (3, 4, 1)
INSERT [Book-to-Order] (book_id, quantity, order_id) VALUES (5, 1, 1)
INSERT [Book-to-Order] (book_id, quantity, order_id) VALUES (4, 1, 2)
INSERT [Book-to-Order] (book_id, quantity, order_id) VALUES (1, 1, 3)
INSERT [Book-to-Order] (book_id, quantity, order_id) VALUES (2, 2, 4)
INSERT [Book-to-Order] (book_id, quantity, order_id) VALUES (3, 3, 5)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
