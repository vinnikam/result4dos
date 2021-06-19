/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.vinni.result4dos.dao;

import co.vinni.result4dos.util.Configuracion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinni
 */
public class ManejadorConexiones {
    public Connection conectarse(String usuario, String clave, String bd){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mariadb://127.0.0.1/"+bd, usuario, clave);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorConexiones.class.getName()).log(Level.SEVERE, "No se tiene el driver de la BD", ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorConexiones.class.getName()).log(Level.SEVERE, "Datos de conexion incorrectos. ", ex);
        }
        return null;
    }
    
    public Connection conectarse(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mariadb://127.0.0.1/"+Configuracion.bd, Configuracion.usuario, Configuracion.clave);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorConexiones.class.getName()).log(Level.SEVERE, "No se tiene el driver de la BD", ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorConexiones.class.getName()).log(Level.SEVERE, "Datos de conexion incorrectos. ", ex);
        }
        return null;
    }
    public void desConectarse(Connection cn){
        if (cn!= null) 
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorConexiones.class.getName()).log(Level.SEVERE, "No se puede cerrar la conexion ", ex);
            }
    }
    
    
}
