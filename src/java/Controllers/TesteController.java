/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Pergunta;
import Models.Pesquisador;
import Models.Teste;
import Utils.LoginControl;
import Utils.ServiceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igort
 */
@WebServlet(name = "TesteController", urlPatterns = {"/TesteController.do"})
public class TesteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code>
     * methods.
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
        Integer id = null;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
        }
        Teste teste = null;
        ArrayList<Pergunta> perguntas = null;
        try {
            if(id != null){
                teste = ServiceFactory.getTesteService().getTeste(id);
            }
            if(teste != null){
                perguntas = ServiceFactory.getTesteService().getPerguntas(id);
                request.setAttribute("perguntas", perguntas);
                request.setAttribute("teste", teste);
                RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp"); 
                dispatcher.forward(request, response);
            }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        LoginControl.checkLogin(request, response);
        PrintWriter out = response.getWriter();
        String erro = ""; //para validacao
        Pesquisador pesquisador = (Pesquisador) request.getSession().getAttribute("login");
        if(!(request.getParameter("description") instanceof String) || (request.getParameter("description").length() < 10)){
            erro += "Descrição é obrigatória<br>";
        }
        if(!(request.getParameter("name") instanceof String) || (request.getParameter("name").length() < 5)){
            erro += "Título é obrigatório<br>";
        }
        if(!erro.equals("")){
            request.setAttribute("erro", erro);
            RequestDispatcher dispatcher = request.getRequestDispatcher("create-test.jsp"); 
            dispatcher.forward(request, response);
        } else {
            try  {
                String descricao = request.getParameter("description");
                String titulo = request.getParameter("name");
                if(ServiceFactory.getTesteService().existeNomeTeste(titulo) == 1){
                    erro += "Título do teste já existe<br>";
                    request.setAttribute("erro", erro);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("create-test.jsp"); 
                    dispatcher.forward(request, response);
                } else {
                    //fazer a operação
                    Teste teste = new Teste(request.getParameter("description"), request.getParameter("name"), pesquisador.getId(), 0);
                    ServiceFactory.getTesteService().salvarTeste(teste);
                    Integer id = ServiceFactory.getTesteService().getId(teste);
                    if(id != null){
                        response.sendRedirect(request.getContextPath()+"/TesteController.do?id="+id);
                    }
                }
            } catch(Exception e) {
                response.sendRedirect(request.getContextPath()+"/error500.html");
            }
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
