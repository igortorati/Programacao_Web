package Services;

import Models.Pergunta;
import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class PerguntaService {
    
    public void criarPergunta(Pergunta pergunta) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Pergunta (PER_descricao, PER_codigo, PER_tipo," +
                                        "Teste_TES_idTeste, PER_indice) VALUES (?, ?, ?, ?, ?)");
            if(pergunta.getCodigo() == 0){
                ps.setString(1, pergunta.getDescricao());
            } else {
                ps.setNull(1, Types.VARCHAR);
            }
            ps.setInt(2, pergunta.getCodigo());
            ps.setInt(3, pergunta.getTipo());
            ps.setInt(4, pergunta.getIdTeste());
            ps.setInt(5, pergunta.getIndice());
            ps.execute();
        } finally {
            if(ps != null){
                ps.close();
            }
            conn.close();
        }
    }
    
    public Pergunta getPergunta(Integer idTeste, Integer indice) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pergunta pergunta = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Pergunta WHERE Teste_TES_idTeste = ? AND PER_indice = ?");
            ps.setInt(1, idTeste);
            ps.setInt(2, indice);
            rs = ps.executeQuery();
            if(rs.first()){
                pergunta = new Pergunta(rs.getString("PER_descricao"), rs.getInt("PER_tipo"), rs.getInt("PER_codigo"),
                                        rs.getInt("Teste_TES_idTeste"), rs.getInt("PER_indice"));
            }
        } finally{
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            conn.close();
        }
        
        return pergunta;
    }
    
    public Boolean isIdValido(Pergunta pergunta) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean idValido = true;
        try {
            ps = conn.prepareStatement("SELECT PER_indice FROM Pergunta WHERE Teste_TES_idTeste = ?");
            ps.setInt(1, pergunta.getIdTeste());
            rs = ps.executeQuery();
            while(rs.next()){
                if(pergunta.getIndice() == rs.getInt("PER_indice")){
                    idValido = false;
                }
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
        return idValido;
    }
    
    public void alterarIndice(Integer idPergunta, Integer novoIndice) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE Pergunta SET PER_indice = ? WHERE PER_idPergunta = ?");
            ps.setInt(1, novoIndice);
            ps.setInt(2, idPergunta);
            ps.execute();
        } finally {
            if(ps != null){
                ps.close();
            }
            conn.close();
        }
    }
    
    public void alterarPergunta(Pergunta pergunta) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareCall("UPDATE Pergunta SET PER_descricao=?, PER_codigo=?, PER_tipo=? WHERE PER_idPergunta=? "
                    + "AND Teste_TES_idTeste=? ");
            ps.setString(1, pergunta.getDescricao());
            ps.setInt(2, pergunta.getCodigo());
            ps.setInt(3, pergunta.getTipo());
            ps.setInt(4, pergunta.getIdPergunta());
            ps.setInt(5, pergunta.getIdTeste());
            ps.execute();
        } finally {
            if(ps != null){
                ps.close();
            }
            conn.close();
        }
    }
    
    public Integer getIdPergunta(Integer idTeste, Integer indice) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            ps = conn.prepareStatement("SELECT PER_idPergunta FROM Pergunta WHERE Teste_TES_idTeste = ? AND PER_indice = ?");
            ps.setInt(1, idTeste);
            ps.setInt(2, indice);
            rs = ps.executeQuery();
            if(rs.first()){
                id = rs.getInt("PER_idPergunta");
            }
        } finally{
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
    
    public void salvarImagemEmPergunta(Integer idPergunta, Integer idImagem, Integer indice) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Pergunta_has_Imagem (Pergunta_PER_idPergunta, Imagem_IMG_idImagem, PHI_Indice) "
                    + "VALUES (?, ?, ?)");
            ps.setInt(1, idPergunta);
            ps.setInt(2, idImagem);
            ps.setInt(3, indice);
            ps.execute();
        } finally {
            if(ps != null){
                ps.close();
            }
            conn.close();
        }
    }
    
    public ArrayList<String> getImagensEmPergunta(Integer idPergunta) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> imagens = null;
        try {
            ps = conn.prepareStatement("SELECT IMG_endereco FROM Pergunta_has_Imagem as pi JOIN Imagem as i on (pi.Imagem_IMG_idImagem = i.IMG_idImagem)"
                    + "WHERE Pergunta_PER_idPergunta = ?");
            ps.setInt(1, idPergunta);
            rs = ps.executeQuery();
            System.out.println("teste");
            imagens = new ArrayList();
            while(rs.next()){
                System.out.println(rs.getString("IMG_endereco"));
                imagens.add(rs.getString("IMG_endereco"));
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
        
        return imagens;
    }
    
    public void deletarPergunta(Integer idPergunta) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE from Pergunta WHERE PER_idPergunta = ?");
            ps.setInt(1, idPergunta);
            ps.execute();
        } finally {
             if (ps != null) {
                ps.close();
            }
            conn.close();
        }
    }
    
    public void updateIndiceAoDeletar(Integer idTeste, Integer indice) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE Pergunta SET PER_indice = PER_indice-1 WHERE Teste_TES_idTeste = ? AND PER_indice > ?");
            ps.setInt(1, idTeste);
            ps.setInt(2, indice);
            ps.execute();
        } finally {
             if (ps != null) {
                ps.close();
            }
            conn.close();
        }
    }
}
