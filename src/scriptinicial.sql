/**
 * Author:  Vinni
 * Created: 18/06/2021
 */
-- DATOS DE CREACION DE USUARIO Y BASE DE DATOS. 
create database `bd_campeonato`;
CREATE USER 'elusuario' IDENTIFIED BY 'laclave';
GRANT USAGE ON *.* TO 'elusuario'@'%' IDENTIFIED BY 'laclave';
GRANT ALL privileges ON `bd_campeonato`.* TO 'elusuario'@'%';

CREATE TABLE equipos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    eq_nombre VARCHAR(100) UNIQUE,
    eq_entrenador VARCHAR(150) 
);
create table partidos(
    id BIGINT auto_increment,
    pa_fecha date,
    pa_elocal_id bigint,
    pa_local_gol bigint,
    pa_evisitante_id bigint,
    pa_visitante_gol bigint,
    primary key(id),
    constraint  fk_plocal   foreign key(pa_elocal_id) 
        references equipos(id),
    constraint  fk_pvisitante   foreign key(pa_evisitante_id) 
        references equipos(id)

);
