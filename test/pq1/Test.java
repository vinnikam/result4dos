
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pq1;

import co.vinni.result4dos.dao.ManejadorConexiones;
import co.vinni.result4dos.dao.OperacionesEquipo;
import co.vinni.result4dos.dao.OperacionesPartido;
import co.vinni.result4dos.dto.Equipo;
import co.vinni.result4dos.dto.Partido;
import co.vinni.result4dos.util.Utilidades;
import com.github.javafaker.Faker;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;
import org.junit.Assert;



/**
 *
 * @author Vinni
 */

public class Test{
    

    @org.junit.Test
    public void verificaConexion(){
        ManejadorConexiones c = new ManejadorConexiones();
        Connection con = c.conectarse();
        
        Assert.assertNotNull(con);
    }
    @org.junit.Test
    public void consultaEquipos(){
        OperacionesEquipo oper = new OperacionesEquipo();
        List<Equipo> rta = oper.consultarAll();
        Assert.assertNotEquals(0, rta.size());
    }
    @org.junit.Test
    public void consultaEquipo(){
        OperacionesEquipo oper = new OperacionesEquipo();
        Equipo e = new Equipo();
        Faker faker = new Faker(new Locale("es"));
        e.setNombre(faker.esports().team());
        e.setEntrenador(faker.esports().player());
        int rta = oper.crear(e);        

        Equipo rtaE = oper.consultar(1L);
        Assert.assertNotNull(rtaE);
    }
    @org.junit.Test
    public void insertaEquipo(){
        OperacionesEquipo oper = new OperacionesEquipo();
        Equipo e = new Equipo();
        Faker faker = new Faker(new Locale("es"));
        e.setNombre(faker.esports().team());
        e.setEntrenador(faker.esports().player());
        int rta = oper.crear(e);
        Assert.assertEquals(1, rta);
    }
    
    @org.junit.Test
    public void editaEquipo(){
        OperacionesEquipo oper = new OperacionesEquipo();
        Equipo rtaE = oper.consultar(1L);
        Faker faker = new Faker(new Locale("es"));
        rtaE.setNombre(faker.esports().team());
        rtaE.setEntrenador(faker.esports().player());
        int rta = oper.editar(rtaE);
        Assert.assertEquals(1, rta);
    }
    @org.junit.Test
    public void pruebaBase(){
        OperacionesEquipo oper = new OperacionesEquipo();
        Equipo e = new Equipo();
        e.setNombre("UNAB");
        e.setEntrenador("Jorge");
        oper.crear(e);
        e = oper.consultar("UNAB");
        
        Equipo e1 = new Equipo();
        e1.setNombre("UIS");
        e1.setEntrenador("Julian");
        oper.crear(e1);
        e1 = oper.consultar("UIS");
        
        
        Partido p = new Partido();
        p.setFecha(Utilidades.obtenerFecha("dd/MM/yyyy", "12/06/2021"));
        p.setEquipoLocal(e);
        p.setEquipoVisitante(e1);
        
       p.setGolesLocal(3);
       p.setGolesVisitante(2);
        OperacionesPartido oper1 = new OperacionesPartido();
        int rta = oper1.crear(p);
        System.out.println(p.toString());    
        String compara = "12-06-2021 - UNAB (3) VS (2) UIS";
        Assert.assertEquals(compara, p.toString());
    }
   
}
