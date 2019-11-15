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
import Models.Pergunta;
import Utils.ServiceFactory;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author sid
 */
@WebServlet(name = "PerguntaController", urlPatterns = {"/pergunta.do"})
public class PerguntaController extends HttpServlet {


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
        response.setContentType("text/html;charset=UTF-8");
        LoginControl.checkLogin(request, response);
        Integer idTeste = Integer.parseInt(request.getParameter("idTeste"));
        Integer indice = Integer.parseInt(request.getParameter("indice"));
        try {
            Pergunta pergunta = ServiceFactory.getPerguntaService().getPergunta(idTeste, indice);
            if(pergunta != null){
                request.setAttribute("pergunta", pergunta);
                // dispatcher = request.getRequestDispatcher(".jsp"); 
                //dispatcher.forward(request, response);
                PrintWriter out = response.getWriter();
                out.println(request.getAttribute("pergunta"));
            }
        } catch(Exception e){
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
        response.setContentType("text/html;charset=UTF-8");
        LoginControl.checkLogin(request, response);
        Pergunta pergunta = new Pergunta(request.getParameter("descricao"), Integer.parseInt(request.getParameter("tipo")), 
                                        Integer.parseInt(request.getParameter("codigo")),
                                        Integer.parseInt(request.getParameter("idTeste")), 
                                        Integer.parseInt(request.getParameter("indice")));
        try {
            Boolean vaiCriar = ServiceFactory.getPerguntaService().isIdValido(pergunta);
            if(vaiCriar){
                ServiceFactory.getPerguntaService().criarPergunta(pergunta);
                response.sendRedirect(request.getContextPath()+"/pergunta.do?idTeste="+pergunta.getIdTeste()+"&indice="+pergunta.getIndice());
            }
        } catch (Exception e){
            PrintWriter out = response.getWriter();
            out.print(e);
        }
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
