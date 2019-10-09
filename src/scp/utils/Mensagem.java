/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scp.utils;

import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 *
 * @author Desenvolvimento
 */
public class Mensagem {
    
    public static void mensagemErro(String texto, Window win){
        Alert aviso = new Alert(Alert.AlertType.ERROR);
        aviso.initOwner(win);
        aviso.setTitle("Erro!");
        aviso.setContentText(texto);
        aviso.show();
    }
    
    public static void mensagemSucesso(String texto, Window win){
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.initOwner(win);
        aviso.setTitle("Informação");
        aviso.setHeaderText("OPERAÇÃO REALIZADA COM SUCESSO");
        aviso.setContentText(texto);
        aviso.show();
    }
}
