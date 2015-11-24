--http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Audioslave&api_key=57ee3318536b23ee81d6b27e36997cde&format=json

CREATE SCHEMA STEAMFM;

CREATE TABLE STEAMFM.GENERO_JOGO (
ID_GENERO_JOGO INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
NOME_GENERO VARCHAR(100) NOT NULL
);

INSERT INTO STEAMFM.GENERO_JOGO (NOME_GENERO) VALUES 
('2D'),
('Ação'),
('Ação / Aventura'),
('Anime'),
('Arcade'),
('Atmosferico'),
('Aventura'),
('Basquete'),
('Beat''em Up'),
('Cartunizado'),
('Casual'),
('Clássico'),
('Comédia'),
('Competitivo'),
('Cooperativo'),
('Corrida'),
('Cyberpunk'),
('Dark Humor'),
('Difícil'),
('Educativo'),
('Engraçado'),
('Escolhas Importam'),
('Espacial'),
('Esportes'),
('Estilizado'),
('Estratégia'),
('Estratégia em Tempo Real (RTS)'),
('Exploração'),
('Fabricação'),
('Fantasia'),
('Fantasia Sombria'),
('Ficção científica'),
('Física'),
('fofo'),
('FPS'),
('Furtivo'),
('Gráficos Pixelados'),
('Gratuito para Jogar'),
('Hack and Slash'),
('Hackear'),
('Hardware'),
('História'),
('História Alternativa'),
('Indie'),
('Infantil'),
('Jogador Deus'),
('J-RPG'),
('Mecha'),
('Medieval'),
('Metroidvania'),
('Multijogador Massivo Online (MMO)'),
('Mundo Aberto'),
('Mystery Dungeon'),
('Ninja'),
('Nudez'),
('Parkour'),
('Protagonista feminina'),
('Puzzle'),
('Quadrinhos'),
('Remake'),
('Rico em História'),
('Roguelike'),
('Romance Visual'),
('RPG'),
('RPG de Ação'),
('Sangue'),
('Saquear'),
('Shoot ''em up (shmup)'),
('Sobrevivência'),
('Sombrio'),
('Soundtrack'),
('Star Wars'),
('Steampunk'),
('Super Herói'),
('Tanques'),
('Terror'),
('Terror de Sobrevivência'),
('Tiro'),
('Tower Defense'),
('trilha sonora boa'),
('Wargame'),
('Xadrez'),
('Zumbi');

CREATE TABLE STEAMFM.MUSICA (
ID_MUSICA INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
TITULO_MUSICA VARCHAR(100) NOT NULL,
ID_MUSICA_LASTFM VARCHAR(100) NOT NULL,
IMAGEM VARCHAR(255),
DESCRICAO VARCHAR(1000)
);

