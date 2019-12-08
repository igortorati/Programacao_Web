package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.CodigoUnico;
import Utils.ServiceFactory;
import javax.servlet.RequestDispatcher;
import Models.Pergunta;
import java.util.ArrayList;
/**
 *
 * @author sid
 */
@WebServlet(displayName = "RespondenteController", urlPatterns = {"/respondenteController.do"})
public class RespondenteController extends HttpServlet {


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
        request.setCharacterEncoding("UTF-8");
        CodigoUnico codigoUnico = (CodigoUnico) request.getSession().getAttribute("code");
        String erro = request.getParameter("erro");
        if(erro != null && !erro.equals("")){
            request.setAttribute("erro", erro);
        }
        PrintWriter out = response.getWriter();
        if(codigoUnico != null){
            if(codigoUnico.getIndice() == 0){
                RequestDispatcher dispatcher = request.getRequestDispatcher("userInformation.jsp");
                dispatcher.forward(request, response);
            } else {
                try {
                    Pergunta pergunta = ServiceFactory.getPerguntaService().getPergunta(codigoUnico.getIdTeste(), codigoUnico.getIndice());
                    if(pergunta != null){
                        request.setAttribute("pergunta", pergunta);
                        Integer idPergunta = ServiceFactory.getPerguntaService().getIdPergunta(pergunta.getIdTeste(), pergunta.getIndice());
                        ArrayList<String> imagens = ServiceFactory.getPerguntaService().getImagensEmPergunta(idPergunta);
                        if(imagens != null){
                            request.setAttribute("imagens", imagens);
                        }
                        RequestDispatcher dispatcher = request.getRequestDispatcher("question.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath()+"/finalizarTeste.do");
                    }
                } catch (Exception e){
                    response.sendRedirect(request.getContextPath()+"/error500.html");
                }
            }
        } else {
            out.print("Não autorizado");
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
