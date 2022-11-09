-- Set magenta as default schema

-- Genero
insert into magenta.genero (inicial) values ("M"), ("F"), ("O");
-- Elementos    
insert into magenta.elementos (nombreElemento) values ("Fuego"), ("Tierra"), ("Agua"), ("Viento"), ("Void");
-- Clases enemigos
insert into magenta.clases_enemigos (nombreClase) values ("Dragon"), ("Fantasma"), ("Demonio"), ("Pez"), ("Humano"), ("Bestia"), ("Ave"), ("Otros");
-- Clases heroes
insert into magenta.clases_heroes (nombreClase) values ("Fedaykin"), ("Hechicero"), ("Explorador"), ("Tirador"), ("Centinela"), ("Paladin"), ("Berserker"), ("Jedi"), ("Netrunner"), ("Traceur"), ("Oraculo");
-- Objetos
insert into magenta.objetos (nombreObjeto, efecto, peso, borradoLogico,usadoPorHeroe) values ("Crysknife", "La mejor cuchilla del universo conocido", 0.5,0,0), ("Pistola Maula", "De corto alcance, extremadamente efectiva", 1.1,0,0),
("Escudo personal", "De energia pura, rodea a quien lo usa", 2.0 ,0,0), ("Lagrima de Eincrad", "Gema de color azur, aumenta el ataque",  3.1, 0,0), ("Varita de sauco", "Aumenta la precision y potencia del hechizo",  2.6, 0,0),
("Cubo de Zemeckis", "Retrocede el tiempo 60s", 0.2 , 0,0), ("Pildora roja","Libera tu mente",0.05,0,0), ("Shimmer","Pocion de Zaun que otorga buff momentaneo",1.3,0,0);
-- Hechizos base
insert into magenta.hechizos(nombreHechizo, potenciaHechizo, precisionHechizo, desbloqueado, nivelAprendizaje, idElemento, idHechizoBase, borradoLogico)
values ("Combustion", 15, 20, 1, null, 1, null,0), ("Confusion", 20, 14, 1, null, 5, null,0), ("Roca afilada", 23, 9, 1, null, 2, null,0), ("Burbujas", 40, 30, 1, null, 3, null,0), ("Vendaval", 10, 34, 1, null, 4, null,0) ;

-- hechizos
insert into magenta.hechizos(nombreHechizo, potenciaHechizo, precisionHechizo, desbloqueado, nivelAprendizaje, idElemento, idHechizoBase, borradoLogico)
values ("Inferno", 30, 40, 0, 5, 1, 1,0), ("Onda mental", 37, 23, 0, 8, 5, 2,0), ("Avalancha",43, 17, 0, 6, 2, 3,0), ("Hidrobomba", 60, 20, 0, 10, 3,4,0),  ("Tornado", 46, 23, 0, 4, 4,5,0 ) ;

-- Debilidades/Fortalezas
insert into magenta.deb_fort (idClaseEnemigo, idElemento, porcentajeDanio) values (1,1,0.2), (7,3,1.6), (2,3,0.1), (3,5,1.5), (4,2,0.7),(2,4,0.9),(6,3,1.6),(5,5,1.2),(7,4,1.7),(5,3,0.2),(6,5,1.4),(8,3,0.1),(8,4,1.8),(7,1,0.6),(4,2,0.5),(8,1,1.2);
-- Enemigos
insert into magenta.enemigos (nombreEnemigo, ataque, experienciaDerrotado, probabilidadDejarObjeto, idGenero, idObjeto, idClaseENemigo, borradoLogico)
values ("Forond", 80, 500, 0.35, 1, 2, 1,0), ("Muad'Dib", 100, 1000, 0.00005, 1, 1, 3,0), ("Scytale", 777, 1000, 0.00005, 3, 6, 5,0),
("Clicker", 40, 89, 0.78, 3, 4, 6,0), ("AgentSmith", 300, 2000, 0.9, 3, 7, 3,0);
-- Heroes
insert into magenta.heroes (nombreHeroe, edad, puntosExperiencia, nivelInicial, ataque, idPareja, idGenero, idClase, borradoLogico)  
values ("Mold", 30, 0, 50, 50, null, 1, 11,0),
		("Erde", 20, 0, 16, 20, null, 2, 3,0),
        ("Percival", 18, 0, 10, 25, null, 1, 1,0),
        ("LinusT", 52, 0, 50, 50, null, 1, 9,0),
        ("AlanMoore", 777, 0, 77, 100, null, 3, 2,0),
        ("Ellie", 23, 0, 12, 20, null, 2, 4,0),
        ("Neo", 25, 0, 40, 60, null, 1, 9,0),
        ("Trinity", 23, 0, 60, 40, null, 2, 4,0);
-- Inventario objetos
insert into magenta.inventario (idHeroe, idObjeto, cantidadOBjeto) values (3,1,1), (2,2,2), (7,7,4), (6,4,6), (3,5,2), (8,6,1), (5,3,4), (4,4,1), (8,5,3), (1,7,5), (3,3,4), (2,1,1);


update magenta.heroes set idPareja=3 where idHeroe=2;
update magenta.heroes set idPareja=2 where idHeroe=3;
update magenta.heroes set idPareja=7 where idHeroe=8;
update magenta.heroes set idPareja=8 where idHeroe=7;
update magenta.objetos set usadoPorHeroe=1 where idObjeto in (1,2,7,4,5,6,3);