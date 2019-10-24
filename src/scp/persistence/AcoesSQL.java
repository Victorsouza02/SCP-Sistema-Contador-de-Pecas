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
import java.util.logging.Level;
import java.util.logging.Logger;
import scp.models.Pecas;
import scp.models.RegistroContagem;

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
                String pmp = result.getString("pmp");
                Pecas pec = new Pecas(
                   result.getString("nome"),
                   result.getString("descricao"),
                   pmp.substring(0,7),
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
    
    public boolean registrarContagem(RegistroContagem reg){
        Conexao conexao = new Conexao();
        boolean resposta = false;
        SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fmtTime = new SimpleDateFormat("HH:mm:ss");
        Date data = new Date();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO registro (nome_peca, desc_peca, qtd_amostras, pmp, data, hora, peso, pecas_contadas, grandeza)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sql.setString(1, reg.getNome_peca());
            sql.setString(2, reg.getDesc_peca());
            sql.setString(3, reg.getQtd_amostras());
            sql.setString(4, reg.getPmp());
            sql.setString(5, fmtDate.format(data));
            sql.setString(6, fmtTime.format(data));
            sql.setString(7, reg.getPeso());
            sql.setString(8, reg.getPecas_contadas());
            sql.setString(9, reg.getGrandeza());

            resposta = sql.executeUpdate() != 0;
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
        
        return resposta;
    }
    
    public List<RegistroContagem> listarRegistros(){
        Conexao conexao = new Conexao();
        List<RegistroContagem> registros = new ArrayList<RegistroContagem>();
        SimpleDateFormat dateFormatSql = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatView = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from registro");
            ResultSet result = sql.executeQuery();
            while(result.next()){
                data = dateFormatSql.parse(result.getString("data"));
                String pmp = result.getString("pmp");
                RegistroContagem reg = new RegistroContagem(
                   result.getString("nome_peca"),
                   result.getString("desc_peca"),
                   result.getString("qtd_amostras"),
                   pmp.substring(0,6),
                   dateFormatView.format(data),
                   result.getString("hora"),
                   result.getString("peso"),
                   result.getString("pecas_contadas"),
                   result.getString("grandeza")
                );
                reg.setId(result.getInt("id"));
                registros.add(reg);
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return registros;
    }
    
    public List<RegistroContagem> buscarRegistros(String nome){
        Conexao conexao = new Conexao();
        List<RegistroContagem> registros = new ArrayList<RegistroContagem>();
        SimpleDateFormat dateFormatSql = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatView = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from registro WHERE nome_peca LIKE ?");
            sql.setString(1, nome+"%");
            ResultSet result = sql.executeQuery();
            while(result.next()){
                data = dateFormatSql.parse(result.getString("data"));
                RegistroContagem reg = new RegistroContagem(
                   result.getString("nome_peca"),
                   result.getString("desc_peca"),
                   result.getString("qtd_amostras"),
                   result.getString("pmp"),
                   dateFormatView.format(data),
                   result.getString("hora"),
                   result.getString("peso"),
                   result.getString("pecas_contadas"),
                   result.getString("grandeza")
                );
                reg.setId(result.getInt("id"));
                registros.add(reg);
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return registros;
    }
    
     public List<RegistroContagem> buscarRegistros(String data_inicio , String data_fim){
        Conexao conexao = new Conexao();
        List<RegistroContagem> registros = new ArrayList<RegistroContagem>();
        SimpleDateFormat dateFormatSql = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatView = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from registro WHERE data BETWEEN ? AND ?");
            sql.setString(1, data_inicio);
            sql.setString(2, data_fim);
            ResultSet result = sql.executeQuery();
            while(result.next()){
                data = dateFormatSql.parse(result.getString("data"));
                RegistroContagem reg = new RegistroContagem(
                   result.getString("nome_peca"),
                   result.getString("desc_peca"),
                   result.getString("qtd_amostras"),
                   result.getString("pmp"),
                   dateFormatView.format(data),
                   result.getString("hora"),
                   result.getString("peso"),
                   result.getString("pecas_contadas"),
                   result.getString("grandeza")
                );
                reg.setId(result.getInt("id"));
                registros.add(reg);
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return registros;
    }
    
    public RegistroContagem getRegistro(int id){
        Conexao conexao = new Conexao();
        RegistroContagem reg = null;
        SimpleDateFormat dateFormatSql = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatView = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from registro WHERE id = ?");
            sql.setInt(1, id);
            ResultSet result = sql.executeQuery();
            while(result.next()){
                data = dateFormatSql.parse(result.getString("data"));
                reg = new RegistroContagem(
                   result.getString("nome_peca"),
                   result.getString("desc_peca"),
                   result.getString("qtd_amostras"),
                   result.getString("pmp"),
                   dateFormatView.format(data),
                   result.getString("hora"),
                   result.getString("peso"),
                   result.getString("pecas_contadas"),
                   result.getString("grandeza")
                );
                reg.setId(result.getInt("id"));
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return reg;
    }
    
    public RegistroContagem getUltimoRegistro(){
        Conexao conexao = new Conexao();
        RegistroContagem reg = null;
        SimpleDateFormat dateFormatSql = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatView = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * from registro order by id desc limit 1");
            ResultSet result = sql.executeQuery();
            while(result.next()){
                data = dateFormatSql.parse(result.getString("data"));
                reg = new RegistroContagem(
                   result.getString("nome_peca"),
                   result.getString("desc_peca"),
                   result.getString("qtd_amostras"),
                   result.getString("pmp"),
                   dateFormatView.format(data),
                   result.getString("hora"),
                   result.getString("peso"),
                   result.getString("pecas_contadas"),
                   result.getString("grandeza")
                );
                reg.setId(result.getInt("id"));
            }
            sql.close();
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return reg;
    }
    
    
}
