-- Genero
insert into genero (inicial) values ("M"), ("F"), ("O");
-- Elementos    
insert into elementos (nombreElemento) values ("Fuego"), ("Tierra"), ("Agua"), ("Viento"), ("Void");
-- Clases enemigos
insert into clases_enemigos (nombreClase) values ("Dragon"), ("Fantasma"), ("Demonio"), ("Pez"), ("Humano"), ("Bestia"), ("Ave"), ("Otros");
-- Clases heroes
insert into clases_heroes (nombreClase) values ("Fedaykin"), ("Hechicero"), ("Explorador"), ("Tirador"), ("Centinela"), ("Paladin");
-- Objetos
insert into objetos (nombreObjeto, efecto, peso) values ("Crysknife", "La mejor cuchilla del universo conocido", 0.5), ("Pistola Maula", "aaaaaaa", 1.1);
-- Hechizos
insert into hechizos(nombreHechizo, potenciaHechizo, precisionHechizo, desbloqueado, nivelAprendizaje, idElemento, idHechizoBase)
values ("Combustion", 15, 20, 1, null, 1, null), ("Inferno", 30, 40, 0, 5, 1, 1);
-- Debilidades/Fortalezas
insert into deb_fort (idClaseEnemigo, idElemento, porcentajeDanio) values (1,1,0.2), (2,3,0.1), (3,5,1.5);
-- Enemigos
insert into enemigos (nombreEnemigo, ataque, experienciaDerrotado, probabilidadDejarObjeto, idGenero, idObjeto, idClaseENemigo)
values ("Forond", 80, 500, 0.35, 1, 2, 1), ("Muad'Dib", 100, 1000, 0.00005, 1, 1, 3);
-- Heroes
insert into heroes (nombreHeroe, edad, puntosExperiencia, nivelInicial, ataque, idPareja, idGenero, idClase)  
values ("Mold", 30, 0, 50, 50, null, 1, 2),
		("Erde", 20, 0, 16, 20, null, 2, 3),
        ("Percival", 18, 0, 10, 25, null, 1, 1);
-- Inventario objetos
insert into inventario (idHeroe, idObjeto, cantidadOBjeto) values (3,1,1), (2,2,2);