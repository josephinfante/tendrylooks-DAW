-- Create Category Table
CREATE TABLE Categoria (
      codCat INT PRIMARY KEY AUTO_INCREMENT,
      nomCat VARCHAR(90) NOT NULL,
      estCat BIT DEFAULT 1,
      fecCat DATETIME DEFAULT CURRENT_TIMESTAMP NULL)
);

-- Insert some Categories
INSERT INTO Categoria (nomCat)
VALUES
    ('Ropa Casual'),
    ('Pantalones'),
    ('Vestidos'),
    ('Ropa Deportiva'),
    ('Abrigos'),
    ('Calzado'),
    ('Accesorios'),
    ('Ropa Formal'),
    ('Ropa Interior'),
    ('Ropa de Invierno');

-- Create Category
DROP PROCEDURE IF EXISTS sp_CreateCategory;

CREATE PROCEDURE sp_CreateCategory(
    IN nomCat VARCHAR(90)
)
BEGIN
INSERT INTO Categoria (nomCat)
VALUES (nomCat);
END;

-- Update Category
DROP PROCEDURE IF EXISTS sp_UpdateCategory

CREATE PROCEDURE sp_UpdateCategory(
    IN codCat INT,
    IN nomCat VARCHAR(90),
    IN estCat BOOLEAN,
    IN fecCat DATETIME
)
BEGIN
UPDATE Categoria
SET
    nomCat = nomCat,
    estCat = estCat,
    fecCat = fecCat
WHERE codCat = codCat;
END;

-- Get All Categories
DROP PROCEDURE IF EXISTS sp_GetAllCategories;

CREATE PROCEDURE sp_GetAllCategories(IN p_limit INT, IN p_offset INT)
BEGIN
SELECT * FROM Categoria LIMIT p_limit OFFSET p_offset;
END;