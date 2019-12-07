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
import java.util.ArrayList;

/**
 *
 * 
 * @author sid
 */
@WebServlet(name = "Codigo", urlPatterns = {"/codigo.do"})
public class CodigoController extends HttpServlet {
    private final int qtd = 7;
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
        Integer id = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        try {
            ArrayList<String> codigos = ServiceFactory.getCodigoUnicoService().getCodigos(id);
            String formatCod = "";
            for(int i = 0; i < codigos.size(); ++i){
                formatCod += codigos.get(i);
                if(i < (codigos.size()-1)){
                    formatCod += ",";
                }
            }
            out.print(formatCod);
        } catch(Exception e){
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
        request.setCharacterEncoding("UTF-8");
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            ServiceFactory.getCodigoUnicoService().gerarCodigos(id, qtd);
            response.sendRedirect(request.getContextPath()+"/codigo.do?id="+id);
        } catch(Exception e){
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
