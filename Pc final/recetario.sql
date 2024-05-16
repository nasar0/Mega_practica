DROP TABLE PasosPreparacion;
DROP TABLE Ingredientes;
DROP TABLE Recetas;
DROP TABLE usuarios;


CREATE TABLE usuarios (
    usuario VARCHAR2(20) PRIMARY KEY,
    pass VARCHAR2(20),
    email VARCHAR2(30) unique,
    tipo NUMBER(1,0) CHECK (tipo IN (1,2))
);

CREATE TABLE Recetas (
    ID integer GENERATED ALWAYS AS IDENTITY (Start with 1 Increment by 1) primary key,
    Nombre VARCHAR2(100) NOT NULL,
    Descripcion VARCHAR2(500),
    Dibujo VARCHAR2(100),
    Tiempo NUMBER(5,0), -- en minutos
    Dificultad VARCHAR2(20) CHECK (Dificultad IN ('Facil', 'Intermedia', 'Dificil')),
    Calorias NUMBER(5,0),
    usuario VARCHAR2(50),
    FOREIGN KEY (usuario) REFERENCES usuarios(usuario)
);

CREATE TABLE Ingredientes (
    Nombre VARCHAR2(100) NOT NULL,
    Cantidad VARCHAR2(50),
    ID NUMBER(2,0),
    CONSTRAINT fk_Recetas FOREIGN KEY (ID) REFERENCES Recetas(ID)
);

CREATE TABLE PasosPreparacion (
    PasoNumero NUMBER,
    Descripcion VARCHAR2(500),
    ID NUMBER(2,0),
    CONSTRAINT fk_Recetas_PP FOREIGN KEY (ID) REFERENCES Recetas(ID)
);

INSERT INTO usuarios VALUES ('1','1',null,1);

INSERT INTO Recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario) VALUES('Tarta de manzana de Hora de Aventuras', 'Una deliciosa tarta de manzana inspirada en el mundo de Hora de Aventuras.', 'Hora de Aventuras', 60, 'Intermedia', 350,'1');
INSERT INTO Recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario) VALUES ('Ramen de Naruto', 'Un plato de ramen caliente y reconfortante, al estilo del ramen que Naruto Uzumaki adora.', 'Naruto', 30, 'Facil', 300,'1');
INSERT INTO Recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario) VALUES ('Cangreburgers de Bob Esponja', 'Las famosas cangreburgers de Bob Esponja, tan deliciosas como en el Crustaceo Crujiente!', 'Bob Esponja', 45, 'Intermedia', 400,'1');
INSERT INTO Recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario) VALUES('Pudin de Chocolate de Billy y Mandy', 'Un delicioso pudin de chocolate que te transportara al mundo sombrio y comico de Billy y Mandy.', 'Las sombrias aventuras de Billy y Mandy', 45, 'Intermedia', 400,'1');
INSERT INTO Recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario) VALUES('Galletas de Jengibre de Shrek', 'Deliciosas galletas con forma de personajes de Shrek, perfectas para cualquier ocasion.', 'Shrek', 60, 'Facil', 250,'1');
INSERT INTO Recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario) VALUES ('Pastel de Zanahoria de Bugs Bunny', 'Un delicioso pastel de zanahoria con glaseado, favorito del iconico Bugs Bunny.', 'Looney Tunes', 50, 'Intermedia', 400,'1');
INSERT INTO Recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario) VALUES ('Sopa de Piedras de Pedro y el Lobo', 'Una sopa reconfortante y llena de sabor, inspirada en el cuento clasico de Pedro y el Lobo.', 'Pedro y el Lobo', 45, 'Facil', 200 ,'1');
INSERT INTO Recetas(Nombre,Descripcion,Dibujo,Tiempo,Dificultad,Calorias,usuario) VALUES ('Ratatouille de Remy', 'Un plato tradicional frances preparado con los mejores ingredientes y el toque especial del talentoso Remy.', 'Ratatouille', 45, 'Intermedia', 300,'1');

