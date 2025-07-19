-- =================================================================
-- V1__create_schema.sql
-- =================================================================

-- 1) Tabela de perfis
CREATE TABLE tb_profile (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nome VARCHAR(255),
  sub VARCHAR(255) UNIQUE,
  bio TEXT,
  link_lattes VARCHAR(255),
  link_github VARCHAR(255),
  link_linkedin VARCHAR(255),
  id_lattes VARCHAR(255),
  photo_url VARCHAR(255),
  profile_type VARCHAR(20)
);

-- 2) Laboratórios
CREATE TABLE tb_laboratorio (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nome VARCHAR(255),
  descricao_curta TEXT,
  descricao_longa TEXT,
  gradient_accent VARCHAR(20),    -- antes era gradient_accent ENUM
  logo_url VARCHAR(255)
);

-- 3) Associação Profile ⇆ Laboratório
CREATE TABLE tb_profile_laboratorio (
  lab_id BIGINT NOT NULL,
  profile_id BIGINT NOT NULL,
  ativo BOOLEAN,
  lab_role VARCHAR(20),
  admin BOOLEAN,
  PRIMARY KEY (lab_id, profile_id),
  FOREIGN KEY (lab_id)     REFERENCES tb_laboratorio(id),
  FOREIGN KEY (profile_id) REFERENCES tb_profile(id)
);

-- 4) Eventos
CREATE TABLE tb_evento (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  titulo VARCHAR(255),
  descricao TEXT,
  data_evento TIMESTAMP,
  instante TIMESTAMP,
  local VARCHAR(255),
  lab_id BIGINT  REFERENCES tb_laboratorio(id),
  profile_id BIGINT  REFERENCES tb_profile(id)
);

-- 5) Materiais
CREATE TABLE tb_material (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  titulo VARCHAR(255),
  descricao VARCHAR(255),
  link VARCHAR(255),
  tipo VARCHAR(20),
  visibilidade VARCHAR(20),
  lab_id BIGINT REFERENCES tb_laboratorio(id)
);

-- 6) Posts
CREATE TABLE tb_post (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  titulo VARCHAR(255),
  conteudo TEXT,
  instante TIMESTAMP,
  visibilidade VARCHAR(20),
  lab_id BIGINT    REFERENCES tb_laboratorio(id),
  profile_id BIGINT    REFERENCES tb_profile(id)
);

-- 7) Orientações
CREATE TABLE tb_orientacao (
  id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  profile_id BIGINT NOT NULL REFERENCES tb_profile(id),
  nivel VARCHAR(255),
  titulo VARCHAR(255) NOT NULL,
  ano INTEGER,
  discente VARCHAR(255),
  instituicao VARCHAR(255),
  external_id INTEGER NOT NULL UNIQUE
);

-- 8) Patentes
CREATE TABLE tb_patente (
  id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  profile_id BIGINT NOT NULL REFERENCES tb_profile(id),
  tipo VARCHAR(255),
  titulo VARCHAR(255),
  codigo VARCHAR(255),
  data_deposito DATE,
  data_concessao DATE,
  external_id INTEGER NOT NULL UNIQUE
);

-- 9) Prêmios
CREATE TABLE tb_premio (
  id  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  profile_id BIGINT NOT NULL REFERENCES tb_profile(id),
  nome_premio VARCHAR(255) NOT NULL,
  entidade VARCHAR(255),
  ano INTEGER,
  external_id INTEGER NOT NULL UNIQUE
);

-- 10) Publicações
CREATE TABLE tb_publicacao (
  id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  profile_id BIGINT NOT NULL REFERENCES tb_profile(id),
  tipo VARCHAR(255) NOT NULL,
  titulo VARCHAR(255) NOT NULL,
  ano INTEGER,
  doi VARCHAR(255),
  issn_isbn_sigla VARCHAR(255),
  external_id INTEGER NOT NULL UNIQUE
);

-- Índices para acelerar JOINs
CREATE INDEX idx_evento_lab  ON tb_evento (lab_id);
CREATE INDEX idx_evento_user  ON tb_evento (profile_id);
CREATE INDEX idx_material_lab ON tb_material (lab_id);
CREATE INDEX idx_post_lab ON tb_post (lab_id);
CREATE INDEX idx_post_user ON tb_post (profile_id);
CREATE INDEX idx_orientacao_user ON tb_orientacao(profile_id);
CREATE INDEX idx_patente_user ON tb_patente (profile_id);
CREATE INDEX idx_premio_user ON tb_premio (profile_id);
CREATE INDEX idx_publicacao_user ON tb_publicacao(profile_id);
