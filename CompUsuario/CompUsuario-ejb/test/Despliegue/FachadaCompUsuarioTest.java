/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import Dominio.Empleado;
import Dominio.Empresa;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alerome
 * @author pamarti
 */
public class FachadaCompUsuarioTest {
    
    static EJBContainer container;
    
    public FachadaCompUsuarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
            try {
            container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        } catch (Exception e) {
            System.out.println("Error al crear el contenedor embebido " + e.toString());
            fail("Fallo al crear el contenedor embebido");
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        container.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEmpresa method, of class FachadaCompUsuario.
     */
    @Test
    public void testGetEmpresa_NULL() throws Exception {
        System.out.println("getEmpresa: no existe");
        String nifcif = "NoExiste";
        FachadaCompUsuarioLocal instance = (FachadaCompUsuarioLocal)container.getContext().lookup("java:global/classes/FachadaCompUsuario");
        Empresa expResult = null;
        Empresa result = instance.getEmpresa(nifcif);
        assertEquals("Empresa no existe",expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of getEmpresa method, of class FachadaCompUsuario.
     */
    @Test
    public void testGetEmpresa_NoNULL() throws Exception {
        System.out.println("getEmpresa: existe");
        String nifcif = "111111U";
        FachadaCompUsuarioLocal instance = (FachadaCompUsuarioLocal)container.getContext().lookup("java:global/classes/FachadaCompUsuario");
        Short expResult = 1;
        Empresa result = instance.getEmpresa(nifcif);
        assertEquals("Empresa existe y es cliente",expResult, result.getEscliente());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getEmpleado method, of class FachadaCompUsuario.
     */
    @Test
    public void testGetEmpleado_NULL() throws Exception {
        System.out.println("getEmpleado: no existe");
        String nifcif = "NoExiste";
        FachadaCompUsuarioLocal instance = (FachadaCompUsuarioLocal)container.getContext().lookup("java:global/classes/FachadaCompUsuario");
        Empleado expResult = null;
        Empleado result = instance.getEmpleado(nifcif);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of getEmpleado method, of class FachadaCompUsuario.
     */
    @Test
    public void testGetEmpleado_NoNULL_rol() throws Exception {
        System.out.println("getEmpleado: existe y es GerenteVentas");
        String nifcif = "444444U";
        FachadaCompUsuarioLocal instance = (FachadaCompUsuarioLocal)container.getContext().lookup("java:global/classes/FachadaCompUsuario");
        String expResult = "GerenteVentas";
        Empleado result = instance.getEmpleado(nifcif);
        assertEquals("Empleado es GerenteVentas",expResult, result.getRol().getNombrerol());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of getEmpleado method, of class FachadaCompUsuario.
     */
    @Test
    public void testGetEmpleado_NoNULL_password() throws Exception {
        System.out.println("getEmpleado: existe y comprobamos su password");
        String nifcif = "222222U";
        FachadaCompUsuarioLocal instance = (FachadaCompUsuarioLocal)container.getContext().lookup("java:global/classes/FachadaCompUsuario");
        String expResult = "222";
        Empleado result = instance.getEmpleado(nifcif);
        assertEquals("Empleado es GerenteVentas",expResult, result.getUsuario().getPassword());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
