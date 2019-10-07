/*
    *CLASSE : AcoesSQL
    *FUNCÃO : Armazenar metodos de CRUD dos dados
 */
package scp.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    public Pecas procurarPeca(int cod){
        Conexao conexao = new Conexao();
        Pecas pec = null;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from pecas WHERE cod = ?");
            sql.setInt(1, cod);
            ResultSet result = sql.executeQuery();
            while(result.next()){
                pec = new Pecas(
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
        return pec;
    }
    
    public List<String> listarNomesPecas(){
        Conexao conexao = new Conexao();
        List<String> nomes = new ArrayList<String>();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT nome from pecas");
            ResultSet result = sql.executeQuery();
            while(result.next()){
                nomes.add(result.getString("nome"));
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return nomes;
    }
    
    
}
