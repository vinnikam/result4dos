/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.vinni.result4dos.dao;

import co.vinni.result4dos.dto.Equipo;
import co.vinni.result4dos.dto.Partido;
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
public class OperacionesPartido implements Operaciones<Partido>{
    private final String sqlInsertar = "insert into partidos "
            + "(pa_fecha, pa_elocal_id, pa_local_gol, pa_evisitante_id,  pa_visitante_gol) values(?, ?, ?, ?, ?)";
    private final String sqlEditar = "update partidos set  pa_local_gol = ? , pa_visitante_gol =?  where id = ?";
    private final String sqlBorrar = "delete from partidos where id = ?";
    private final String sqlConsultaPK = "select * from partidos where id = ?";
    private final String sqlConsulta = "select * from partidos ";
    private final String sqlConsultaOrder = "select * from partidos order by pa_fecha desc";
    @Override
    public int crear(Partido dato) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlInsertar);
                
                ps.setDate(1, new java.sql.Date(dato.getFecha().getTime()));
                ps.setLong(2, dato.getEquipoLocal().getId());
                ps.setLong(3, dato.getGolesLocal());
                ps.setLong(4, dato.getEquipoVisitante().getId());
                ps.setLong(5, dato.getGolesVisitante());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al crear partido", ex);
            } finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return 0;
        
    }

    @Override
    public int editar(Partido dato) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlEditar);
                ps.setLong(1, dato.getGolesLocal());
                ps.setLong(2, dato.getGolesVisitante());
                ps.setLong(3, dato.getId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al editar partido", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return 0;
    }

    @Override
    public int borrar(Partido dato) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlBorrar);
                ps.setLong(1, dato.getId());
                return ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al borrar partido", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return 0;
    }

    @Override
    public Partido consultar(Long pk) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sqlConsultaPK);
                ps.setLong(1, pk);
                ResultSet rs = ps.executeQuery();
                
                
                if (rs.next()){
                    Partido e = cargarPartido(rs);
                    return e;
                }
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al consultar pk partido", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return null;
    }

    @Override
    public List<Partido> consultarAll() {
        return consultarAll(sqlConsulta);
    }
        
    public List<Partido> consultarAllOrder() {
        return consultarAll(sqlConsultaOrder);
    }
    private List<Partido> consultarAll(String sql) {
        ManejadorConexiones mc = new ManejadorConexiones();
        Connection conActiva = mc.conectarse();
        if (conActiva != null){
            try {
                PreparedStatement ps = conActiva.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List<Partido> partidos = new ArrayList<>();
                while (rs.next()){
                    Partido e = cargarPartido(rs);
                    partidos.add(e);
                }
                return partidos;
            } catch (SQLException ex) {
                Logger.getLogger(OperacionesEquipo.class.getName()).log(Level.SEVERE, "Error al consultar partido", ex);
            }finally{
                mc.desConectarse(conActiva);
            }
            
        }
        return new ArrayList<>();
    }
    private Partido cargarPartido(ResultSet rs) throws SQLException {
        Partido e = new Partido();
        e.setId(rs.getLong("id"));//pa_fecha, pa_elocal_id, pa_local_gol, pa_evisitante_id,  pa_visitante_gol
        e.setFecha(rs.getDate("pa_fecha"));
        e.setGolesLocal(rs.getInt("pa_local_gol"));
        e.setGolesVisitante(rs.getInt("pa_visitante_gol"));
        Long idEquipoLocal = rs.getLong("pa_elocal_id");
        OperacionesEquipo oper = new OperacionesEquipo();
        if (idEquipoLocal >0){
            e.setEquipoLocal(oper.consultar(idEquipoLocal));
        }
        Long idEquipoVisitante = rs.getLong("pa_evisitante_id");
        if (idEquipoVisitante >0){
            e.setEquipoVisitante(oper.consultar(idEquipoVisitante));
        }
        return e;
    }    
    
}
