/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Usuario;
import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author igort
 */
public class UsuarioService {
    
    public void salvarUsuario(Usuario usuario) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO `progweb`.`Usuario`(`USU_idUsuario`,`USU_contato`,`USU_email`,`USU_idade`,`USU_sexo`,`USU_cep`,`USU_cor`,`USU_enfermidade`,`Teste_TES_idTeste`,`USU_codigo_unico_teste`)VALUES(null,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, usuario.getContato());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getIdade());
            ps.setString(4, usuario.getSexo());
            ps.setString(5, usuario.getCep());
            ps.setString(6, usuario.getCor());
            ps.setString(7, usuario.getEnfermidade());
            ps.setInt(8, usuario.getIdTeste());
            ps.setString(9, usuario.getCodigo());
            ps.execute();
        } finally {
            if(ps != null){
                ps.close();
            }
            conn.close();
        }
    }
    
    public Integer getIdUsuarioByCodigoUnico(String codigoUnico) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            ps = conn.prepareStatement("SELECT USU_idUsuario FROM Usuario WHERE USU_codigo_unico_teste = ?");
            ps.setString(1, codigoUnico);
            rs = ps.executeQuery();
            if(rs.first()){
                id = rs.getInt("USU_idUsuario");
            }
        } finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            conn.close();
        }
        return id;
    }
    
    public ArrayList<Integer> getIdsUsuarioByIdTeste(Integer idTeste) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Integer> ids = null;
        try {
            ps = conn.prepareStatement("SELECT USU_idUsuario FROM Usuario WHERE Teste_TES_idTeste = ?");
            ps.setInt(1, idTeste);
            rs = ps.executeQuery();
            ids = new ArrayList();
            while(rs.next()){
                ids.add(rs.getInt("USU_idUsuario"));
            }
        } finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            conn.close();
        }
        return ids;
    }
}
