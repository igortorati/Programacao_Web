/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author igort
 */
public class Usuario {
    private String email;
    private Integer idade;
    private String contato;
    private String sexo;
    private String cep;
    private String cor;
    private String enfermidade;
    private Integer idTeste;
    private String codigo;
    private Integer idUsuario;

    public Usuario() {
    }

    public Usuario(String email, Integer idade, String contato, String sexo, String cep, String cor, String enfermidade, Integer idTeste, String codigo) {
        this.email = email;
        this.idade = idade;
        this.contato = contato;
        this.sexo = sexo;
        this.cep = cep;
        this.cor = cor;
        this.enfermidade = enfermidade;
        this.idTeste = idTeste;
        this.codigo = codigo;
    }

    public Usuario(String email, Integer idade, String contato, String sexo, String cep, String cor, String enfermidade, Integer idTeste, String codigo, Integer idUsuario) {
        this.email = email;
        this.idade = idade;
        this.contato = contato;
        this.sexo = sexo;
        this.cep = cep;
        this.cor = cor;
        this.enfermidade = enfermidade;
        this.idTeste = idTeste;
        this.codigo = codigo;
        this.idUsuario = idUsuario;
    }
    
    public Usuario(String email, Integer idade, String contato, String sexo, String cep, String cor, String enfermidade, Integer idTeste, Integer idUsuario) {
        this.email = email;
        this.idade = idade;
        this.contato = contato;
        this.sexo = sexo;
        this.cep = cep;
        this.cor = cor;
        this.enfermidade = enfermidade;
        this.idTeste = idTeste;
        this.idUsuario = idUsuario;
    }
    
    
    

    public String getContato() {
        return contato;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCep() {
        return cep;
    }

    public String getCor() {
        return cor;
    }

    public String getEnfermidade() {
        return enfermidade;
    }

    public Integer getIdTeste() {
        return idTeste;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setEnfermidade(String enfermidade) {
        this.enfermidade = enfermidade;
    }

    public void setIdTeste(Integer idTeste) {
        this.idTeste = idTeste;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
    
}
