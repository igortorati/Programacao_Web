package Services;

import Utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Models.Resposta;

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
            ps.setInt(1, resposta.getRespostaContinua());
            ps.setInt(2, resposta.getRespostaOrdinal());
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
