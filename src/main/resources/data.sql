-- 20 categories first
INSERT INTO category (id_category, category_name, parent_category_id_category)
SELECT
    gs,
    'Category ' || gs,
    CASE
        WHEN gs <= 5 THEN NULL
        ELSE FLOOR(RANDOM() * 5) + 1
    END
FROM generate_series(1, 20) AS gs;


-- 70 authors second
INSERT INTO author (id_author, name, age, nationality)
SELECT
    gs,
    'Author ' || gs,
    25 + (gs % 50),
    CASE
        WHEN gs % 5 = 0 THEN 'Algerian'
        WHEN gs % 5 = 1 THEN 'French'
        WHEN gs % 5 = 2 THEN 'English'
        WHEN gs % 5 = 3 THEN 'Moroccan'
        ELSE 'Tunisian'
    END
FROM generate_series(1, 70) AS gs;


-- 170 books last
INSERT INTO book (id_book, title, publication_year, language, b_pages, category_id_category, author_id_author)
SELECT
    gs,
    'Book ' || gs,
    1990 + (gs % 35),
    CASE
        WHEN gs % 3 = 0 THEN 'English'
        WHEN gs % 3 = 1 THEN 'French'
        ELSE 'Arabic'
    END,
    100 + (gs % 400),
    FLOOR(RANDOM() * 20) + 1,
    FLOOR(RANDOM() * 70) + 1
FROM generate_series(1, 170) AS gs;

