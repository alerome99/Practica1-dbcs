/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import Dominio.Empleado;
import Dominio.Empresa;
import Persistencia.EmpleadoFacadeLocal;
import Persistencia.EmpresaFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alerome
 * @author pamarti
 */

@Stateless
public class FachadaCompUsuario implements FachadaCompUsuarioLocal {
    @EJB
    private EmpleadoFacadeLocal empleadoFacade;
    @EJB
    private EmpresaFacadeLocal empresaFacade;
    
    /**
     * Obtiene la empresa a la que pertenece un usuario.
     * @param nifcif Cadena de caracteres que representa el NIF/CIF del usuario.
     * @return Empresa a la que pertence el usuario.
     */
    @Override
    public Empresa getEmpresa(String nifcif) {
        Empresa e = null;
        try{
            e = empresaFacade.find(nifcif);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return e;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /**
     * Obtiene una instancia de empleado a través de su NIF/CIF.
     * @param nifcif Cadena de caracteres que representa el NIF/CIF del usuario.
     * @return Instancia del empleado.
     */
    
    @Override
    public Empleado getEmpleado(String nifcif) {
        Empleado e = null;
        try{
            e = empleadoFacade.find(nifcif);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return e;
    }
}
