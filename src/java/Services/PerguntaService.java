package Services;

import Models.Pergunta;
import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PerguntaService {
    
    public void criarPergunta(Pergunta pergunta) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Pergunta (PER_descricao, PER_codigo, PER_tipo," +
                                        "Teste_TES_idTeste, PER_indice) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, pergunta.getDescricao());
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
}