INSERT INTO STEAMFM.MUSICA (TITULO_MUSICA, ID_MUSICA_LASTFM) VALUES
('Show Me How To Live', '62d9d200-5445-4ee9-9541-88345027d0f1'), --1
('I Want Out', '5811749b-8c42-4ecb-baac-6e8ca0166d8f'),--2
('Get Back', 'f1a6d5af-73ed-4213-9182-c8e2cdf841db'),--3
('Sabotage', '73ef25fd-89c6-452e-b02c-90bf8bcb95b7'),--4
('Do The Evolution', '8e69ef9f-603e-4ee6-bf13-69aeb7c7e7e5'),--5
('Happy', '455732fa-77b5-4ac5-bfa8-18dea06451d7'),--6
('Moonlight Sonata', '0bba02c9-8e00-4e66-b81d-9b3594ec5e81'),--7
('The Trooper', 'e920ec5b-7cda-45fd-be4a-4d3280996f54'),--8
('All Together Now', 'af994d19-6e64-4d10-a1b4-217caf14698d'),--9
('We Are the Champions', '4b3e7f93-5c74-4ad8-9a88-8b0676b5604e'),--10
('Crush On You', '68607b7f-49c7-4cfe-9699-e52f45803e71'),--11
('Guaranteed', 'f36024a5-324a-4d3a-bf42-0acdc81d47bf'),--12
('Hacker', '42c1be71-2f5b-4900-b206-0edafa141b3a'),--13
('Ramble On', '906769c2-9d4f-4212-8be3-fb7d5fe5e614'),--14
('Aluga-se', 'c519ac9e-218d-4d0c-86e8-599f6988494c'),--15
('About that time', '052af4a2-8962-40c2-a967-fdc604fe1ce8'),--16
('Rap Das Armas', '95bd6543-f8ff-4be9-953c-091dcc8a83c2'),--17
('Pull the Pin', 'e098e8bc-3d35-43c4-b24c-9c7bd707bee3'),--18
('Lullaby', '7eca082a-4b79-49d9-812d-1c9711cfe346'),--19
('Pocket Calculator', '867cf1a1-c46a-4ad5-916f-0c5397ff65e5'),--20
('Often', 'e5dd9ecb-3fd1-4e85-9c4b-521ec87c8840'),--21
('Fly Away', '93ed37e9-b27f-4969-9e92-1684862352f9'),--22
('Robot', '72681861-77e6-4fc2-81f3-55c73600cd91'),--23
('Skyfall', 'edda9387-65a9-4ade-903c-3a9cea3fa7c2'),--24
('The Saga Begins', '37a3915b-6748-4ee7-a7bb-02a3665d879d'),--25
('Iron', '70f41327-d8f5-4895-8e08-b7da28dd9007'),--26
('Panzer', '6a276888-9dab-4e62-a907-1c5b59c80dcc'),--27
('Machine Gun', '1fefb693-e195-424a-9e34-d026ec09bc16'),--28
('War Pigs', 'c2786bd8-7dc7-4633-ab6c-70c70ebd432f'),--29
('The Chess Players', '0e922118-efce-4a4e-9ef2-a03c6a9ad15d'),--30
('Iron Man', '4b5ee2e8-3135-40f4-8837-64314baa640d'),--31
('Stronger', 'a7ddf553-673a-4d4e-96cc-d5a4bca6af2d'),--32
('Lie, Cheat, Steal', 'ff15b1f2-b72d-487e-a9e6-cb20f2230ff1'),--33
('Intro', '773a1557-a974-4f1e-978e-c240be4c3cb8'),--34
('All Along the Watchtower', '81edf1da-d812-4e08-96dc-bf8c48edef1a'),--35
('The Age of the Understatement', '577fa4a2-c4e0-426b-916b-bf7321e7af4b'),--36
('I Shot The Sheriff', '38506054-b826-4e81-8108-d9634afcd199'),--37
('Blitzkrieg Bop', '71e53239-315b-4f96-872a-11ee5fc779c9');--38

CREATE TABLE STEAMFM.ALBUM (
ID_ALBUM INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
TITULO_ALBUM VARCHAR(100) NOT NULL,
ID_ALBUM_LASTFM VARCHAR(100) NOT NULL,
IMAGEM VARCHAR(255),
DESCRICAO VARCHAR(1000)
);

INSERT INTO STEAMFM.ALBUM (TITULO_ALBUM, ID_ALBUM_LASTFM) VALUES 
('Demon Days', 'ad0a377b-6c7c-30ff-921d-a47edae073e2'),--1
('Let Me Introduce My Friends', 'db09aad8-bb63-4203-9a0a-3a24cdf2e7c5'),--2
('Ladies and Gentlemen We Are Floating in Space', 'a74bf5c1-7a8d-302b-9fe0-de4cd596a3e7'),--3
('Meet the Residents', '259359e6-50af-4bc9-bb37-22a3a0e485fb'),--4
('Year Zero', '8067f190-dc3e-362a-8117-8a13df522b2c'),--5
('The Wall', 'd4611812-e7cd-42bf-885a-b1cea9fd52bc'),--6
('2112', '800fea67-8a4a-48a0-bffb-07181549d2df'),--7
('The Rise and Fall of Ziggy Stardust and the Spiders From Mars', 'da4db8d1-13b2-3d5e-a447-e2ad7733476a'),--8
('My War', 'c04c7090-1bea-4852-a4c3-6d54065117d2'),--9
('Dark Medieval Times', '085dfe73-75bf-3a7b-b2c0-a426876642ca'),--10
('Filosofem', '1ce9177c-62a0-4403-a7ee-7359026fcbf6'),--11
('Giles Corey', '945d7382-e187-454c-bbf9-09abe4404772'),--12
('The Greatest Video Game Music', '790e13e7-590a-437a-9dcd-a7542b9ad53a');--13

