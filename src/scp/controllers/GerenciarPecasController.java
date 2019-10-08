/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scp.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scp.main.Principal;
import scp.models.Pecas;

/**
 * FXML Controller class
 *
 * @author Desenvolvimento
 */
public class GerenciarPecasController implements Initializable {
    @FXML
    private TableView<Pecas> tabela;
    @FXML
    private TableColumn<Pecas, Integer> codpeca;
    @FXML
    private TableColumn<Pecas, String> nomepeca;
    @FXML
    private TableColumn<Pecas, String> descpeca;
    @FXML
    private TableColumn<Pecas, String> pmppeca;
    @FXML
    private TableColumn<Pecas, String> qtdamostras;
    @FXML
    private TableColumn<Pecas, String> grandeza;
    
    @FXML
    private Label lb_codpeca;
    @FXML
    private TextField tf_buscarpeca;
    @FXML
    private TextField tf_nomepeca;
    @FXML
    private TextField tf_descpeca;
    @FXML
    private TextField tf_pmppeca;
    @FXML
    private TextField tf_qtdamostras;
    
    @FXML
    private ComboBox cb_grandeza;
    
    @FXML
    private Pane paineledicao;
    
    @FXML
    private Button btn_contagem;

    @FXML
    private Button btn_salvar;
    @FXML
    private Button btn_excluir;
    
    private Pecas peca_edicao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paineledicao.setVisible(false);
        preencherTabela();
        carregarComboBox();
        eventos();
    }
    
    //PREENCHE A TABELA COM OS DADOS DO BANCO
    private void preencherTabela() {
        Pecas pec = new Pecas();
        codpeca.setCellValueFactory(
                new PropertyValueFactory<>("cod"));
        nomepeca.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        descpeca.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));
        qtdamostras.setCellValueFactory(
                new PropertyValueFactory<>("qtd_amostras"));
        pmppeca.setCellValueFactory(
                new PropertyValueFactory<>("pmp"));
        grandeza.setCellValueFactory(
                new PropertyValueFactory<>("grandeza"));

        tabela.setItems(pec.listaPecas());
    }
    
    private void preencherTabela(String nome) {
        Pecas pec = new Pecas();
        codpeca.setCellValueFactory(
                new PropertyValueFactory<>("cod"));
        nomepeca.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        descpeca.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));
        qtdamostras.setCellValueFactory(
                new PropertyValueFactory<>("qtd_amostras"));
        pmppeca.setCellValueFactory(
                new PropertyValueFactory<>("pmp"));
        grandeza.setCellValueFactory(
                new PropertyValueFactory<>("grandeza"));

        tabela.setItems(pec.listaPecas(nome));
    }
    
    private void eventos(){
        //EVENTO NA TABELA
        tabela.setRowFactory(tv -> {
            TableRow<Pecas> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                //AO CLICAR DUAS VEZES EM UMA LINHA NA TABELA
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                   Pecas peca = row.getItem();
                   lb_codpeca.setText(String.valueOf(peca.getCod()));
                   tf_nomepeca.setText(peca.getNome());
                   tf_descpeca.setText(peca.getDescricao());
                   tf_pmppeca.setText(peca.getPmp());
                   tf_qtdamostras.setText(peca.getQtd_amostras());
                   cb_grandeza.setValue(peca.getGrandeza());
                   paineledicao.setVisible(true); //ESCONDE PAINEL DE EDIÇÃO
                   peca_edicao = peca;
                }
            });
            return row;
        });
        
        //EVENTO - AO DIGITAR NO CAMPO DE BUSCAR PEÇA
        tf_buscarpeca.setOnKeyReleased((event) -> {
            //PREENCHE A TABELA COM OS RESULTADOS DE ACORDO COM A PESQUISA
            preencherTabela(tf_buscarpeca.getText());
        });
        
        //EVENTO - CLICAR NO BOTÃO DE SALVAR
        btn_salvar.setOnMouseClicked((event)->{
            peca_edicao.setNome(tf_nomepeca.getText());
            peca_edicao.setDescricao(tf_descpeca.getText());
            peca_edicao.setPmp(tf_pmppeca.getText());
            peca_edicao.setQtd_amostras(tf_qtdamostras.getText());
            peca_edicao.setGrandeza(cb_grandeza.getValue().toString());
            peca_edicao.editarPeca();
            paineledicao.setVisible(false);//ESCONDE PAINEL DE EDIÇÃO
            preencherTabela(); //ATUALIZA A TABELA COM OS DADOS ATUAIS
        });
        
        //EVENTO - CLICAR NO BOTÃO DE EXCLUIR
        btn_excluir.setOnMouseClicked((event)->{
            peca_edicao.deletarPeca(lb_codpeca.getText());
            paineledicao.setVisible(false);//ESCONDE PAINEL DE EDIÇÃO
            preencherTabela();//ATUALIZA A TABELA COM OS DADOS ATUAIS
        });
        
        //EVENTO - CLICAR NO BOTÃO DE FAZER CONTAGEM
        btn_contagem.setOnMouseClicked((event)->{
            PmpconhecidoController.codPassado = lb_codpeca.getText();
            Principal.novaTela(Principal.contadorPmpScene(), "Contagem de Peças - PMP Conhecido", true);
            Stage stage = (Stage) btn_contagem.getScene().getWindow();
            stage.close();
        });
    }
    
    //CARREGA COMBOBOX COM AS GRANDEZAS
     public void carregarComboBox(){
        List<String> grandezas = new ArrayList<String>();
        grandezas.add("mg");
        grandezas.add("g");
        grandezas.add("kg");
        cb_grandeza.setItems(FXCollections.observableArrayList(grandezas));
        
    } 
}
