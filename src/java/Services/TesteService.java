package Services;

import Models.Pergunta;
import Models.Teste;
import java.util.ArrayList;
import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.sort;
import java.util.Date;

public class TesteService {
    private static final int QTD_PAGINACAO = 7;


    public ArrayList<Teste> listaTestes(String nome, Integer pagina) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Teste> testes = null;
        try {
            if(nome != null){
                ps = conn.prepareStatement("SELECT * FROM Teste "
                        + "WHERE TES_titulo LIKE ? OR TES_descricao LIKE ? LIMIT ?,?");
               ps.setString(1, "%"+nome+"%");
               ps.setString(2, "%"+nome+"%");
               ps.setInt(3, pagina*QTD_PAGINACAO);
               ps.setInt(4, QTD_PAGINACAO);
            } else {
                ps = conn.prepareStatement("SELECT * FROM Teste "
                        + "LIMIT ?,?");
                ps.setInt(1, pagina*QTD_PAGINACAO);
                ps.setInt(2, QTD_PAGINACAO);
            }
            rs = ps.executeQuery();
            testes = new ArrayList();
            while(rs.next()){
                Teste teste = new Teste();
                teste.setId(rs.getInt("TES_idTeste"));
                teste.setTitulo(rs.getString("TES_titulo"));
                teste.setDescricao(rs.getString("TES_descricao"));
                teste.setVisibilidade(rs.getInt("TES_visibilidade"));
                teste.setCreatedAt(rs.getTimestamp("TES_createdAt"));

                testes.add(teste);
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

        return testes;
    }
    
    public Integer ultimaPagina() throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer ultimaPag = 0;
        try {
            ps = conn.prepareStatement("SELECT count(*) FROM Teste");
            rs = ps.executeQuery();
            if(rs.next()){
                ultimaPag= (rs.getInt("count(*)")/QTD_PAGINACAO);
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
        
        return ultimaPag;
    }
    
    public String gerarCSV(int idTeste)throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer ultimaPag = 0;
        String conteudo = "";
        try {
            ps = conn.prepareStatement("CALL answers_to_csv(?)");
            ps.setInt(1, idTeste);
            rs = ps.executeQuery();
            if(rs.next()){
                conteudo= rs.getString("@write_data");
            }
            System.out.println("contudo");
        } finally{
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
            conn.close();
        }
        return conteudo;
    }
    
    public void salvarTeste(Teste teste) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Teste (TES_titulo, TES_descricao, Pesquisador_PES_id, TES_visibilidade)" +
                    "VALUES (?, ?, ?, ?);");
            ps.setString(1, teste.getTitulo());
            ps.setString(2, teste.getDescricao());
            ps.setInt(3, teste.getIdPesquisador());
            ps.setInt(4, teste.getVisibilidade());
            ps.execute();
        } finally {
            if(ps != null){
                ps.close();
            }
            conn.close();
        }
    }

    public Integer getId(Teste teste) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            ps = conn.prepareStatement("SELECT TES_idTeste FROM Teste WHERE TES_titulo = ?");
            ps.setString(1, teste.getTitulo());
            rs = ps.executeQuery();
            if(rs.first()){
                id = rs.getInt("TES_idTeste");
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            conn.close();
        }
        return id;
    }

    public Teste getTeste(Integer id) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teste teste = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Teste WHERE TES_idTeste= ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                teste = new Teste(rs.getInt("TES_idTeste"), rs.getString("TES_descricao"), rs.getString("TES_titulo"), 
                        rs.getInt("Pesquisador_PES_id"), rs.getInt("TES_visibilidade"), rs.getDate("TES_createdAt"), 
                        rs.getDate("TES_updatedAt"));
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
        return teste;
    }
    
    public ArrayList<Pergunta> getPerguntas(Integer id) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        ArrayList<Pergunta> pergunta = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement("SELECT * from Pergunta WHERE Teste_TES_idTeste = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            pergunta = new ArrayList<>();
            while(rs.next()){
                Pergunta p = new Pergunta(rs.getString("PER_descricao"), rs.getInt("PER_tipo"), rs.getInt("PER_codigo"), rs.getInt("PER_idPergunta"), rs.getInt("Teste_TES_idTeste"), rs.getInt("PER_indice"),rs.getDate("PER_updatedAt"));
                pergunta.add(p);
            }   
            Collections.sort(pergunta);
            for(int i = 0; i < pergunta.size();++i){
                System.out.println(pergunta.get(i).getDescricao()+"|"+pergunta.get(i).getDescricao().length());
            }
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

            conn.close();
        }
        return pergunta;
    }
    
       public Integer getNovoIndicePergunta(Integer idTeste) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            ps = conn.prepareStatement("SELECT count(*) FROM Pergunta WHERE Teste_TES_idTeste = ?");
            ps.setInt(1, idTeste);
            rs = ps.executeQuery();
            if(rs.first()){
                id = rs.getInt("count(*)")+1;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

            conn.close();
        }
        
        return id;
    }
 
    public void alterarTeste(Teste teste) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE Teste SET TES_titulo=?, TES_descricao=? WHERE TES_idTeste=?");
            ps.setString(1, teste.getTitulo());
            ps.setString(2, teste.getDescricao());
            ps.setInt(3, teste.getId());
            ps.execute();
        } finally {
             if (ps != null) {
                ps.close();
            }
            conn.close();
        }
    }
    
    public Integer getVisibilidade(Integer idTeste) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer visibilidade = null;
        try {
            ps = conn.prepareStatement("SELECT TES_visibilidade FROM Teste WHERE TES_idTeste = ?");
            ps.setInt(1, idTeste);
            rs = ps.executeQuery();
            if(rs.first()){
                visibilidade = rs.getInt("TES_visibilidade");
            }
        } finally {
            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

            conn.close();
        }
        
        return visibilidade;
    }
    public void alterarVisibilidade(Integer idTeste) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE Teste SET TES_visibilidade= IF(TES_visibilidade=1, 0, 1) WHERE TES_idTeste=?");
            ps.setInt(1, idTeste);
            ps.execute();
        } finally {
             if (ps != null) {
                ps.close();
            }
            conn.close();
        }
    }
    
    public void deletarTeste(Integer idTeste) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE from Teste WHERE TES_idTeste = ?");
            ps.setInt(1, idTeste);
            ps.execute();
        } finally {
             if (ps != null) {
                ps.close();
            }
            conn.close();
        }
        
    }
}