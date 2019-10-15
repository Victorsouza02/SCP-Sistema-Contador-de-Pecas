/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scp.config;

/**
 *
 * @author Desenvolvimento
 */
public class VariaveisGlobais {
    
    private static boolean erroDetectado = false;
    private static String mensagem = "";

    public static boolean isErroDetectado() {
        return erroDetectado;
    }

    public static void setErroDetectado(boolean erroDetectado) {
        VariaveisGlobais.erroDetectado = erroDetectado;
    }

    public static String getMensagem() {
        return mensagem;
    }

    public static void setMensagem(String mensagem) {
        VariaveisGlobais.mensagem = mensagem;
    }

    
}
