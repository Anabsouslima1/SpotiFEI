-- Criação da tabela usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);

-- Criação da tabela artistas
CREATE TABLE IF NOT EXISTS artistas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

-- Criação da tabela albuns
CREATE TABLE IF NOT EXISTS albuns (
    id SERIAL PRIMARY KEY,
    id_artista INTEGER NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    ano_lancamento INTEGER,
    FOREIGN KEY (id_artista) REFERENCES artistas(id)
);

-- Criação da tabela playlists
CREATE TABLE Playlists (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    id_usuario INTEGER NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id)
);

-- Criação da tabela playlist_musicas
CREATE TABLE playlist_musicas (
    playlist_id INT NOT NULL,
    musica_id INT NOT NULL,
    PRIMARY KEY (playlist_id, musica_id),
    FOREIGN KEY (playlist_id) REFERENCES playlist(id) ON DELETE CASCADE,
    FOREIGN KEY (musica_id) REFERENCES musicas(id) ON DELETE CASCADE
);

-- Criação da tabela músicas
CREATE TABLE IF NOT EXISTS musicas (
    id SERIAL PRIMARY KEY,
    id_album INTEGER NOT NULL,
    id_artista INTEGER NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    duracao INTERVAL NOT NULL,
    genero VARCHAR(100),
    ano_lancamento INTEGER,
    FOREIGN KEY (id_album) REFERENCES albuns(id),
    FOREIGN KEY (id_artista) REFERENCES artistas(id)
);

-- Limpa tabelas para evitar duplicações (opcional, cuidado em produção)
DELETE FROM musicas;
DELETE FROM albuns;
DELETE FROM artistas;

-- Insere artistas
INSERT INTO artistas (id, nome) VALUES
(1, 'Chico Buarque'),
(2, 'Tim Maia'),
(3, 'Nirvana'),
(4, 'Ella Fitzgerald'),
(5, 'The Marías'),
(6, 'Legião Urbana'),
(7, 'Kendrick Lamar'),
(8, 'Tame Impala'),
(9, 'Hozier'),
(10, 'Queen'),
(11, 'Seu Jorge'),
(12, 'Lady Gaga'),
(13, 'Rita Lee'),
(14, 'The Smiths'),
(15, 'Arctic Monkeys'),
(16, 'System of a Down'),
(17, 'ABBA'),
(18, 'Gorillaz'),
(19, 'Chappell Roan'),
(20, 'Cartola'),
(21, 'Alcione'),
(22, 'Tyler, The Creator'),
(23, 'Sabrina Carpenter'),
(24, 'Alice Phoebe Lou'),
(25, 'Caravan Palace');


-- Insere álbuns
INSERT INTO albuns (id, id_artista, titulo, ano_lancamento) VALUES
(1, 1, 'Construção', 1971),
(2, 2, 'O Descobridor Dos Sete Mares', 1983),
(3, 3, 'In Utero', 1993),
(4, 4, 'Ella Fitzgerald Sings the Cole Porter Songbook', 1956),
(5, 5, 'Submarine', 2024),
(6, 6, 'As Quatro Estações', 1989),
(7, 7, 'Mr. Morale & the Big Steppers', 2022),
(8, 8, 'Currents', 2015),
(9, 9, 'Hozier', 2014),
(10, 10, 'A Day at the Races', 1976),
(11, 11, 'Musica para Churrasco, Vol. 1', 2011),
(12, 12, 'Mayhem', 2025),
(13, 13, 'Rita Lee', 1980),
(14, 14, 'Louder Than Bombs', 1987),
(15, 15, 'AM', 2013),
(16, 16, 'Toxicity', 2001),
(17, 17, 'Single', 1976),
(18, 18, 'Demon Days', 2005),
(19, 19, 'The Rise and Fall of a Midwest Princess', 2023),
(20, 20, 'Cartola (1976)', 1976),
(21, 21, 'Sabiá Marrom - O Samba Raro de Alcione', 1999),
(22, 22, 'CHROMAKOPIA', 2017),
(23, 23, 'Short n'' Sweet', 2022),
(24, 24, 'Glow', 2019),
(25, 25, 'Gangbusters Melody Club', 2019);



