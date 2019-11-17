package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.CodigoUnico;
import Utils.ServiceFactory;

/**
 *
 * @author sid
 */
@WebServlet(name = "FinalizarTeste", urlPatterns = {"/finalizarTeste.do"})
public class FinalizarTesteController extends HttpServlet {


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
        PrintWriter out = response.getWriter();
        CodigoUnico codigoUnico = (CodigoUnico) request.getSession().getAttribute("code");
        if(codigoUnico != null){
            //deletar codigo e sessao
            try {
                ServiceFactory.getCodigoUnicoService().deletarCodigoUnico(codigoUnico.getIdCodigoUnico());
                request.getSession().invalidate();
                out.print("Teste realizado com sucesso!");
            } catch (Exception e){
                out.print(e);
            }
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
