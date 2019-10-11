/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scp.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
import scp.models.Impressao;

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
    
    public static boolean mensagemConfirmacao(String texto, Window win){
        boolean autorizado = false;
         Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                    aviso.initOwner(win);
                    aviso.setTitle("Confirmação");
                    aviso.setHeaderText("Confirmação da ação");
                    aviso.setContentText(texto);
                    ButtonType botaoSim = new ButtonType("Sim");
                    ButtonType botaoNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
                    aviso.getButtonTypes().setAll(botaoSim, botaoNao);
                    Optional<ButtonType> result = aviso.showAndWait();
                    if (result.get() == botaoSim) { //Se a opção for SIM
                        autorizado = true;
                    }
        
        return autorizado;
    
    }
    
    
}
