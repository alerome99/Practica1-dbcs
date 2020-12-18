/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import Dominio.Estadoventapcs;
import Dominio.Pedidopc;
import Dominio.Configuracionpc;
import Persistencia.EstadoventapcsFacadeLocal;
import Persistencia.PedidopcFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alerome
 * @author pamarti
 */
@Stateless
public class fachadaCompPedido implements fachadaCompPedidoLocal {
    @EJB
    private PedidopcFacadeLocal pedidopcFacade;
    @EJB
    private EstadoventapcsFacadeLocal estadoventapcsFacade;
    @EJB
    private fachadaCompCatalogoRemote fachadaCompCatalogo;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /**
     * Devuelve la cantidad que un usuario debe abonar.
     * @param nifcif Cadena de caracteres que representa el NIF/CIF del usuario.
     * @return Número en punto flotante que representa la cantidad que el usuario debe abonar.
     */
    
    @Override
    public float importeAbonar(String nifcif) {
        float importeTotal = 0.0F;
        /* Obtiene los pedidos asociados a una empresa a través de una NamedQuery */
        List<Pedidopc> pedidosEmpresa = pedidopcFacade.getPedidoByEncargadopor(nifcif);
        if(pedidosEmpresa==null || pedidosEmpresa.isEmpty()){
            return -1.0f;
        }
        for (Pedidopc pedidosEmpresa1 : pedidosEmpresa) {
            /* Para cada pedido comprueba que su estado es completado */
            if (pedidosEmpresa1.getEstado().getIdestadoventa() == 3) {
                int confPedido = pedidosEmpresa1.getConfiguracionsolicitada();
                /* Añade al precio final el importe de la configuración multiplicado por la cantidad solicitada */
                importeTotal+=(fachadaCompCatalogo.getPrecioTotal(confPedido)*pedidosEmpresa1.getCantidadsolicitada());
            }
        }
        return importeTotal;
    }

    /**
     * Añade un pedido a la base de datos.
     * @param cantidad Número entero que representa la cantidad de configuraciones que se han solicitado.
     * @param idConfiguracion Número entero que representa el identificador de una configuración de ordenador.
     * @param nifcif Cadena de caracteres que representa el NIF/CIF del usuario.
     * @return {code True} en caso de que la operación se haya completado con éxito y {@code false} en caso contrario.
     */
    @Override
    public boolean addPedido(int cantidad, int idConfiguracion, String nifcif) {
        /* 53 es una clave arbitraria seleccionada al azar */
        Pedidopc pedido = new Pedidopc(53);
        float precioConfiguracion = fachadaCompCatalogo.getPrecioTotal(idConfiguracion);
        /* Se comprueba que la configuración existe en la base de datos */
        if(precioConfiguracion==-1.0f){
            return false;
        }
        pedido.setCantidadsolicitada(cantidad);
        pedido.setEncargadopor(nifcif);
        pedido.setConfiguracionsolicitada(idConfiguracion);
        /* Obtenemos el estado del pedido en forma de Entity de la base de datos */
        Estadoventapcs estado = estadoventapcsFacade.find((short)1);
        pedido.setEstado(estado);
        try{
            pedidopcFacade.create(pedido);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Elimina un pedido de la base de datos.
     * @param idConfiguracion Número entero que representa el identificador de una configuración de ordenador.
     * @param nifcif Cadena de caracteres que representa el NIF/CIF del usuario.
     * @return {code True} en caso de que la operación se haya completado con éxito y {@code false} en caso contrario.
     */
    @Override
    public boolean delPedido(int idConfiguracion, String nifcif) {
        /* Obtiene los pedidos asociados a una empresa a través de una NamedQuery */
        List<Pedidopc> pedidosUsuario = pedidopcFacade.getPedidoByEncargadopor(nifcif);
        if(pedidosUsuario==null || pedidosUsuario.isEmpty()){
            return false;
        }
        for (Pedidopc pedidosUsuario1 : pedidosUsuario) {
            /* Se busca entre los pedidos del usuario y al encontrar el indicado, se elimina de la base de datos */
            if (pedidosUsuario1.getConfiguracionsolicitada() == idConfiguracion) {
                pedidopcFacade.remove(pedidosUsuario1);
                return true;
            }
        }
        return false;
    }
}