CREATE TABLE STEAMFM.ARTISTA (
ID_ARTISTA INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
NOME_ARTISTA VARCHAR(100) NOT NULL,
ID_ARTISTA_LASTFM VARCHAR(100) NOT NULL,
IMAGEM VARCHAR(255),
DESCRICAO VARCHAR(1000)
);

INSERT INTO STEAMFM.ARTISTA (NOME_ARTISTA, ID_ARTISTA_LASTFM) VALUES 
('the GazettE', '8d4c13de-6e61-4a8e-ac4d-d0e1996bcef8'), --1
('Explosions In The Sky', '4236acde-2ce2-441c-a3d4-38d55f1b5474'),--2
('Anamanaguchi', 'e4d7cfe5-0bed-46cf-acad-ab9a4dcb7aa6'),--3
('Survival', 'c8550ac0-6fd4-404d-af97-44b2b51f9103'),--4
('Mamonas Assassinas', '3abca171-d700-4a1f-aa0f-d683c37ef74d'),--5
('The Glitch Mob', '2e62daef-dc0d-40ad-b430-fd6872e992aa'),--6
('The Lonely Island', '028e1863-cab4-4a3d-9dd9-91c682c91005'),--7
('Detonator', 'cdcdd83e-571a-4285-9cd0-0b452f5d9318'),--8
('David Bowie', '5441c29d-3602-4898-b1a1-b77fa23b8e50'),--9
('Sabaton', '39a31de6-763d-48b6-a45c-f7cfad58ffd8'),--10
('Algiers', '685a5aa7-68b9-4293-9bd8-a6e4ac6b5160'),--11
('Misfits', '936addc3-91aa-49de-8ec0-0dc186de151f'),--12
('Marcin Przybylowicz', '9ce4f0c8-8ce4-461a-ba1e-10460d2399a2'),--13
('Kyary Pamyu Pamyu', '10e2d18c-7a8c-4d26-bb4a-450bb6851cb2'),--14
('Arctic Monkeys', 'ada7a83c-e3e1-40f1-93f9-3e73dbc9298a'),--15
('Mono', 'ffe02aed-ef7e-4736-a186-c2f1dd55ce8d'),--16
('Tang Dynasty', 'ea1b05cd-3d66-4760-89ea-d339227f67fd'),--17
('Hans Zimmer', 'e6de1f3b-6484-491c-88dd-6d619f142abc'),--18
('Pink Floyd', '83d91898-7763-47d7-b03b-b92132375c47'),--19
('Kanye West', '164f0d73-1234-4e2c-8743-d77bf2191051'),--20
('The Jackson 5','e5257dc5-1edd-4fca-b7e6-1158e00522c8'),--21
('Johann Sebastian Bach','24f1766e-9635-4d58-a4d4-9413f9f98a4c'),--22
('Rhapsody','4e0dffde-ad2d-45b7-9c75-d57ce55de061'),--23
('Steam Powered Giraffe','101c4754-1dc7-4c0f-92b9-49d8063d63da'),--24
('Linkin Park','f59c5520-5f46-4d2c-b2c4-822eabf53419'),--25
('Joan Jett','f376828a-b438-4fda-bb2e-dcd5fbe81f83'),--26
('Gorgoroth','a1d69fd9-0096-4a60-b277-de34e5a77fca'),--27
('White Zombie','cfe572bf-840f-4bed-934a-f69f4b1e90d4'),--28
('Bauhus','7b3e04bb-c4bb-4e7b-aa17-65ed5ca4925d'),--29
('The Music Tapes','11e5be8b-e420-43ff-a8fc-87d8336db556'),--30
('The Black Keys','d15721d8-56b4-453d-b506-fc915b14cba2'),--31
('Dethklok','1afcd689-9be4-4d1a-a9fa-49e086250f51'),--32
('Blind Guardian','7fa7fc04-1011-4876-8095-ecd232edea87'),--33
('HammerFall','abf5f674-dbad-4219-b3ad-dbd6fb163c8e');--34

