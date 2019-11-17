package Services;

import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.CodigoUnico;
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
            ps = conn.prepareStatement("SELECT * FROM CodigoUnico WHERE idCodigoUnico LIKE ?");
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
}
