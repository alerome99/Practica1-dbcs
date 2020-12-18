/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import Dominio.Empleado;
import Dominio.Empresa;
import javax.ejb.Local;

/**
 *
 * @author alerome
 * @author pamarti
 */
@Local
public interface FachadaCompUsuarioLocal {

    Empresa getEmpresa(String nifcif);

    Empleado getEmpleado(String nifcif);
    
}
