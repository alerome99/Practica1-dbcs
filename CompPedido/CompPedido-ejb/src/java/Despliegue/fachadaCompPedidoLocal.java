/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import javax.ejb.Local;

/**
 *
 * @author alerome
 * @author pamarti
 */
@Local
public interface fachadaCompPedidoLocal {

    float importeAbonar(String nifcif);

    boolean addPedido(int cantidad, int idConfiguracion, String nifcif);

    boolean delPedido(int idConfiguracion, String nifcif);
    
}
