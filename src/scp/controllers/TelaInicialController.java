/*
    * CLASSE : TelaInicialController
    * FUNÇÃO : Controlar os eventos da Tela Inicial e usar os metodos necessários.
*/

package scp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import scp.config.ConfiguracaoGlobal;
import scp.config.VariaveisGlobais;
import scp.main.Principal;
import scp.utils.Formatacao;

public class TelaInicialController implements Initializable {

    @FXML
    private MenuItem menu_sobre;
    @FXML
    private MenuItem menu_config;
    @FXML
    private MenuItem menu_impressao;

    
    @FXML
    private ImageView logo;
    
    @FXML
    private Button btn_cadastrar;
    @FXML
    private Button btn_contagem;
    @FXML
    private Button btn_gerenciar;
    @FXML
    private Button btn_registro;
    
    @FXML
    private Pane painel_erro;
    @FXML
    private Label msg_erro;
    
    Thread verificarErrosThread;

    
    //INICIALIZA O CONTROLLER
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        verificarErrosThread = new Thread(this::verificarErrosThread);
        verificarErrosThread.start();
        atribuirValores(); //Atribui valores aos elementos
        eventosElementos(); //Eventos dos elementos visuais   
    }
    
    private void atribuirValores(){
        //Carrega imagem da logo
        Image img = new Image(Principal.class.getResourceAsStream("/scp/imgs/"+ConfiguracaoGlobal.getLOGO_EMPRESA()));
        logo.setImage(img);
    }


    //EVENTOS DE ELEMENTOS
    private void eventosElementos() {
        painel_erro.setVisible(false);
        //AO CLICAR NO MENU SOBRE
        menu_sobre.setOnAction((event) -> {
            //Carrega modal de sobre o programa
            Principal.novaTela(Principal.sobreScene(), "Sobre o programa",false);
        });
        
        //AO CLICAR NO MENU CONFIGURAÇÕES GERAIS
        menu_config.setOnAction((event) -> {
            //Carrega modal de configurações gerais
            Principal.novaTela(Principal.configScene(), "Configurações Gerais",false);
        });
        
        //AO CLICAR NO MENU CONFIGURAÇÕES DE IMPRESSÃO
        menu_impressao.setOnAction((event) ->{
            //Carrega modal de configurações de impressão
            Principal.novaTela(Principal.impressaoScene(), "Configurações de Impressão",false);
        });
        
        
        btn_cadastrar.setOnAction((event) -> {
            //Carrega modal de pmp desconhecido
            Principal.novaTela(Principal.cadastrarPmpScene(), "Cadastrar Peça - PMP Desconhecido",false);
        });
        
        btn_contagem.setOnAction((event) -> {
            Principal.novaTela(Principal.contadorPmpScene(), "Contagem de Peças - PMP Conhecido", false);
        });
        
        btn_gerenciar.setOnAction((event) -> {
            Principal.novaTela(Principal.gerenciarPecasScene(), "Gerenciar Peças",false);
        });
        
        btn_gerenciar.setOnAction((event) -> {
            Principal.novaTela(Principal.gerenciarPecasScene(), "Gerenciar Peças",false);
        });
        
        btn_registro.setOnAction((event) -> {
            Principal.novaTela(Principal.registrosScene(), "Registros de Contagem",false);
        });

    }
    
    private void verificarErrosThread() {
        boolean exibiuPainel = false;
        while (true) {
            if(VariaveisGlobais.isErroDetectado()){
                if(!exibiuPainel){
                    painel_erro.setVisible(true);
                    FadeTransition ft = new FadeTransition(Duration.millis(2000), painel_erro);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    exibiuPainel = true;
                }
                Platform.runLater(() -> {
                    btn_contagem.setDisable(true);
                    btn_gerenciar.setDisable(true);
                    btn_cadastrar.setDisable(true);
                    msg_erro.setText(VariaveisGlobais.getMensagem());
                });
            } else {
                painel_erro.setVisible(false);
                btn_contagem.setDisable(false);
                btn_gerenciar.setDisable(false);
                btn_cadastrar.setDisable(false);
                exibiuPainel = false;
            }
            try {
                if(exibiuPainel){
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(20);
                }
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            }
        }
    }
    

}
