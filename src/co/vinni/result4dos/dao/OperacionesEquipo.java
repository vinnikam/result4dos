/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.vinni.result4dos.dao;

import co.vinni.result4dos.dto.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinni
 */
public class OperacionesEquipo implements Operaciones<Equipo>{

    private final String sqlInsertar = "insert into equipos (eq_nombre, eq_entrenador) values(?, ?)";
    private final String sqlEditar = "update equipos set  eq_nombre = ? , eq_entrenador =?  where id = ?";
    private final String sqlBorrar = "delete from equipos where id = ?";
    private final String sqlConsultaPK = "select * from equipos where id = ?";
    private final String sqlConsultaNM = "select * from equipos where eq_nombre = ?";
    private final String sqlConsulta = "select * from equipos ";
    @Override
    public int crear(Equipo dato) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlInsertar);
                ps.setString(1, dato.getNombre());
                ps.setString(2, dato.getEntrenador());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al crear equipo", ex);
            } finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return 0;
        
    }

    @Override
    public int editar(Equipo dato) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlEditar);
                ps.setString(1, dato.getNombre());
                ps.setString(2, dato.getEntrenador());
                ps.setLong(3, dato.getId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al editar equipo", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return 0;
    }

    @Override
    public int borrar(Equipo dato) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlBorrar);
                ps.setLong(1, dato.getId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al borrar equipo", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return 0;
    }

    @Override
    public Equipo consultar(Long pk) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlConsultaPK);
                ps.setLong(1, pk);
                ResultSet rs = ps.executeQuery();
                
                
                if (rs.next()){
                    Equipo e = cargarEquipo(rs);
                    return e;
                }
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al consultar pk equipo", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return null;
    }
    public Equipo consultar(String nombre) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlConsultaNM);
                ps.setString(1, nombre);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Equipo e = cargarEquipo(rs);
                    return e;
                }
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al consultar nombre equipo", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return null;
    }
    @Override
    public List<Equipo> consultarAll() {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlConsulta);
                ResultSet rs = ps.executeQuery();
                List<Equipo> equipos = new ArrayList<>();
                while (rs.next()){
                    Equipo e = cargarEquipo(rs);
                    equipos.add(e);
                }
                return equipos;
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al consultar equipo", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return new ArrayList<>();
    }

    private Equipo cargarEquipo(ResultSet rs) throws SQLException {
        Equipo e = new Equipo();
        e.setId(rs.getLong("id"));
        e.setNombre(rs.getString("eq_nombre"));
        e.setEntrenador(rs.getString("eq_entrenador"));    
        return e;
    }
    
}
