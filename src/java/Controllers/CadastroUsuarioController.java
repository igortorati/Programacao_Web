/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Usuario;
import Utils.ServiceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.CodigoUnico;

/**
 *
 * @author igort
 */
@WebServlet(name = "CadastroUsuarioController", urlPatterns = {"/CadastroUsuarioController.do"})
public class CadastroUsuarioController extends HttpServlet {

    
    
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
        response.setContentType("text/html;charset=UTF-8");
        CodigoUnico codigoUnico = (CodigoUnico) request.getSession().getAttribute("code");
        System.out.println(request.getParameter("email")+"|"+ Integer.parseInt(request.getParameter("age"))+"|"+ request.getParameter("phone")+"|"+ request.getParameter("gender")+"|"+ request.getParameter("zipcode")+"|"+ request.getParameter("color")+"|"+ request.getParameter("disease")+"|"+codigoUnico.getIdTeste()+"|"+codigoUnico.getIdCodigoUnico());
        Usuario usuario = new Usuario(request.getParameter("email"), Integer.parseInt(request.getParameter("age")), request.getParameter("phone"), request.getParameter("gender"), request.getParameter("zipcode"), request.getParameter("color"), request.getParameter("disease"),codigoUnico.getIdTeste(),codigoUnico.getIdCodigoUnico());
        PrintWriter out = response.getWriter();
        try  {
            if(codigoUnico.getIndice() == 0){
                ServiceFactory.getUsuarioService().salvarUsuario(usuario);
                codigoUnico.setIndice(1);
                ServiceFactory.getCodigoUnicoService().incrementarIndice(codigoUnico.getIdCodigoUnico());
                request.getSession().setAttribute("code", codigoUnico);
                response.sendRedirect(request.getContextPath()+"/respondenteController.do");
            } else {
                out.print("Você já inseriu seus dados");
            }
        } catch(Exception e) {
            out.print(e);
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
