package Models;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Pergunta implements Comparable<Pergunta>{
    private String Descricao;
    private Integer tipo; //continua ou ordinal
    private Integer codigo; //tem ou nao descricao
    private Integer idPergunta;
    private Integer idTeste;
    private Integer indice; //posicao que esta no teste
    private Date updatedAt;
    
    public Pergunta(){   
    }

    public Pergunta(String Descricao, Integer tipo, Integer codigo, Integer idTeste, Integer indice) {
        this.Descricao = Descricao;
        this.tipo = tipo;
        this.codigo = codigo;
        this.idTeste = idTeste;
        this.indice = indice;
    }
    
    public Pergunta(String Descricao, Integer tipo, Integer codigo, Integer idPergunta, Integer idTeste, Integer indice) {
        this.Descricao = Descricao;
        this.tipo = tipo;
        this.codigo = codigo;
        this.idPergunta = idPergunta;
        this.idTeste = idTeste;
        this.indice = indice;
    }

    public Pergunta(String Descricao, Integer tipo, Integer codigo, Integer idPergunta, Integer idTeste, Integer indice, Date updatedAt) {
        this.Descricao = Descricao;
        this.tipo = tipo;
        this.codigo = codigo;
        this.idPergunta = idPergunta;
        this.idTeste = idTeste;
        this.indice = indice;
        this.updatedAt = updatedAt;
    }

    public String getDescricao() {
        return Descricao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Integer getIdPergunta() {
        return idPergunta;
    }

    public Integer getIdTeste() {
        return idTeste;
    }

    public Integer getIndice() {
        return indice;
    }
    
    public String getUpdatedAt(){
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = fmt.format(this.updatedAt);
        return date;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setIdPergunta(Integer idPergunta) {
        this.idPergunta = idPergunta;
    }

    public void setIdTeste(Integer idTeste) {
        this.idTeste = idTeste;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Override
    public int compareTo(Pergunta o) {
        if(this.indice < o.indice) return -1;
        else if(this.indice == o.indice)return 0;
        else return 0;
    }
}
