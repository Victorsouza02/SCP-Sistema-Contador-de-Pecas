/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scp.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import scp.models.Impressao;
import scp.models.RegistroContagem;

public class RegistrosController implements Initializable {

    @FXML
    private TableView<RegistroContagem> tabela;
    @FXML
    private TableColumn<RegistroContagem, Integer> idcol;
    @FXML
    private TableColumn<RegistroContagem, String> pecacol;
    @FXML
    private TableColumn<RegistroContagem, String> desccol;
    @FXML
    private TableColumn<RegistroContagem, String> qtdcol;
    @FXML
    private TableColumn<RegistroContagem, String> pmpcol;
    @FXML
    private TableColumn<RegistroContagem, String> datacol;
    @FXML
    private TableColumn<RegistroContagem, String> horacol;
    @FXML
    private TableColumn<RegistroContagem, String> pesocol;
    @FXML
    private TableColumn<RegistroContagem, String> contcol;
    @FXML
    private TableColumn<RegistroContagem, String> grandeza;
    
    private List<RegistroContagem> registros;
    
    @FXML
    private Button btn_imprimir;
    @FXML
    private Button btn_buscardata;
    
    @FXML
    private DatePicker data_inicio;
    @FXML
    private DatePicker data_fim;
    
    @FXML
    private TextField tf_nomepeca;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTabelaTodos();
        eventos();
    }    
    
    private void eventos(){
        //EVENTO NA TABELA
        tabela.setRowFactory(tv -> {
            TableRow<RegistroContagem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                //AO CLICAR DUAS VEZES EM UMA LINHA NA TABELA
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    RegistroContagem rowData = row.getItem();
                    //PERGUNTA SE QUER REIMPRESSÃO DA ETIQUETA
                    Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                    aviso.initOwner(tabela.getScene().getWindow());
                    aviso.setTitle("Impressão");
                    aviso.setHeaderText("Imprimir o registro de contagem ID : " + rowData.getId());
                    aviso.setContentText("Deseja fazer a impressão?");
                    ButtonType botaoSim = new ButtonType("Sim");
                    ButtonType botaoNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
                    aviso.getButtonTypes().setAll(botaoSim, botaoNao);
                    Optional<ButtonType> result = aviso.showAndWait();
                    if (result.get() == botaoSim) { //Se a opção for SIM
                        Impressao.fazerEtiquetaHtml(rowData.getId()); //Reimprime a etiqueta 
                    }
                }
            });
            return row;
        });
        
        tf_nomepeca.setOnKeyReleased((event) -> {
            preencherTabelaPesquisaNome(tf_nomepeca.getText());
        });
        
        btn_buscardata.setOnMouseClicked((event) -> {
            preencherTabelaPesquisaData(data_inicio.getValue().toString(), data_fim.getValue().toString());
        });
        
        btn_imprimir.setOnAction((event)->{
            Impressao.fazerRelatorioHtml(registros, LocalDate.MIN, LocalDate.MIN);
        });
    }
    //PREENCHE A TABELA COM OS DADOS DO BANCO
    private void preencherTabelaTodos() {
        RegistroContagem reg = new RegistroContagem();
        idcol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        pecacol.setCellValueFactory(
                new PropertyValueFactory<>("nome_peca"));
        desccol.setCellValueFactory(
                new PropertyValueFactory<>("desc_peca"));
        qtdcol.setCellValueFactory(
                new PropertyValueFactory<>("qtd_amostras"));
        pmpcol.setCellValueFactory(
                new PropertyValueFactory<>("pmp"));
        datacol.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        horacol.setCellValueFactory(
                new PropertyValueFactory<>("hora"));
        pesocol.setCellValueFactory(
                new PropertyValueFactory<>("peso"));
        contcol.setCellValueFactory(
                new PropertyValueFactory<>("pecas_contadas"));
        grandeza.setCellValueFactory(
                new PropertyValueFactory<>("grandeza"));
        registros = reg.listarRegistros();
        tabela.setItems(FXCollections.observableList(registros));
    }
    
    private void preencherTabelaPesquisaNome(String nome) {
        RegistroContagem reg = new RegistroContagem();
        idcol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        pecacol.setCellValueFactory(
                new PropertyValueFactory<>("nome_peca"));
        desccol.setCellValueFactory(
                new PropertyValueFactory<>("desc_peca"));
        qtdcol.setCellValueFactory(
                new PropertyValueFactory<>("qtd_amostras"));
        pmpcol.setCellValueFactory(
                new PropertyValueFactory<>("pmp"));
        datacol.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        horacol.setCellValueFactory(
                new PropertyValueFactory<>("hora"));
        pesocol.setCellValueFactory(
                new PropertyValueFactory<>("peso"));
        contcol.setCellValueFactory(
                new PropertyValueFactory<>("pecas_contadas"));
        grandeza.setCellValueFactory(
                new PropertyValueFactory<>("grandeza"));
        registros = reg.buscarRegistros(nome);
        tabela.setItems(FXCollections.observableList(registros));
    }
    
    private void preencherTabelaPesquisaData(String data_inicio, String data_fim) {
        RegistroContagem reg = new RegistroContagem();
        idcol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        pecacol.setCellValueFactory(
                new PropertyValueFactory<>("nome_peca"));
        desccol.setCellValueFactory(
                new PropertyValueFactory<>("desc_peca"));
        qtdcol.setCellValueFactory(
                new PropertyValueFactory<>("qtd_amostras"));
        pmpcol.setCellValueFactory(
                new PropertyValueFactory<>("pmp"));
        datacol.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        horacol.setCellValueFactory(
                new PropertyValueFactory<>("hora"));
        pesocol.setCellValueFactory(
                new PropertyValueFactory<>("peso"));
        contcol.setCellValueFactory(
                new PropertyValueFactory<>("pecas_contadas"));
        grandeza.setCellValueFactory(
                new PropertyValueFactory<>("grandeza"));
        registros = reg.buscarRegistros(data_inicio,data_fim);
        tabela.setItems(FXCollections.observableList(registros));
    }
}
