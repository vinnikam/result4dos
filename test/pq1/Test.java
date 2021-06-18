
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pq1;

import co.vinni.result4dos.dao.ManejadorConexiones;
import co.vinni.result4dos.dao.OperacionesEquipo;
import co.vinni.result4dos.dto.Equipo;
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
    

//    @org.junit.Test
//    public void verificaMetodo(){
//        utilidades.UtilidadesVarias valor = new UtilidadesVarias();
//        valor.sumar(sumando1, 0, c, Boolean.TRUE)
//        assertEquals(true , rta);
//    }    
}
