-- Criação das tabelas (se ainda não existirem)
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS artistas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS albuns (
    id SERIAL PRIMARY KEY,
    id_artista INTEGER NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    data_lancamento DATE,
    FOREIGN KEY (id_artista) REFERENCES artistas(id)
);

CREATE TABLE IF NOT EXISTS musicas (
    id SERIAL PRIMARY KEY,
    id_album INTEGER NOT NULL,
    id_artista INTEGER NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    duracao INTERVAL NOT NULL,
    genero VARCHAR(100),
    data_lancamento DATE,
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
(5, 'The Marías');

-- Insere álbuns
INSERT INTO albuns (id, id_artista, titulo, data_lancamento) VALUES
(1, 1, 'Construção', '1971-01-01'),  -- Chico Buarque
(2, 2, 'O Descobridor Dos Sete Mares', '1983-01-01'),    -- Tim Maia
(3, 3, 'In Utero', '1993-09-21'),    -- Nirvana
(4, 4, 'Ella Fitzgerald Sings the Cole Porter Songbook', '1956-01-01'), -- Ella
(5, 5, 'Submarine', '2021-04-30');      -- The Marías


-- Insere músicas
INSERT INTO musicas (id_album, id_artista, titulo, duracao, genero, data_lancamento) VALUES
(
    (SELECT id FROM albuns WHERE titulo = 'Construção' AND id_artista = 1),
    1,
    'Construção',
    '00:06:23',
    'MPB',
    '1971-01-01'
),
(
    (SELECT id FROM albuns WHERE titulo = 'Tim Maia' AND id_artista = 2),
    2,
    'O Descobridor Dos Sete Mares',
    '00:04:25',
    'Soul',
    '1983-01-01'
),
(
    (SELECT id FROM albuns WHERE titulo = 'In Utero' AND id_artista = 3),
    3,
    'Heart-Shaped Box',
    '00:04:40',
    'Rock',
    '1993-09-21'
),
(
    (SELECT id FROM albuns WHERE titulo = 'Ella Fitzgerald Sings the Cole Porter Songbook' AND id_artista = 4),
    4,
    'Night and Day',
    '00:03:30',
    'Jazz',
    '1956-01-01'
),
(
    (SELECT id FROM albuns WHERE titulo = 'Cinema' AND id_artista = 5),
    5,
    'Hamptons',
    '00:04:00',
    'Indie Pop',
    '2021-04-30'
);
