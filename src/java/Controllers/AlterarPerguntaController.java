/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Pergunta;
import Utils.LoginControl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utils.ServiceFactory;
import java.io.BufferedReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
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
        request.setCharacterEncoding("UTF-8");
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
        Integer indice = Integer.parseInt(request.getParameter("indice"));
        Integer tipo = jsonObject.getInt("tipo");
        JSONArray jArray = jsonObject.getJSONArray("imagens");
        ArrayList<String> imagens = new ArrayList();
        for(int i = 0; i < jArray.length(); ++i){
           imagens.add(jArray.getString(i));
        }
        Integer codigo = jsonObject.getInt("codigo");
        String descricao = null;
        if(jsonObject.has("descricao")){
            descricao = jsonObject.getString("descricao");
        }
        if(codigo == 0 && descricao == null){
            out.print("Descrição é obrigatório");
        } else {
            try {
                Integer idPergunta = ServiceFactory.getPerguntaService().getIdPergunta(idTeste, indice);
                Pergunta pergunta = new Pergunta(descricao, tipo, codigo, idPergunta, idTeste, indice);
                //alterando pergunta
                ServiceFactory.getPerguntaService().alterarPergunta(pergunta);
                //alterando imagens
                for(int i = 0; i < imagens.size(); ++i){
                    Integer idImagem = ServiceFactory.getImagemService().getIdByCaminho(imagens.get(i));
                    if(idImagem != null){
                        if(ServiceFactory.getPerguntaService().existeImagem(idPergunta, idImagem, i) == 0){
                            System.out.println("teste");
                            ServiceFactory.getPerguntaService().deletarImagemEmPergunta(idPergunta, i);
                            ServiceFactory.getPerguntaService().salvarImagemEmPergunta(idPergunta, idImagem, i);
                        }
                    }
                }
                out.print(true);
            } catch (Exception e){
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
