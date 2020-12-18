/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import Dominio.Configuracionpc;
import Dominio.Cpu;
import Dominio.Descripcioncomponente;
import Persistencia.ConfiguracionpcFacadeLocal;
import Persistencia.CpuFacadeLocal;
import Persistencia.DescripcioncomponenteFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alerome
 * @author pamarti
 */
@Stateless
public class fachadaCompCatalogo implements fachadaCompCatalogoRemote {
    @EJB
    private DescripcioncomponenteFacadeLocal descripcioncomponenteFacade;
    @EJB
    private CpuFacadeLocal cpuFacade;
    @EJB
    private ConfiguracionpcFacadeLocal configuracionpcFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /**
     * Obtiene todas las configuraciones que oferta la empresa.
     * @return Lista que contiene todas las configuraciones de ordenador existentes.
     */
    
    @Override
    public List<Configuracionpc> getCatalogo() {
        List<Configuracionpc> list = null;
        try{
            list = configuracionpcFacade.findAll();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return list;
    }

    /**
     *¨Creación e inserción en la base de datos de una nueva configuración de ordenador.
     * @param velCPU Número entero que representa la velocidad de la CPU.
     * @param capRAM Número entero que representa la capacidad de la memoria RAM.
     * @param capDD Número entero que representa la capacidad del disco.
     * @param velTarGraf Número entero que representa la velocidad de la tarjeta gráfica.
     * @param memTarGraf Número entero que representa la memoria de la tarjeta gráfica.
     * @param idTipoCPU Número que representa el tipo de CPU.
     * @param idsDescrComp Lista que contiene los identificadores de cada componente de la configuración.
     * @return {@code True} en caso de que la operación se realice con éxito y {@code false} en caso contrario.
     */
    @Override
    public Boolean addConfiguracion(int velCPU, int capRAM, int capDD, int velTarGraf, int memTarGraf, short idTipoCPU, List<Integer> idsDescrComp) {
        /*Algoritmo personal para generar el ID de la configuración */
        int id = 1000 * velCPU + 100 * capRAM + 10 * capDD + velTarGraf + memTarGraf;
        
        Configuracionpc cf = new Configuracionpc(id, velCPU, capRAM, capDD);
        cf.setMemoriatarjetagrafica(memTarGraf);
        cf.setVelocidadtarjetagrafica(velTarGraf);
        
        /* En caso de que la configuración de CPU introducida no exista, se retorna directamente */
        Cpu c = cpuFacade.find(idTipoCPU);
        if(c!=null){
           cf.setTipocpu(c); 
        }else{
            return false;
        }
        List<Descripcioncomponente> listaComps = new ArrayList<>();
        for (Integer idsDescrComp1 : idsDescrComp) {
            /* En caso de que alguno de los componentes dados no exista, se retorna directamente */
            Descripcioncomponente dc = descripcioncomponenteFacade.find(idsDescrComp1);
            if(dc!=null){
                listaComps.add(dc); 
            }else{
                return false;
            }                      
        }
        cf.setDescripcioncomponenteList(listaComps);
        try{
            configuracionpcFacade.create(cf);
        }catch(Exception ex){
            /* En caso de que la creación falle, se crea una excepción y se devuelve false */
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Modificación de los datos de una configuración de ordenador existente.
     * @param IdConfiguracion Número entero que representa el identificador de una configuración de ordenador específica.
     * @param velCPU Número entero que representa la velocidad de la CPU.
     * @param capRAM Número entero que representa la capacidad de la memoria RAM.
     * @param capDD Número entero que representa la capacidad del disco.
     * @param velTarGraf Número entero que representa la velocidad de la tarjeta gráfica.
     * @param memTarGraf Número entero que representa la memoria de la tarjeta gráfica.
     * @param idTipoCPU Número que representa el tipo de CPU.
     * @return {@code True} en caso de que la operación se realice con éxito y {@code false} en caso contrario.
     */
    @Override
    public Boolean editConfiguracion(int IdConfiguracion, int velCPU, int capRAM, int capDD, int velTarGraf,int memTarGraf, short idTipoCPU){
        Configuracionpc cf = configuracionpcFacade.find(IdConfiguracion);
        /* En caso de que la configuracion de ordenador no exista, se retorna directamente false */
        if(cf==null){
            return false;
        }
        
        /* Un valor de cero en el tipo de CPU, indica que no se debe modificar.
           Si es distinto de cero, se debe comprobar que dicha CPU exista. De no existir, se retorna directamente false */
        short zero = 0;
        if(idTipoCPU!=zero){
            Cpu c = cpuFacade.find(idTipoCPU);          
            if(c==null){
                return false;
            }
            cf.setTipocpu(c);
        }
        cf.setMemoriatarjetagrafica(memTarGraf);
        cf.setVelocidadcpu(velCPU);
        cf.setCapacidadram(capRAM);
        cf.setCapacidaddd(capDD);
        cf.setVelocidadtarjetagrafica(velTarGraf);
        try{
            configuracionpcFacade.edit(cf);
        }catch(Exception ex){
            /* En caso de que la edición falle, se crea una excepción y se devuelve false */
            System.err.println(ex.getMessage());
            return false;
        }
        return true;       
    }

    /**
     * Obtiene el precio total de una configuración de ordenador.
     * @param idConfiguracion Número entero que representa el identificador de una configuración de ordenador.
     * @return Número en punto flotante que representa el precio de una configuración de ordenador.
     */
    @Override
    public float getPrecioTotal(int idConfiguracion) {
        float precioTotal;
        precioTotal = 0.0f;
        Configuracionpc cf = configuracionpcFacade.find(idConfiguracion);
        /* En caso de que la configuracion de ordenador no exista, se retorna directamente false */
        if(cf==null){
            return -1.0f;
        }
        List<Descripcioncomponente> listaComps = cf.getDescripcioncomponenteList();
        if(listaComps.isEmpty()){
            return -1.0f;
        }
        for (Descripcioncomponente listaComp : listaComps) {
            precioTotal += listaComp.getPrecio();                   
        }
        return precioTotal;
    }
    
    
    
    
    
    
}
