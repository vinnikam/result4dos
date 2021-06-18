/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.vinni.result4dos.dao;

import java.util.List;



/**
 *
 * @author Vinni
 */
public interface Operaciones<T> {
    public int crear (T dato);
    public int editar (T dato);
    public int borrar (T dato);
    public T consultar (Long pk);
    public List<T> consultarAll ();
    
}
