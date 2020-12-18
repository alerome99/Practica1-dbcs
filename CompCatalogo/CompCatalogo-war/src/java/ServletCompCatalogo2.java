/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Despliegue.fachadaCompCatalogoRemote;
import Dominio.Configuracionpc;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alerome
 * @author pamarti
 */
@WebServlet(name = "ServletCompCatalogo", urlPatterns = {"/ServletCompCatalogo"})
public class ServletCompCatalogo2 extends HttpServlet {
    @EJB
    private fachadaCompCatalogoRemote fachadaCompCatalogo;


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Configuracionpc> catalogo = fachadaCompCatalogo.getCatalogo();
        
        List<Integer> descripcion = Arrays.asList(111,888,999,444,555,666);
        Boolean respuestaCreaConfiguracion = false;
        try{
            respuestaCreaConfiguracion = fachadaCompCatalogo.addConfiguracion(12, 12, 12,
                    12, 12, (short) 1, descripcion);
        } catch (Exception e) {
            System.err.println("En servlet");
        }
        Boolean respuestaCreaConfiguracion_ErrTipoCPU = fachadaCompCatalogo.addConfiguracion(12, 12, 12, 
                12, 12, (short) 8, descripcion);
        descripcion.set(2, 0);
        Boolean respuestaCreaConfiguracion_ErrDesc = fachadaCompCatalogo.addConfiguracion(12, 12, 12, 
                12, 12, (short) 1, descripcion);
        
        Boolean respuestaEditConfiguracion_editValor = fachadaCompCatalogo.editConfiguracion(2222, 4, 0, 0, 4, 0, (short) 0);
        Boolean respuestaEditConfiguracion_editCpu = fachadaCompCatalogo.editConfiguracion(3333, 0, 0, 0, 0, 0, (short) 2);
        Boolean respuestaEditConfiguracion_ErrIdConf = fachadaCompCatalogo.editConfiguracion(8888, 4, 0, 0, 4, 0, (short) 0);
        Boolean respuestaEditConfiguracion_ErrIdCPU = fachadaCompCatalogo.editConfiguracion(2222, 0, 0, 0, 0, 0, (short) 8);
        
        float precio_Err = fachadaCompCatalogo.getPrecioTotal(8888);
        float precio_NoErr = fachadaCompCatalogo.getPrecioTotal(1111);
        

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet pruebaCompCatalogo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Prueba componente catalogo</h1>");
            out.println("<h2> *** Operacion getcatalogo:");
            if (catalogo != null) {
                out.println("<p> Numero de productos: " + catalogo.size() + "</p>");
                out.println("<p> Velocidad CPU primer producto: " + catalogo.get(0).getVelocidadcpu());
                out.println("<p> Tablas asociadas. Tipo CPU: " + catalogo.get(0).getTipocpu().getNombretipocpu());
                out.println("<p> Tablas asociadas. Precio: " + catalogo.get(0).
                        getDescripcioncomponenteList().get(0).getPrecio() + "</p>");
            } else {
                out.println("<p> ERROR el catalogo esta vacio </p>");
            }
            out.println("<h2> *** Operacion addConfiguracion:");
            out.println("<p> Operacion exitosa. Respuesta: " + respuestaCreaConfiguracion + "</p>");
            out.println("<p> Operacion error en tipo CPU. Respuesta: " + respuestaCreaConfiguracion_ErrTipoCPU + "</p>");
            out.println("<p> Operacion error en descripcion. Respuesta: " + respuestaCreaConfiguracion_ErrDesc + "</p>");
            out.println("<h2> *** Operacion editConfiguracion:");
            out.println("<p> Operacion exitosa cambio valor. Respuesta: " + respuestaEditConfiguracion_editValor + "</p>");
            out.println("<p> Operacion exitosa cambio CPU. Respuesta: " + respuestaEditConfiguracion_editCpu + "</p>");
            out.println("<p> Operacion error en tipo CPU. Respuesta: " + respuestaEditConfiguracion_ErrIdCPU + "</p>");
            out.println("<p> Operacion error en id conf. Respuesta: " + respuestaEditConfiguracion_ErrIdConf + "</p>");
            out.println("<h2> *** Operacion getPrecio:");
            out.println("<p> Operacion con error. Respuesta: " + precio_Err + "</p>");
            out.println("<p> Operacion exitosa. Precio conf 1111: " + precio_NoErr + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
