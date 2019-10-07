/*
    * CLASSE : TelaInicialController
    * FUNÇÃO : Controlar os eventos da Tela Inicial e usar os metodos necessários.
*/

package scp.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import scp.main.Principal;
//import scp.models.Impressao;
import scp.models.LerSerial;
import scp.utils.Formatacao;

public class TelaInicialController implements Initializable {

    @FXML
    private MenuItem menu_relatorio;
    @FXML
    private MenuItem menu_sobre;
    @FXML
    private MenuItem menu_config;
    @FXML
    private MenuItem menu_impressao;
    @FXML
    private MenuItem pmp_desconhecido;
    @FXML
    private MenuItem contagem_pmp;
    
    @FXML
    private ImageView logo;
    
    @FXML
    private Button btn_cadastrar;
    @FXML
    private Button btn_contagem;
    @FXML
    private Button btn_gerenciar;



    
    //INICIALIZA O CONTROLLER
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventosElementos(); //Eventos dos elementos visuais
        //Carrega imagem da logo
        Image img = new Image(Principal.class.getResourceAsStream("/scp/imgs/logoebm.png"));
        logo.setImage(img);
        
    }


    //EVENTOS DE ELEMENTOS
    public void eventosElementos() {     
        //AO CLICAR NO MENU SOBRE
        menu_sobre.setOnAction((event) -> {
            //Carrega modal de sobre o programa
            Principal.loadScene(Principal.sobreScene(), "Sobre o programa",false);
        });
        
        //AO CLICAR NO MENU CONFIGURAÇÕES GERAIS
        menu_config.setOnAction((event) -> {
            //Carrega modal de configurações gerais
            Principal.loadScene(Principal.configScene(), "Configurações Gerais",false);
        });
        
        //AO CLICAR NO MENU CONFIGURAÇÕES DE IMPRESSÃO
        menu_impressao.setOnAction((event) ->{
            //Carrega modal de configurações de impressão
            Principal.loadScene(Principal.impressaoScene(), "Configurações de Impressão",false);
        });
        
        
        //AO CLICAR NO MENU PMP DESCONHECIDO
        pmp_desconhecido.setOnAction((event) -> {
            //Carrega modal de pmp desconhecido
            Principal.loadScene(Principal.cadastrarPmpScene(), "Cadastrar Peça - PMP Desconhecido",false);
        });
        
        btn_cadastrar.setOnAction((event) -> {
            //Carrega modal de pmp desconhecido
            Principal.loadScene(Principal.cadastrarPmpScene(), "Cadastrar Peça - PMP Desconhecido",false);
        });
        
        
        //AO CLICAR NO MENU PMP DESCONHECIDO
        contagem_pmp.setOnAction((event) -> {
            //Carrega modal de pmp desconhecido
            Principal.loadScene(Principal.contadorPmpScene(), "Contagem de Peças - PMP Conhecido",false);
        });
        
        btn_contagem.setOnAction((event) -> {
            Principal.loadScene(Principal.contadorPmpScene(), "Contagem de Peças - PMP Conhecido",false);
        });
        
        btn_gerenciar.setOnAction((event) -> {
            Principal.loadScene(Principal.contadorPmpScene(), "Contagem de Peças - PMP Conhecido",false);
        });

    }


    public void limparCampos() { //LIMPA TODOS OS CAMPOS DO FORMULÁRIO
        
    }



}
