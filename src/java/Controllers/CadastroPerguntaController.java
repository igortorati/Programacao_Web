/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.Pergunta;
import Utils.ServiceFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author sid
 */
@WebServlet(name = "CadastroPergunta", urlPatterns = {"/cadastroPergunta.do"})
public class CadastroPerguntaController extends HttpServlet {

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
        Integer idTeste = Integer.parseInt(request.getParameter("idTeste"));
        Integer indice = Integer.parseInt(request.getParameter("indice"));
        try {
            Pergunta pergunta = ServiceFactory.getPerguntaService().getPergunta(idTeste, indice);
            request.setAttribute("pergunta", pergunta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit-question.jsp"); 
            dispatcher.forward(request, response);
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
