/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author sid
 */
public class Validacoes {
    public static Boolean emailValido(String email){
        Pattern p = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"); 
        Matcher m = p.matcher(email); 
        return (m.find() && m.group().equals(email));
    }
    
    public static Boolean telefoneValido(String telefone){
        Pattern p = Pattern.compile("^\\([1-9]{2}\\) (?:[0-9]|[0-9]{2})[0-9]{3}\\-[0-9]{4}$"); 
        Matcher m = p.matcher(telefone); 
        return (m.find() && m.group().equals(telefone));
    }
    
    public static Boolean cepValido(String cep){
        Pattern p = Pattern.compile("\\d{5}-\\d{3}"); 
        Matcher m = p.matcher(cep); 
        return (m.find() && m.group().equals(cep));
    }
}
