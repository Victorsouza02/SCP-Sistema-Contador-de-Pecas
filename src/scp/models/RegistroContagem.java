/*
   * CLASSE : RegistroContagem
   * FUNÇÃO : Gerencia os registros de entrada/saida de motoristas
 */
package scp.models;

import java.util.List;
import scp.persistence.AcoesSQL;

/**
 *
 * @author Desenvolvimento
 */
public class RegistroContagem {
    private int id;
    private String nome_peca;
    private String desc_peca;
    private String qtd_amostras;
    private String pmp;
    private String data;
    private String hora;
    private String peso;
    private String pecas_contadas;
    private String grandeza;
     
    public RegistroContagem(){
    
    }
    
    public RegistroContagem(String nome_peca, String desc_peca, String qtd_amostras, String pmp, String data, String hora, String peso, String pecas_contadas, String grandeza){
        setNome_peca(nome_peca);
        setDesc_peca(desc_peca);
        setQtd_amostras(qtd_amostras);
        setPmp(pmp);
        setData(data);
        setHora(hora);
        setPeso(peso);
        setPecas_contadas(pecas_contadas);
        setGrandeza(grandeza);
    }
    
    public RegistroContagem(String nome_peca, String desc_peca, String qtd_amostras, String pmp, String peso, String pecas_contadas, String grandeza){
        setNome_peca(nome_peca);
        setDesc_peca(desc_peca);
        setQtd_amostras(qtd_amostras);
        setPmp(pmp);
        setPeso(peso);
        setPecas_contadas(pecas_contadas);
        setGrandeza(grandeza);
    }
    
    //REGISTRA ENTRADA DOS MOTORISTAS
    public boolean registrarContagem(){
        AcoesSQL acao = new AcoesSQL();
        return acao.registrarContagem(this);
    }
    
    
    //RETORNA UMA OBSERVABLE LIST DE REGISTROS
    public List<RegistroContagem> listarRegistros() {
        AcoesSQL acao = new AcoesSQL();
        return acao.listarRegistros();
    }
    
    //RETORNA UMA  LISTA DE REGISTROS DE UM DETERMINADO PERIODO
    public List<RegistroContagem> buscarRegistros(String data_inicio, String data_fim) {
        AcoesSQL acao = new AcoesSQL();
        return acao.buscarRegistros(data_inicio,data_fim);
    }
    
     //RETORNA UMA  LISTA DE REGISTROS DE UMA DETERMINADA PEÇA
    public List<RegistroContagem> buscarRegistros(String nome_peca) {
        AcoesSQL acao = new AcoesSQL();
        return acao.buscarRegistros(nome_peca);
    }
    
    //RETORNA UMA OBSERVABLE LIST DE REGISTROS NUM PERIODO ESPECIFICO
    public List<RegistroContagem> listarRegistros(String data_ini, String data_fim) {
        AcoesSQL acao = new AcoesSQL();
        return null;
    }


    //GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_peca() {
        return nome_peca;
    }

    public void setNome_peca(String nome_peca) {
        this.nome_peca = nome_peca;
    }

    public String getDesc_peca() {
        return desc_peca;
    }

    public void setDesc_peca(String desc_peca) {
        this.desc_peca = desc_peca;
    }

    public String getQtd_amostras() {
        return qtd_amostras;
    }

    public void setQtd_amostras(String qtd_amostras) {
        this.qtd_amostras = qtd_amostras;
    }

    public String getPmp() {
        return pmp;
    }

    public void setPmp(String pmp) {
        this.pmp = pmp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPecas_contadas() {
        return pecas_contadas;
    }

    public void setPecas_contadas(String pecas_contadas) {
        this.pecas_contadas = pecas_contadas;
    }

    public String getGrandeza() {
        return grandeza;
    }

    public void setGrandeza(String grandeza) {
        this.grandeza = grandeza;
    }
    
    
    
    
    
    
    
}
