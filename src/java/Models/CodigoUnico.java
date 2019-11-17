package Models;

/**
 *
 * @author sid
 */
public class CodigoUnico {
    private String idCodigoUnico;
    private Integer idTeste;
    private Integer indice;

    public CodigoUnico() {
    }
    
    public CodigoUnico(String idCodigoUnico, Integer idTeste, Integer indice) {
        this.idCodigoUnico = idCodigoUnico;
        this.idTeste = idTeste;
        this.indice = indice;
    }
    
    public String getIdCodigoUnico() {
        return idCodigoUnico;
    }

    public Integer getIdTeste() {
        return idTeste;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIdCodigoUnico(String idCodigoUnico) {
        this.idCodigoUnico = idCodigoUnico;
    }

    public void setIdTeste(Integer idTeste) {
        this.idTeste = idTeste;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }
    
}
