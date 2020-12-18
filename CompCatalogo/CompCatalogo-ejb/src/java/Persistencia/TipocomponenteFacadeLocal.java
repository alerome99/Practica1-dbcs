/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Tipocomponente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Propietario
 */
@Local
public interface TipocomponenteFacadeLocal {

    void create(Tipocomponente tipocomponente);

    void edit(Tipocomponente tipocomponente);

    void remove(Tipocomponente tipocomponente);

    Tipocomponente find(Object id);

    List<Tipocomponente> findAll();

    List<Tipocomponente> findRange(int[] range);

    int count();
    
}
