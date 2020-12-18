/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

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
public class fachadaCompPedidoTest {
    
    static EJBContainer container;
    
    public fachadaCompPedidoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        } catch (Exception e) {
            System.out.println("ERROR" + e.toString());
            fail("Fallo al crear el contenedor embebido");
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    /**
     * Test of importeAbonar method, of class fachadaCompPedido.
     */
    //@Ignore
    @Test
    public void testImporteAbonar_Err() throws Exception {
        System.out.println("importeAbonar_Err");
        String nifcif = "NoExiste";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        fachadaCompPedidoLocal instance = (fachadaCompPedidoLocal)container.getContext().lookup("java:global/ejb-app/classes/fachadaCompPedido");
        float expResult = -1.0F;
        float result = instance.importeAbonar(nifcif);
        assertEquals(expResult, result, 0.0);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testImporteAbonar_NoErr_ConCompletados() throws Exception {
        System.out.println("importeAbonar_Err");
        String nifcif = "111111U";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        fachadaCompPedidoLocal instance = (fachadaCompPedidoLocal)container.getContext().lookup("java:global/ejb-app/classes/fachadaCompPedido");
        float expResult =  82533.0F;
        float result = instance.importeAbonar(nifcif);
        assertEquals(expResult, result, 0.0);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testImporteAbonar_NoErr_SinCompletados() throws Exception {
        System.out.println("importeAbonar_Err");
        String nifcif = "555555U";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        fachadaCompPedidoLocal instance = (fachadaCompPedidoLocal)container.getContext().lookup("java:global/ejb-app/classes/fachadaCompPedido");
        float expResult =  0.0F;
        float result = instance.importeAbonar(nifcif);
        assertEquals(expResult, result, 0.0);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addPedido method, of class fachadaCompPedido.
     */
    //@Ignore
    @Test
    public void testAddPedido_Err() throws Exception {
        System.out.println("addPedido");
        int cantidad = 3;
        int idConfiguracion = 8888; // Configuracion inexistente
        String nifcif = "555555U";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        fachadaCompPedidoLocal instance = (fachadaCompPedidoLocal)container.getContext().lookup("java:global/ejb-app/classes/fachadaCompPedido");
        Boolean result = instance.addPedido(cantidad, idConfiguracion, nifcif);
        assertFalse(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testAddPedido_NoErr() throws Exception {
        System.out.println("addPedido");
        int cantidad = 3;
        int idConfiguracion = 3333;
        String nifcif = "555555U";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        fachadaCompPedidoLocal instance = (fachadaCompPedidoLocal)container.getContext().lookup("java:global/ejb-app/classes/fachadaCompPedido");
        Boolean result = instance.addPedido(cantidad, idConfiguracion, nifcif);
        assertTrue(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delPedido method, of class fachadaCompPedido.
     */
    //@Ignore
    @Test
    public void testDelPedido_Err() throws Exception {
        System.out.println("delPedido");
        // No hay pedido del usuario 111111U con esa configuracion
        int idConfiguracion = 4444;
        String nifcif = "555555U";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        fachadaCompPedidoLocal instance = (fachadaCompPedidoLocal)container.getContext().lookup("java:global/ejb-app/classes/fachadaCompPedido");
        Boolean result = instance.delPedido(idConfiguracion, nifcif);
        assertFalse(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testDelPedido_NoErr() throws Exception {
        System.out.println("delPedido");
        int idConfiguracion = 2222;
        String nifcif = "555555U";
        //EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        fachadaCompPedidoLocal instance = (fachadaCompPedidoLocal)container.getContext().lookup("java:global/ejb-app/classes/fachadaCompPedido");
        Boolean result = instance.delPedido(idConfiguracion, nifcif);
        assertTrue(result);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
