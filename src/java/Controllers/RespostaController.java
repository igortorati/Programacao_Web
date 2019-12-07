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
import Utils.ServiceFactory;
import Models.Resposta;
import Models.CodigoUnico;
/**
 *
 * @author sid
 */
@WebServlet(name = "Resposta", urlPatterns = {"/resposta.do"})
public class RespostaController extends HttpServlet {

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
        //processRequest(request, response);
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
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        if((request.getParameter("ordinal") != null) || (request.getParameter("continua") != null)){
            Integer respOrdinal = null;
            Double respContinua = null;
            if(request.getParameter("ordinal") != null){
                    respOrdinal = Integer.parseInt(request.getParameter("ordinal"));
            } else {
                respContinua = Double.parseDouble(request.getParameter("continua"));
            }
            
            String idItem = request.getParameter("idItem");
            try {
                CodigoUnico codigoUnico = (CodigoUnico) request.getSession().getAttribute("code");
                if(codigoUnico != null){
                    Integer idPergunta = ServiceFactory.getPerguntaService().getIdPergunta(codigoUnico.getIdTeste(), codigoUnico.getIndice());
                    if(idPergunta != null){
                        Integer idUsuario = ServiceFactory.getUsuarioService().getIdUsuarioByCodigoUnico(codigoUnico.getIdCodigoUnico());
                        if(idUsuario != null){
                            Resposta resposta = new Resposta(respContinua, respOrdinal, idItem, idUsuario, idPergunta);
                            
                            ServiceFactory.getRespostaService().salvarResposta(resposta);
                            //incrementando indice
                            codigoUnico.setIndice(codigoUnico.getIndice()+1);
                            ServiceFactory.getCodigoUnicoService().incrementarIndice(codigoUnico.getIdCodigoUnico());
                            request.getSession().setAttribute("code", codigoUnico);
                            //direcionando para controle de perguntas
                            response.sendRedirect(request.getContextPath()+"/respondenteController.do");
                        } else {
                            out.print("Não existe essa pergunta");
                        }
                    } else {
                        out.print("Pergunta não existe");
                    }
                } else {
                    out.print("Sem permissão");
                }
            } catch (Exception e){
                out.print(e);
            }
        } else {
            out.print("Responda o teste primeiro!");
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
