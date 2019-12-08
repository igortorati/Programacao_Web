/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.ServiceFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author sid
 */
@WebServlet(name = "AlterarOrdemPerguntas", urlPatterns = {"/alterarOrdemPerguntas.do"})
public class AlterarOrdemPerguntasController extends HttpServlet {

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
        StringBuilder jb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
              jb.append(line);
        } catch (Exception e) {
            out.print(e);
        }
        JSONObject jsonObject =  new JSONObject(jb.toString());
        JSONArray jArray = jsonObject.getJSONArray("questions");
        ArrayList<Integer> perguntas = new ArrayList();
        for(int i = 0; i < jArray.length(); ++i){
            perguntas.add(jArray.getInt(i));
        }
        try {
            Integer idTeste = ServiceFactory.getPerguntaService().getTesteId(perguntas.get(0));
            if(!ServiceFactory.getTesteService().getOnceVisible(idTeste)){
                for(int i = 0; i < perguntas.size(); ++i){
                    ServiceFactory.getPerguntaService().alterarIndice(perguntas.get(i), i+1);
                }
            }
            
            out.print(true);
        } catch (Exception e) {
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
