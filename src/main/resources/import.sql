INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url, profile_type, id_lattes) VALUES ('test', 'test@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url', 'PROFESSOR', '8287861610873629')
INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url, profile_type) VALUES ('test2', 'test2@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url', 'ALUNO')
INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url, profile_type) VALUES ('test3', 'test3@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url', 'ALUNO')
INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url, profile_type) VALUES ('test4', 'test4@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url', 'ALUNO')
INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url, profile_type) VALUES ('test5', 'test5@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url', 'ALUNO')
INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url, profile_type) VALUES ('lucas', 'lucas@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url', 'ALUNO')
INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url, profile_type) VALUES ('maria', 'maria@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url', 'ALUNO')

INSERT INTO tb_laboratorio (nome, descricao_curta, descricao_longa, gradient_accent, logo_url) VALUES ('Telemidia', 'descricao curta', 'descricao longa', 'ROSE', 'logo do lab');
INSERT INTO tb_laboratorio (nome, descricao_curta, descricao_longa, gradient_accent, logo_url) VALUES ('VipLab', 'descricao curta', 'descricao longa', 'GREEN', 'logo do lab');

INSERT INTO tb_profile_laboratorio (profile_id, lab_id, ativo, lab_role, admin) VALUES (1, 1, true, 'OWNER', true);
INSERT INTO tb_profile_laboratorio (profile_id, lab_id, ativo, lab_role, admin) VALUES (7, 2, true, 'OWNER', true);
INSERT INTO tb_profile_laboratorio (profile_id, lab_id, ativo, lab_role, admin) VALUES (7, 1, true, 'ADMIN', true);
INSERT INTO tb_profile_laboratorio (profile_id, lab_id, ativo, lab_role, admin) VALUES (6, 1, true, 'ADMIN', true);
INSERT INTO tb_profile_laboratorio (profile_id, lab_id, ativo, lab_role, admin) VALUES (2, 1, true, 'ADMIN', true);
INSERT INTO tb_profile_laboratorio (profile_id, lab_id, ativo, lab_role, admin) VALUES (3, 1, false, 'MEMBER', true);
INSERT INTO tb_profile_laboratorio (profile_id, lab_id, ativo, lab_role, admin) VALUES (4, 1, true, 'MEMBER', true);

INSERT INTO tb_post (titulo, conteudo, visibilidade, instante, lab_id, profile_id) VALUES ('test', 'meu primeiro post', 'PUBLICO', '2025-07-10T14:30:00', 1, 1)
INSERT INTO tb_post (titulo, conteudo, visibilidade, instante, lab_id, profile_id) VALUES ('test', 'meu segundo post', 'PRIVADO', '2025-07-10T14:31:00', 1, 1)
INSERT INTO tb_post (titulo, conteudo, visibilidade, instante, lab_id, profile_id) VALUES ('test', 'meu terceiro post', 'PUBLICO', '2025-07-10T14:32:00', 1, 1)

INSERT INTO tb_evento (titulo, descricao, local, instante, data_evento, profile_id, lab_id) VALUES ('evento', 'meu primeiro evento', 'ufma', '2025-07-10T14:30:00', '2025-08-10T14:30:00', 1, 1)
INSERT INTO tb_evento (titulo, descricao, local, instante, data_evento, profile_id, lab_id) VALUES ('evento', 'meu segundo evento', 'ufma', '2025-07-10T14:30:00', '2025-09-10T14:30:00', 1, 1)
INSERT INTO tb_evento (titulo, descricao, local, instante, data_evento, profile_id, lab_id) VALUES ('evento', 'meu terceiro evento', 'ufma', '2025-07-10T14:30:00', '2024-09-10T14:30:00', 1, 1)
INSERT INTO tb_evento (titulo, descricao, local, instante, data_evento, profile_id, lab_id) VALUES ('evento', 'meu quarto evento', 'ufma', '2025-07-10T14:30:00', '2025-09-10T14:30:00', 1, 2)

INSERT INTO tb_material (titulo, descricao, link, visibilidade, tipo, lab_id) VALUES ('material 1', 'descricao', 'link', 'PUBLICO', 'PDF', 1)
INSERT INTO tb_material (titulo, descricao, link, visibilidade, tipo, lab_id) VALUES ('material 2', 'descricao', 'link', 'PUBLICO', 'PDF', 2)
INSERT INTO tb_material (titulo, descricao, link, visibilidade, tipo, lab_id) VALUES ('material 3', 'descricao', 'link', 'PUBLICO', 'SLIDE', 1)
INSERT INTO tb_material (titulo, descricao, link, visibilidade, tipo, lab_id) VALUES ('material 4', 'descricao', 'link', 'PRIVADO', 'SLIDE', 1)