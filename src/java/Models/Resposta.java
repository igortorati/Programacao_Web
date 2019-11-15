package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sid
 */
public class Resposta {
    private int idResposta;
    private int respostaContinua;
    private int respostaOrdinal;
    private String descricao;
    private int idUsuario;
    private int idPergunta;
    private Date createdAt;

    public Resposta() {
    }

    public Resposta(int idResposta, int respostaContinua, int respostaOrdinal, String descricao, int idUsuario, int idPergunta, Date createdAt) {
        this.idResposta = idResposta;
        this.respostaContinua = respostaContinua;
        this.respostaOrdinal = respostaOrdinal;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.idPergunta = idPergunta;
        this.createdAt = createdAt;
    }
    
    public int getIdResposta() {
        return idResposta;
    }

    public int getRespostaContinua() {
        return respostaContinua;
    }

    public int getRespostaOrdinal() {
        return respostaOrdinal;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public String getCreatedAt() { 
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = fmt.format(this.createdAt);
        return date;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }

    public void setRespostaContinua(int respostaContinua) {
        this.respostaContinua = respostaContinua;
    }

    public void setRespostaOrdinal(int respostaOrdinal) {
        this.respostaOrdinal = respostaOrdinal;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
}
