-- Create Product Table
CREATE TABLE Producto (
    codProd INT PRIMARY KEY AUTO_INCREMENT,
    nomProd VARCHAR(90) NOT NULL,
    descProd VARCHAR(500) DEFAULT NULL,
    codCat INT NOT NULL,
    preProd DECIMAL(10, 2) NOT NULL,
    stockProd INT NOT NULL,
    imgProd VARCHAR(1000) DEFAULT NULL,
    estProd BIT DEFAULT 1,
    fecProd DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
    FOREIGN KEY (codCat) REFERENCES Categoria(codCat)
);

-- Insert some products
INSERT INTO Producto (nomProd, descProd, codCat, preProd, stockProd, imgProd)
VALUES
    ('Camiseta Básica', 'Camiseta de algodón en varios colores', 1, 12.99, 100, 'img_camiseta_basica.jpg'),
    ('Jeans Clásicos', 'Jeans de mezclilla ajustados, color azul oscuro', 2, 29.99, 50, 'img_jeans_clasicos.jpg'),
    ('Vestido Elegante Blanco', 'Vestido elegante en color blanco, perfecto para ocasiones especiales', 3, 55.99, 25, 'img_vestido_blanco_elegante.jpg'),
    ('Sudadera con Capucha', 'Sudadera con capucha y bolsillo delantero, varios colores', 4, 39.95, 80, 'img_sudadera_capucha.jpg'),
    ('Chaqueta de Cuero', 'Chaqueta de cuero sintético, color negro', 5, 99.99, 20, 'img_chaqueta_cuero.jpg'),
    ('Zapatillas Deportivas', 'Zapatillas cómodas para actividades deportivas', 6, 59.99, 60, 'img_zapatillas_deportivas.jpg'),
    ('Sombrero de Paja', 'Sombrero de paja ideal para el verano', 7, 15.99, 40, 'img_sombrero_paja.jpg'),
    ('Camisa Formal', 'Camisa formal para eventos importantes', 8, 25.99, 70, 'img_camisa_formal.jpg'),
    ('Calcetines Deportivos', 'Calcetines de algodón para uso diario y deportes', 9, 6.99, 200, 'img_calcetines_deportivos.jpg'),
    ('Abrigo de Invierno', 'Abrigo grueso y cálido para el invierno', 10, 120.99, 15, 'img_abrigo_invierno.jpg');

-- Create Product
DROP PROCEDURE IF EXISTS sp_CreateProduct;

CREATE PROCEDURE sp_CreateProduct(
    IN nomProd VARCHAR(90),
    IN descProd VARCHAR(500),
    IN codCat INT,
    IN preProd DECIMAL(10, 2),
    IN stockProd INT,
    IN imgProd VARCHAR(255)
)
BEGIN
    INSERT INTO Producto (nomProd, descProd, codCat, preProd, stockProd, imgProd)
    VALUES (nomProd, descProd, codCat, preProd, stockProd, imgProd);
END;

-- Update Product
DROP PROCEDURE IF EXISTS sp_UpdateProduct

CREATE PROCEDURE sp_UpdateProduct(
    IN codProd INT,
    IN nomProd VARCHAR(90),
    IN descProd VARCHAR(500),
    IN codCat INT,
    IN preProd DECIMAL(10, 2),
    IN stockProd INT,
    IN imgProd VARCHAR(255),
    IN estProd BOOLEAN,
    IN fecProd DATETIME
)
BEGIN
    UPDATE Producto
    SET 
        nomProd = nomProd,
        descProd = descProd,
        codCat = codCat,
        preProd = preProd,
        stockProd = stockProd,
        imgProd = imgProd,
        estProd = estProd,
        fecProd = fecProd
    WHERE codProd = codProd;
END;

-- Get All Products
DROP PROCEDURE IF EXISTS sp_GetAllProducts;

CREATE PROCEDURE sp_GetAllProducts(IN p_limit INT, IN p_offset INT)
BEGIN
    SELECT * FROM Producto LIMIT p_limit OFFSET p_offset;
END;