-- Insere músicas
INSERT INTO musicas (id_album, id_artista, titulo, duracao, genero, ano_lancamento) VALUES
(
    (SELECT id FROM albuns WHERE titulo = 'Construção' AND id_artista = 1),
    1,
    'Construção',
    '00:06:23',
    'MPB',
    1971
),
(
    (SELECT id FROM albuns WHERE titulo = 'O Descobridor Dos Sete Mares' AND id_artista = 2),
    2,
    'O Descobridor Dos Sete Mares',
    '00:04:25',
    'Soul',
    1983
),
(
    (SELECT id FROM albuns WHERE titulo = 'In Utero' AND id_artista = 3),
    3,
    'Heart-Shaped Box',
    '00:04:41',
    'Rock',
    1993
),
(
    (SELECT id FROM albuns WHERE titulo = 'Ella Fitzgerald Sings the Cole Porter Songbook' AND id_artista = 4),
    4,
    'Night and Day',
    '00:03:06',
    'Jazz',
    1956
),
(
    (SELECT id FROM albuns WHERE titulo = 'Submarine' AND id_artista = 5),
    5,
    'Paranoia',
    '00:03:51',
    'Indie Pop',
    2024
),
(
    (SELECT id FROM albuns WHERE titulo = 'As Quatro Estações' AND id_artista = 6),
    6,
    'Pais E Filhos',
    '00:05:08',
    'Rock Brasileiro',
    1989
),
(
    (SELECT id FROM albuns WHERE titulo = 'Mr. Morale & the Big Steppers' AND id_artista = 7),
    7,
    'United In Grief',
    '00:04:15',
    'Hip Hop',
    2022
),
(
    (SELECT id FROM albuns WHERE titulo = 'Currents' AND id_artista = 8),
    8,
    'The Less I Know The Better',
    '00:03:36',
    'Psychedelic Pop',
    2015
),
(
    (SELECT id FROM albuns WHERE titulo = 'Hozier' AND id_artista = 9),
    9,
    'From Eden',
    '00:04:43',
    'Soul Rock',
    2014
),
(
    (SELECT id FROM albuns WHERE titulo = 'A Day at the Races' AND id_artista = 10),
    10,
    'Somebody To Love',
    '00:04:55',
    'Rock',
    1976
),
(
    (SELECT id FROM albuns WHERE titulo = 'Musica para Churrasco, Vol. 1' AND id_artista = 11),
    11,
    'Quem Não Quer Sou Eu',
    '00:05:26',
    'MPB',
    2011
),
(
    (SELECT id FROM albuns WHERE titulo = 'Mayhem' AND id_artista = 12),
    12,
    'Abracadabra',
    '00:03:43',
    'Dance-Pop',
    2025
),
(
    (SELECT id FROM albuns WHERE titulo = 'Rita Lee' AND id_artista = 13),
    13,
    'Lança Perfume',
    '00:02:07',
    'Pop Rock',
    1980
),
(
    (SELECT id FROM albuns WHERE titulo = 'Louder Than Bombs' AND id_artista = 14),
    14,
    'Unloveable',
    '00:03:56',
    'Alternative Rock',
    1987
),
(
    (SELECT id FROM albuns WHERE titulo = 'AM' AND id_artista = 15),
    15,
    'One For The Road',
    '00:03:26',
    'Indie Rock',
    2013
),
(
    (SELECT id FROM albuns WHERE titulo = 'Toxicity' AND id_artista = 16),
    16,
    'Aerials',
    '00:03:55',
    'Metal Alternativo',
    2001
),
(
    (SELECT id FROM albuns WHERE titulo = 'Single' AND id_artista = 17),
    17,
    'Dancing Queen',
    '00:03:50',
    'Disco',
    1976
),
(
    (SELECT id FROM albuns WHERE titulo = 'Demon Days' AND id_artista = 18),
    18,
    'Feel Good Inc.',
    '00:03:42',
    'Alternative Hip Hop',
    2005
),
(
    (SELECT id FROM albuns WHERE titulo = 'The Rise and Fall of a Midwest Princess' AND id_artista = 19),
    19,
    'Pink Pony Club',
    '00:04:18',
    'Pop',
    2023
),
(
    (SELECT id FROM albuns WHERE titulo = 'Cartola (1976)' AND id_artista = 20),
    20,
    'Alvorada',
    '00:02:38',
    'Samba',
    1976
),
(
    (SELECT id FROM albuns WHERE titulo = 'Sabiá Marrom - O Samba Raro de Alcione' AND id_artista = 21),
    21,
    'Figa de Guiné',
    '00:02:19',
    'Samba',
    1972
),
(
    (SELECT id FROM albuns WHERE titulo = 'CHROMAKOPIA' AND id_artista = 22),
    22,
    'Noid',
    '00:03:45',
    'Hip Hop / Rap Rock',
    2024
),
(
    (SELECT id FROM albuns WHERE titulo = 'Short n'' Sweet' AND id_artista = 23),
    23,
    'Juno',
    '00:03:43',
    'Pop',
    2022
),
(
    (SELECT id FROM albuns WHERE titulo = 'Glow' AND id_artista = 24),
    24,
    'Only When I',
    '00:03:51',
    'Indie',
    2021
),
(
    (SELECT id FROM albuns WHERE titulo = 'Gangbusters Melody Club' AND id_artista = 25),
    25,
    'City Cook',
    '00:03:24',
    'Electro Swing',
    2024
);