/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import scp.config.ConfiguracaoGlobal;
import scp.main.Principal;



public class SobreController implements Initializable {
    @FXML
    private ImageView imagem;
    @FXML
    private Label nome_empresa;
    @FXML
    private Label versao;
    @FXML
    private Label telefone;
    @FXML
    private Label site;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atribuirValores();
    }
    
    private void atribuirValores(){
        Image img = new Image(Principal.class.getResourceAsStream("/scp/imgs/"+ConfiguracaoGlobal.getLOGO_EMPRESA()));
        imagem.setImage(img);
        nome_empresa.setText(ConfiguracaoGlobal.getNOME_EMPRESA());
        versao.setText("Versão : "+ConfiguracaoGlobal.getVERSAO());
        telefone.setText("Contato : "+ConfiguracaoGlobal.getTELEFONE());
        site.setText(ConfiguracaoGlobal.getSITE());
    }


    
    
    

    
    
}
