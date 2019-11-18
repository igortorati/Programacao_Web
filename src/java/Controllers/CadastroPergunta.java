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
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author sid
 */
@WebServlet(name = "CadastroPergunta", urlPatterns = {"/cadastroPergunta.do"})
public class CadastroPergunta extends HttpServlet {

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
        Integer idTeste = Integer.parseInt(request.getParameter("idTeste"));
        Integer tipo = jsonObject.getInt("tipo");
        JSONArray jArray = jsonObject.getJSONArray("imagens");
        ArrayList<String> imagens = new ArrayList();
        for(int i = 0; i < jArray.length(); ++i){
           imagens.add(jArray.getString(i));
        }
        Integer codigo = jsonObject.getInt("codigo");
        String descricao = jsonObject.getString("descricao");
        
        if(codigo == 0 && descricao == null){
            out.print("Descrição é obrigatório");
        } else {
            try {
                Integer indice = ServiceFactory.getTesteService().getNovoIndicePergunta(idTeste);
                //salvando pergunta
                Pergunta pergunta = new Pergunta(descricao, tipo, codigo, idTeste, indice);
                ServiceFactory.getPerguntaService().criarPergunta(pergunta);
                //salvando imagens em pergunta has imagem
                Integer idPergunta = ServiceFactory.getPerguntaService().getIdPergunta(idTeste, indice);
                
                for(int i = 0; i < imagens.size(); ++i){
                    Integer idImagem = ServiceFactory.getImagemService().getIdByCaminho(imagens.get(i));
                    System.out.println(idImagem + " / " + imagens.get(i));
                    if(idImagem != null){
                        ServiceFactory.getPerguntaService().salvarImagemEmPergunta(idPergunta, idImagem, i);
                    }
                }
                
                out.print(true);
            } catch (Exception e) {
                out.print(e);
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
