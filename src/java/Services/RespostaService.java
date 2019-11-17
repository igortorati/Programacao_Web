package Services;

import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Models.Resposta;
import java.sql.Types;

/**
 *
 * @author sid
 */
public class RespostaService {
    public void salvarResposta(Resposta resposta) throws Exception{
        Connection conn = DbConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Resposta (RES_respostaContinua, RES_respostaOrdinal, RES_descricao,"
                    + "Usuario_USU_idUsuario, Pergunta_PER_idPergunta) VALUES (?, ?, ?, ?, ?)");
            if(resposta.getRespostaContinua() != null){
                ps.setDouble(1, resposta.getRespostaContinua());
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setNull(1, Types.DOUBLE);
                ps.setInt(2, resposta.getRespostaOrdinal());
            }
            ps.setString(3, resposta.getDescricao());
            ps.setInt(4, resposta.getIdUsuario());
            ps.setInt(5, resposta.getIdPergunta());
            ps.execute();
        } finally {
            if(ps != null){
                ps.close();
            }
            conn.close();
        }
    }
}
