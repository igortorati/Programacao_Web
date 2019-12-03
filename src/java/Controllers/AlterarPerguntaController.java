/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.LoginControl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utils.ServiceFactory;
/**
 *
 * @author sid
 */
@WebServlet(name = "AlterarPerguntaController", urlPatterns = {"/alterarPergunta.do"})
public class AlterarPerguntaController extends HttpServlet {


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
        LoginControl.checkLogin(request, response);
        Integer idTeste = Integer.parseInt(request.getParameter("idTeste"));
        Integer indice = Integer.parseInt(request.getParameter("indice"));
        try {
            Integer idPergunta = ServiceFactory.getPerguntaService().getIdPergunta(idTeste, indice);
            if(idPergunta != null){
                ServiceFactory.getPerguntaService().deletarPergunta(idPergunta);
                ServiceFactory.getPerguntaService().updateIndiceAoDeletar(idTeste, indice);
            }
            response.sendRedirect(request.getContextPath()+"/TesteController.do?id="+idTeste);
        } catch (Exception e){
            PrintWriter out = response.getWriter();
            out.print(e);
        }
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
        //processRequest(request, response);
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
