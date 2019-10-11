/*
 * CLASSE : Impressao
 * Função : Gerenciar tudo relacionado a impressão de cupom/relatórios.
*/

package scp.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import scp.persistence.AcoesSQL;
import scp.utils.BrowserLaunch;


public class Impressao {

    private static final String PATH_TXT = new File("").getAbsolutePath() + "\\print\\etiqueta.txt";
    private static final String PATH_HTML = new File("").getAbsolutePath() + "\\print\\PRINT.HTML";
    private static final String PATH_HTML_REPORT = new File("").getAbsolutePath() + "\\print\\report.html";
    private static final String FONTE = Propriedades.getFonte();
    private static final String[] substituir = new String[]{"$NOMEEMPRESA","$ENDERECOEMPRESA","$TELEMPRESA","$ID", "$PECA", "$DESCRICAO", "$QTDAMOSTRAS", "$PMP", "$DATA",
                        "$HORA", "$PESO", "$PCONTADAS"};

    public static void fazerEtiquetaHtml(int id) { //FAZER O ETIQUETA/CUPOM EM HTML PARA IMPRESSÃO
        try {
            AcoesSQL acao = new AcoesSQL();
            RegistroContagem reg = acao.getRegistro(id);
            //ARQUIVO DE LEITURA
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_TXT), StandardCharsets.ISO_8859_1));
            String linha = "";
            //ARQUIVO DE ESCRITA
            OutputStreamWriter buffWrite = new OutputStreamWriter(new FileOutputStream(PATH_HTML), StandardCharsets.UTF_8);

            buffWrite.write("");
            buffWrite.append("<!DOCTYPE html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body><pre style='font-size:"+FONTE+"px'><center>");
            while (true) {
                if (linha != null) {
                    for (String str : substituir) {
                        //VERIFICA SE A LINHA TEM A VARIAVEL DE SUBSTITUIÇÃO E COLOCA UM VALOR NO LOCAL
                        if (linha.contains(str)) {
                            linha = colocarValores(linha, str, reg);
                        }
                    }
                    buffWrite.append(linha + "<br>");
                } else {
                    break;
                }
                linha = buffRead.readLine();
            }
            for(int i = 1; i <= Integer.parseInt(Propriedades.getAltura()); i++){
               buffWrite.append("&nbsp"+"<br>");
            }
            buffWrite.append("</pre><script>print()</script></body></html>");
            buffRead.close();
            buffWrite.close();
            BrowserLaunch.openURL(PATH_HTML);
        } catch (IOException iEx) {
            System.out.println(iEx.getMessage());
        }
    }

    //LISTA OS RELATÓRIOS DO BANCO DE DADOS DE UM PERIODO EM UM ARQUIVO HTML
    public static void fazerRelatorioHtml(List<RegistroContagem> registros, LocalDate inicio, LocalDate fim) {
        try {
            String data_ini = inicio.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            String data_fim = fim.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            String linha = "";

            //ARQUIVO PARA ESCRITA
            OutputStreamWriter buffWrite = new OutputStreamWriter(new FileOutputStream(PATH_HTML_REPORT), StandardCharsets.UTF_8);

            buffWrite.write("");
            buffWrite.append("<!DOCTYPE html><html>\n"
                    + "<head>"
                    + "<style type=\"text/css\">"
                    + "@media print {br.pb {page-break-after:always}}"
                    + "body {font-family:verdana; font-size: 9px}"
                    + "th {font-family:verdana; font-size: 10px; text-align: left}"
                    + "td {font-family:verdana; font-size: 9px}"
                    + "</style>"
                    + "</head>"
                    + "<body> <table>"
                    + "<tr><td style=\"font-size: 12px\">REGISTROS DE CONTAGEM</td></tr>"
                    + "</table>"
                    + "<br><table width=\"100%\">"
                    + "<tr><th>ID</th><th>PECA</th><th>DESCRICAO</th><th>QTD.AMOSTRAS</th><th>PMP</th><th>DATA</th><th>HORA</th><th>PESO</th><th>PECAS CONTADAS</th></tr>");
            for (RegistroContagem reg : registros) {
                //A CADA REGISTRO ENCONTRADO
                buffWrite.append("<tr><td>" + reg.getId() + "</td><td>" + reg.getNome_peca() + "</td><td>" + reg.getDesc_peca() + "</td><td>" + reg.getQtd_amostras() + "</td><td>" + reg.getPmp() + "</td><td>" + reg.getData()
                        + "</td><td>" + reg.getHora() + "</td><td>" + reg.getPeso() + reg.getGrandeza() + "</td><td>" + reg.getPecas_contadas() + "</td></tr>");
            }
            buffWrite.append("</table></body></html>");
            buffWrite.close();
            BrowserLaunch.openURL(PATH_HTML_REPORT);
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    //TROCA OS VALORES DOS CAMPOS SUBSTITUIVEIS PELOS DADOS DO REGISTRO
    public static String colocarValores(String linha, String var, RegistroContagem reg) {      
            switch (var) {
                case "$NOMEEMPRESA":
                    linha = linha.replace(var, Propriedades.getNomeempresa());
                    break;
                    
                case "$ENDERECOEMPRESA":
                    linha = linha.replace(var, Propriedades.getEnderecoempresa());
                    break;
                    
                case "$TELEMPRESA":
                    linha = linha.replace(var, Propriedades.getTelempresa());
                    break;
                case "$ID":
                    linha = linha.replace(var, Integer.toString(reg.getId()));
                    break;
                case "$PECA":
                    linha = linha.replace(var, reg.getNome_peca());
                    break;
                case "$DESCRICAO":
                    linha = linha.replace(var, reg.getDesc_peca());
                    break;
                case "$QTDAMOSTRAS":
                    linha = linha.replace(var, reg.getQtd_amostras());
                    break;
                case "$PMP":
                    linha = linha.replace(var, reg.getPmp()+reg.getGrandeza());
                    break;
                case "$DATA":
                    linha = linha.replace(var, reg.getData());
                    break;
                case "$HORA":
                    linha = linha.replace(var, reg.getHora());
                    break;
                case "$PESO":
                    linha = linha.replace(var, reg.getPeso()+reg.getGrandeza());
                    break;
                case "$PCONTADAS":
                    linha = linha.replace(var, reg.getPecas_contadas());
                    break;
            }
        return linha;
    }

    //GETTERS
    
    public static String getPath_txt() {
        return PATH_TXT;
    }

    public static String getPath_html() {
        return PATH_HTML;
    }

    public static String getPath_html_report() {
        return PATH_HTML_REPORT;
    }


}
