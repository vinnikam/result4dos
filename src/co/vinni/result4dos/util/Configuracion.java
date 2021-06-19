/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.vinni.result4dos.util;

import co.vinni.result4dos.dao.ManejadorConexiones;
import co.vinni.result4dos.dao.OperacionesEquipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinni
 */
public class Configuracion {
    public static final String bd = "bd_campeonato";
    public static final String usuario = "elusuario";
    public static String clave = "laclave";
    public static String crearbasdatos = "create database `bd_campeonato`";
    public static String crearusuario = "CREATE USER '"+usuario+"' IDENTIFIED BY '"+clave+"'";
    public static String creartabla1 = "CREATE TABLE equipos (\n" +
        "    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
        "    eq_nombre VARCHAR(100) UNIQUE,\n" +
        "    eq_entrenador VARCHAR(150) \n" +
        ")";
    public static String creartabla2 = "create table partidos(\n" +
        "    id BIGINT auto_increment,\n" +
        "    pa_fecha date,\n" +
        "    pa_elocal_id bigint,\n" +
        "    pa_local_gol bigint,\n" +
        "    pa_evisitante_id bigint,\n" +
        "    pa_visitante_gol bigint,\n" +
        "    primary key(id),\n" +
        "    constraint  fk_plocal   foreign key(pa_elocal_id) \n" +
        "        references equipos(id),\n" +
        "    constraint  fk_pvisitante   foreign key(pa_evisitante_id) \n" +
        "        references equipos(id)\n" +
        "\n" +
        ")";
    public static String permisos = "GRANT USAGE ON *.* TO '"+usuario+"'@'%' IDENTIFIED BY '"+clave+"'";
    public static String permiso2 = "GRANT ALL privileges ON `"+bd+"`.* TO '"+usuario+"'@'%'";
    

    public int iniciarBD(String usuario, String clave, String sql){
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse(usuario, clave, "test");
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sql);
                 return ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al ejecutar SQL", ex);
            } finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return 0;    
    }
}