CREATE TABLE STEAMFM.RELACAO (
ID_RELACAO INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
ID_GENERO_JOGO INT, FOREIGN KEY (ID_GENERO_JOGO) REFERENCES STEAMFM.GENERO_JOGO (ID_GENERO_JOGO) ON DELETE CASCADE,
ID_MUSICA INT, FOREIGN KEY (ID_MUSICA) REFERENCES STEAMFM.MUSICA (ID_MUSICA) ON DELETE CASCADE,
ID_ALBUM INT, FOREIGN KEY (ID_ALBUM) REFERENCES STEAMFM.ALBUM (ID_ALBUM) ON DELETE CASCADE,
ID_ARTISTA INT, FOREIGN KEY (ID_ARTISTA) REFERENCES STEAMFM.ARTISTA (ID_ARTISTA) ON DELETE CASCADE
);

INSERT INTO STEAMFM.RELACAO(ID_GENERO_JOGO, ID_MUSICA, ID_ALBUM, ID_ARTISTA) VALUES
(1, null, 1, null), --2d - gorillaz - demon days
(2, null, null, 4), --ação - survival
(3, 1, null, null), --ação aventura - audioslave - show me how to live
(4, null, null, 1), --anime - the gazette
(5, null, null, 3), --arcade - Anamanaguchi
(6, null, null, 2), --atmosferico - explosions in the sky
(7, 2, null, null), --aventura - helloween - i want out
(8, 3, null, null), --basquete - ludacris - get back 
(9, 4, null, null), --beatemup - beastie boys - sabotage
(10, 5, null, null), --cartunizado - pearl jam - do the evolution
(11, 6, null, null), --casual - pharrel - happpy
(12, 7, null, null), --clásico - bethoveen - moonlight sonata
(13, null, null, 5), --comédia - mamonas assassinas
(14, 8, null, null), --competivivo - iron maiden - the trooper
(15, null, 2, null), --cooperativo - i'm from barcelona - Let Me Introduce My Friends
(16, null, null, 6), --corrida - The Glitch Mob
(17, null, 5, null), --cyberpunk - nine inch nails - year zero
(18, null, null, 7), --dark humor - the lonely island
(19, null, 4, null), --dificil - the residents  - meet the residents
(20, 9, null, null), --educativo - the beatles - all together now
(21, null, null, 8), --Engraçado - detonator
(22, null, null, 11), --Escolhas Importam - algiers
(23, null, 3, null), --Espacial - Spiritualized - Ladies and Gentlemen We Are Floating in Space
(24, 10, null, null), --Esportes - queen - we are the champions
(25, null, null, 9), --Estilizado - david bowie
(26, null, null, 19), --Estratégia - Pink Floyd
(27, null, 9, null), --Estratégia em Tempo Real (RTS) - black flag - my war
(28, 12, null, null), --Exploração - eddie vedder - guaranteed
(29, null, 6, null), --Fabricação - pink floyd - the wall
(30, 14, null, null), --Fantasia - led zeppelin - ramble on
(31, null, null, 13), --Fantasia Sombria - Marcin Przybylowicz
(32, null, 7, null), --Ficção científica - rush - 2112 
(33, null, 8, null), --Física - david bowie - The Rise and Fall of Ziggy Stardust and the Spiders From Mars
(34, null, null, 14), --fofo - kyary pamyu pamyu
(35, 17, null, null), --FPS - cidinho e doca - rap das armas
(36, 24, null, null), --Furtivo - adele- skyfall
(37, 16, null, null), --Gráficos Pixelados - flying lotus - about that time (a glitch is a glitch)
(38, 15, null, null), --Gratuito para Jogar - raul seixas - aluga-se
(39, 18, null, null), --Hack and Slash - combichrist - pull the pin
(40, 13, null, null), --Hackear - death grips - hacker
(41, 20, null, null), --Hardware - kraftwerk - pocket calculator
(42, null, null, 10), --História - sabaton
(43, 38, null, null), --História Alternativa - ramones - Blitzkrieg Bop
(44, null, null, 15), --Indie - Arctic Monkeys
(45, null, null, 21), --Infantil - the jackson 5
(46, null, null, 20), --Jogador Deus - kanye west
(47, null, null, 16), --J-RPG - Mono
(48, 23, null, null), --Mecha - the futureheads - robot
(49, null, 10, null), --Medieval - Satyricon - Dark Medieval Times
(50, null, null, 22), --Metroidvania - Johann Sebastian Bach
(51, null, null, 23), --Multijogador Massivo Online (MMO) - Rhapsody
(52, null, null, 31), --Mundo Aberto - the black keys
(53, null, 11, null), --Mystery Dungeon - burzum - Filosofem
(54, null, null, 17), --Ninja - tang dynasty 
(55, 21, null, null), --Nudez - the weeknd - often
(56, null, null, 25), --Parkour - linkin park
(57, null, null, 26), --Protagonista feminina - joan jett
(58, 34, null, null), --Puzzle - the xx - intro
(59, null, null, 32), --Quadrinhos - Dethklok
(60, 35, null, null), --Remake - jimi hendrix - all along the watchtower
(61, null, null, 30), --Rico em História - The Music Tapes
(62, 36, null, null), --Roguelike - the last shaddow puppets - The Age of the Understatement
(63, 11, null, null), --Romance Visual - nero - crush on you 
(64, null, null, 33), --RPG - Blind Guardian
(65, null, null, 34), --RPG de Ação - HammerFall
(66, null, null, 27), --Sangue - Gorgoroth
(67, 33, null, null), --Saquear - run the jewels - lie cheat steal
(68, 37, null, null), --Shoot ''em up (shmup) - eric clapton - i shot the sheriff
(69, 32, null, null), --Sobrevivência - kanye west - stronger
(70, null, null, 29), --Sombrio - bauhaus
(71, null, 13, null), --Soundtrack - London Philharmonic Orchestra - The Greatest Video Game Music
(72, 25, null, null), --Star Wars - weird al - the saga begins
(73, null, null, 24), --Steampunk - Steam Powered Giraffe
(74, 31, null, null), --Super Herói - black sabbath - iron man
(75, 27, null, null), --Tanques - amplifier - panzer
(76, null, null, 12), --Terror - misfits
(77, null, 12, null), --Terror de Sobrevivência - Giles Corey- Giles Corey
(78, 28, null, null), --Tiro - jimi hendrix - machine gun
(79, 26, null, null), --Tower Defense - woodkid - iron 
(80, null, null, 18), --trilha sonora boa - hans zimmer
(81, 29, null, null), --Wargame - black sabbath - war pigs
(82, 30, null, null), --Xadrez - art blakey - the ches players
(83, null, null, 28); --Zumbi - white zombie

CREATE TABLE STEAMFM.USUARIO (
ID_USUARIO INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
LOGIN VARCHAR(100) NOT NULL,
SENHA VARCHAR(100) NOT NULL,
USUARIO_STEAM VARCHAR(100)
);

INSERT INTO STEAMFM.USUARIO(LOGIN, SENHA, USUARIO_STEAM) VALUES ('admin', 'admin', null);

--SELECT steamfm.relacao.id_relacao, steamfm.genero_jogo.nome_genero, steamfm.musica.titulo_musica, steamfm.album.titulo_album, steamfm.artista.nome_artista FROM   steamfm.relacao LEFT JOIN steamfm.genero_jogo ON steamfm.genero_jogo.id_genero_jogo = steamfm.relacao.id_genero_jogo LEFT JOIN steamfm.musica ON steamfm.musica.id_musica = steamfm.relacao.id_musica LEFT JOIN steamfm.album ON steamfm.album.id_album = steamfm.relacao.id_album LEFT JOIN steamfm.artista ON steamfm.artista.id_artista = steamfm.relacao.id_artista;