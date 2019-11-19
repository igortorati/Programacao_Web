package Services;

import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.CodigoUnico;
import java.util.ArrayList;
/**
 *
 * @author sid
 */
public class CodigoUnicoService {
    
    public CodigoUnico getCodigoUnico(String codigo) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CodigoUnico codigoUnico = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM CodigoUnico WHERE idCodigoUnico = ?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if(rs.first()){
                codigoUnico = new CodigoUnico(rs.getString("idCodigoUnico"), rs.getInt("Teste_TES_idTeste"), rs.getInt("indice"));
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
        return codigoUnico;
    }
    
    public void incrementarIndice(String idCodigoUnico) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE CodigoUnico SET indice = indice + 1 WHERE idCodigoUnico = ?");
            ps.setString(1, idCodigoUnico);
            ps.execute();
        } finally {
            if(ps != null){
                ps.close();
            }
            conn.close();
        }
    }
    
    public void deletarCodigoUnico(String idCodigoUnico) throws Exception {
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM CodigoUnico WHERE idCodigoUnico = ?");
            ps.setString(1, idCodigoUnico);
            ps.execute();
        } finally {
            if(ps != null){
                    ps.close();
            }
            conn.close();
        }
    }
    
    public ArrayList<String> getCodigos(Integer idTeste) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        ArrayList<String> keys = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement("SELECT idCodigoUnico from CodigoUnico WHERE Teste_TES_idTeste = ?");
            ps.setInt(1, idTeste);
            rs = ps.executeQuery();
            keys = new ArrayList();
            while(rs.next()){
                keys.add(rs.getString("idCodigoUnico"));
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
        return keys;
    }
    
    public void gerarCodigos(Integer id, Integer n) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        ArrayList<String> keys = null;
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("CALL genkeys(?,?)");
            ps.setInt(1, id);
            ps.setInt(2, n);
            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
            conn.close();
        }
    }
}