INSERT INTO Ingredientes VALUES ('Manzanas', '4 unidades', 1);
INSERT INTO Ingredientes VALUES ('Fideos de ramen', '1 paquete', 2);
INSERT INTO Ingredientes VALUES ('Caldo de pollo', '1 litro', 2);
INSERT INTO Ingredientes VALUES ('Carne de cangreburger', '200 gramos', 3);
INSERT INTO Ingredientes VALUES ('Pan de hamburguesa', '4 unidades', 3);
INSERT INTO Ingredientes VALUES ('Chocolate', '200 gramos', 4);
INSERT INTO Ingredientes VALUES ('Leche', '500 ml', 4);
INSERT INTO Ingredientes VALUES ('Masa para pizza', '1 unidad', 5);
INSERT INTO Ingredientes VALUES ('Tomate', '2 unidades', 5);
INSERT INTO Ingredientes VALUES ('Queso mozzarella', '200 gramos', 5);
INSERT INTO Ingredientes VALUES ('Zanahorias', '3 unidades', 6);
INSERT INTO Ingredientes VALUES ('Crema de queso', '200 gramos', 6);
INSERT INTO Ingredientes VALUES ('Harina', '200 gramos', 6);
INSERT INTO Ingredientes VALUES ('Piedras limpias', '5 unidades', 7);
INSERT INTO Ingredientes VALUES ('Caldo de verduras', '1 litro', 7);
INSERT INTO Ingredientes VALUES ('Berenjena', '2 unidades', 8);
INSERT INTO Ingredientes VALUES ('Calabacin', '2 unidades', 8);
INSERT INTO Ingredientes VALUES ('Pimiento rojo', '1 unidad', 8);
INSERT INTO Ingredientes VALUES ('Cebolla', '1 unidad', 8);
INSERT INTO Ingredientes VALUES ('Tomate', '3 unidades', 8);
INSERT INTO Ingredientes VALUES ('Ajo', '3 dientes', 8);
INSERT INTO Ingredientes VALUES ('Aceite de oliva', '50 ml', 8);
INSERT INTO Ingredientes VALUES ('Albahaca fresca', '1 ramita', 8);
INSERT INTO Ingredientes VALUES ('Sal', 'al gusto', 8);
INSERT INTO Ingredientes VALUES ('Pimienta negra', 'al gusto', 8);

INSERT INTO PasosPreparacion VALUES (1, 'Pelar las manzanas y cortarlas en rodajas finas.', 1);
INSERT INTO PasosPreparacion VALUES (2, 'Forrar un molde para tarta con la masa y colocar las rodajas de manzana sobre ella.', 1);
INSERT INTO PasosPreparacion VALUES (3, 'Espolvorear azucar sobre las manzanas y hornear durante 40 minutos.', 1);
INSERT INTO PasosPreparacion VALUES (1, 'Cocinar los fideos de ramen segun las instrucciones del paquete.', 2);
INSERT INTO PasosPreparacion VALUES (2, 'Calentar el caldo de pollo en una olla grande y agregar los fideos cocidos.', 2);
INSERT INTO PasosPreparacion VALUES (1, 'Cocinar la carne de cangreburger en una sarten caliente hasta que este dorada.', 3);
INSERT INTO PasosPreparacion VALUES (2, 'Tostar ligeramente los panes de hamburguesa.', 3);
INSERT INTO PasosPreparacion VALUES (3, 'Montar las hamburguesas colocando la carne entre los panes y servir caliente.', 3);
INSERT INTO PasosPreparacion VALUES (1, 'Calentar la leche en una olla y agregar el chocolate troceado.', 4);
INSERT INTO PasosPreparacion VALUES (2, 'Revolver constantemente hasta que el chocolate se derrita y la mezcla este suave.', 4);
INSERT INTO PasosPreparacion VALUES (3, 'Verter la mezcla en recipientes individuales y dejar enfriar en el refrigerador durante al menos 2 horas.', 4);
INSERT INTO PasosPreparacion VALUES (1, 'Extender la masa para pizza en una bandeja.', 5);
INSERT INTO PasosPreparacion VALUES (2, 'Cubrir la masa con salsa de tomate.', 5);
INSERT INTO PasosPreparacion VALUES (3, 'Agregar el queso mozzarella y los demas ingredientes.', 5);
INSERT INTO PasosPreparacion VALUES (4, 'Hornear la pizza a 200? durante 20-25 minutos.', 5);
INSERT INTO PasosPreparacion VALUES (1, 'Rallar las zanahorias y mezclarlas con la harina y la crema de queso.', 6);
INSERT INTO PasosPreparacion VALUES (2, 'Verter la mezcla en un molde y hornear a 180? durante 30 minutos.', 6);
INSERT INTO PasosPreparacion VALUES (1, 'Lavar las piedras y colocarlas en una olla con el caldo de verduras.', 7);
INSERT INTO PasosPreparacion VALUES (2, 'Cocinar a fuego medio durante 30 minutos.', 7);
INSERT INTO PasosPreparacion VALUES (1, 'Cortar todas las verduras en rodajas finas.', 8);
INSERT INTO PasosPreparacion VALUES (2, 'En una sarten grande, calentar el aceite de oliva y añadir el ajo picado.', 8);
INSERT INTO PasosPreparacion VALUES (3, 'Agregar las cebollas y los pimientos, cocinar hasta que esten tiernos.', 8);
INSERT INTO PasosPreparacion VALUES (4, 'Agregar las berenjenas y los calabacines, cocinar por unos minutos mas.', 8);
INSERT INTO PasosPreparacion VALUES (5, 'Añadir los tomates picados y cocinar hasta que se forme una salsa espesa.', 8);
INSERT INTO PasosPreparacion VALUES (6, 'Condimentar con sal, pimienta y albahaca fresca picada.', 8);
INSERT INTO PasosPreparacion VALUES (7, 'Servir caliente como guarnicion o plato principal.', 8);
commit;

