CREATE TABLE users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

ALTER TABLE IF EXISTS public.users
    OWNER to "user";

GRANT ALL ON TABLE public.users TO "user";

CREATE TABLE IF NOT EXISTS tarefa (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    responsavel VARCHAR(100) NOT NULL,
    prioridade VARCHAR(10) NOT NULL CHECK (prioridade IN ('ALTA', 'MEDIA', 'BAIXA')),
    deadline DATE NOT NULL,
    situacao VARCHAR(20) NOT NULL DEFAULT 'EM_ANDAMENTO' CHECK (situacao IN ('EM_ANDAMENTO', 'CONCLUIDA'))
);

INSERT INTO users (id, login, password)
VALUES ('1', 'danilomicaias', '$2a$10$zHNU1HOqXD6dnLzJ414wcOzVhV4LF0c5XyUVKkvcGniGyZNtKoy2u')
ON CONFLICT (login) DO NOTHING;