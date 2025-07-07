INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url) VALUES ('test', 'test@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url')
INSERT INTO tb_profile(nome, sub, bio, link_lattes, link_github, link_linkedin, photo_url) VALUES ('test2', 'test2@gmail.com', 'apenas um test', 'link lattes', 'link git', 'link linkedin', 'foto url')

INSERT INTO tb_laboratorio (nome, descricao_curta, descricao_longa, gradient_accent, logo_url) VALUES ('Telemidia', 'descricao curta', 'descricao longa', 'GREEN', 'logo do lab');

INSERT INTO tb_profile_laboratorio (profile_id, lab_id, ativo, profile_type, admin) VALUES (1, 1, true, 'PROFESSOR', true);