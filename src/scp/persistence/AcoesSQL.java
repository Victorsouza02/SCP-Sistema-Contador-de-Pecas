/*
    *CLASSE : AcoesSQL
    *FUNCÃO : Armazenar metodos de CRUD dos dados
 */
package scp.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import scp.models.Pecas;

/**
 *
 * @author Desenvolvimento
 */
public class AcoesSQL {

    public void cadastrarPeca(Pecas pec){
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO pecas (nome, descricao, pmp, qtd_amostras, grandeza)"
                    + "VALUES (?, ?, ?, ?, ?)");
            sql.setString(1, pec.getNome());
            sql.setString(2, pec.getDescricao());
            sql.setString(3, pec.getPmp());
            sql.setString(4, pec.getQtd_amostras());
            sql.setString(5, pec.getGrandeza());
            sql.executeUpdate();
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
    }
    
    public boolean deletarPeca(int cod){
        Conexao conexao = new Conexao();
        boolean resposta = false;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE from pecas WHERE cod = ?");
            sql.setInt(1, cod);
            resposta = sql.executeUpdate() != 0; 
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return resposta;
    }
    
    public boolean editarPeca(Pecas pec){
        Conexao conexao = new Conexao();
        boolean resposta = false;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE pecas SET nome = ?, descricao = ?, pmp = ?, qtd_amostras = ?, grandeza = ? WHERE cod = ?");
            sql.setString(1, pec.getNome());
            sql.setString(2, pec.getDescricao());
            sql.setString(3, pec.getPmp());
            sql.setString(4, pec.getQtd_amostras());
            sql.setString(5, pec.getGrandeza());
            sql.setInt(6, pec.getCod());
            resposta = sql.executeUpdate() != 0;
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return resposta;
    }
    
    public List<Pecas> procurarPeca(String nome){
        Conexao conexao = new Conexao();
        List<Pecas> pecas = new ArrayList<>();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from pecas WHERE nome LIKE ?");
            sql.setString(1, nome + "%");
            ResultSet result = sql.executeQuery();
            while(result.next()){
                Pecas peca = new Pecas(
                   result.getString("nome"),
                   result.getString("descricao"),
                   result.getString("pmp"),
                   result.getString("qtd_amostras"),
                   result.getString("grandeza")
                );
                peca.setCod(result.getInt("cod"));
                pecas.add(peca);
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return pecas;
    }
    
    public Pecas procurarPeca(int cod){
        Conexao conexao = new Conexao();
        Pecas peca = null;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from pecas WHERE cod = ?");
            sql.setInt(1, cod);
            ResultSet result = sql.executeQuery();
            while(result.next()){
                peca = new Pecas(
                   result.getString("nome"),
                   result.getString("descricao"),
                   result.getString("pmp"),
                   result.getString("qtd_amostras"),
                   result.getString("grandeza")
                );
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return peca;
    }
    
    public List<Pecas> listarPecas(){
        Conexao conexao = new Conexao();
        List<Pecas> pecas = new ArrayList<Pecas>();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from pecas");
            ResultSet result = sql.executeQuery();
            while(result.next()){
                Pecas pec = new Pecas(
                   result.getString("nome"),
                   result.getString("descricao"),
                   result.getString("pmp"),
                   result.getString("qtd_amostras"),
                   result.getString("grandeza")
                );
                pec.setCod(result.getInt("cod"));
                pecas.add(pec);
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return pecas;
    }
    
    
}
