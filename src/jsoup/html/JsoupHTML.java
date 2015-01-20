/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoup.html;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Marcos
 */
public class JsoupHTML {

    /**
     * @param args the command line arguments
     */
    private final Document document;

    public JsoupHTML(Document document) {
        this.document = document;
    }

    public static void main(String[] args) {

        try {
            String url = "http://www.tjpi.jus.br/themisconsulta/processo/303743037";
            Document document = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)").timeout(30000).get();

            JsoupHTML parserProcesso = new JsoupHTML(document);
            parserProcesso.getInfoProcesso();

        } catch (IOException ex) {
            System.out.println("ERRO: Obter URL " + ex.getMessage());
        }

    }

    private void getInfoProcesso() {
        Elements elementNumProcesso = document.getElementsByClass("content");
        Elements elements = document.getElementsByClass("div-table");
        //Num. do processo
        getNumeroProcesso(elementNumProcesso.eq(0));
        //Dados do Processo
        getInfoProcessoDetalhes(elements.eq(0));
        getInfoProcessoPartes(elements.eq(2));
        getInfoProcessoDistribuicoes(elements.eq(4));
        getinfoProcessoMovimentos(elements.eq(5));
    }

    private void getNumeroProcesso(Elements elements) {
        System.out.println("<<<Processo Número>>>");

        Element element = elements.first();
        Element numProces = element.getElementsByClass("div-header").first();
        String processo = numProces.getElementsByClass("div-header").text();
        System.out.println("Número do " + processo);

//        }
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    private void getInfoProcessoDetalhes(Elements elements) {
        System.out.println("<<<Detalhes>>>");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        formatter.setTimeZone(TimeZone.getDefault());
        for (Element element : elements) {
            Elements filha = element.getElementsByTag("tr");
            for (Element tb : filha) {
                String tit = tb.getElementsByTag("th").text();
                String desc = tb.getElementsByTag("td").text();

                if (tit.equals("Data de Abertura") || tit.equals("")) {
                    try {
                        Date date = formatter.parse(desc);

                        String novaData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
                        desc = novaData;
                    } catch (ParseException ex) {
                        Logger.getLogger(JsoupHTML.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                System.out.println(tit + ": " + desc);

            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    private void getInfoProcessoPartes(Elements elements) {
        System.out.println("<<<Partes>>>");
        for (Element element : elements) {
            Elements filha = element.getElementsByTag("tr");
            //Jsoup.parse(document.html().replaceAll("(?i)<br[^>]*>", "<pre>\n</pre>")).text();
            for (Element tb : filha) {
                String tit = tb.getElementsByTag("th").text();
                String desc = tb.getElementsByTag("td").text();
                System.out.println(tit + ": " + desc);
            }

        }
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    private void getInfoProcessoDistribuicoes(Elements elements) {
        System.out.println("<<<Distribuições>>>");
        for (Element element : elements) {
            Elements filha = element.getElementsByTag("tr");
            for (Element tb : filha) {
                String tit = tb.getElementsByTag("th").text();
                String desc = tb.getElementsByTag("td").text();
                System.out.println(tit + ": " + desc);
            }

        }
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    private void getinfoProcessoMovimentos(Elements elements) {
        int countMov = 0;

        System.out.println("<<<Movimentações>>>");

        for (Element element : elements) {
            Elements filha = element.getElementsByTag("tr");
            for (Element tb : filha) {
                String tit = tb.getElementsByTag("th").text();
                String desc = tb.getElementsByTag("td").html();
                String descricao = "";
                descricao = trocarTagBRporQuebraDeLinha(desc, descricao);

                System.out.println(tit + " --> " + descricao);
                System.out.println("_______________________________________________________________________________________________");
                countMov++;

            }

        }
        System.out.println("Total de Movimentos: " + countMov);
    }

    //Trocar a Tag <BR> por \n (quebra de linha
    public static String trocarTagBRporQuebraDeLinha(String html, String linebreakerString) {
        String result = "";
        if (html.contains(linebreakerString)) {
            result = trocarTagBRporQuebraDeLinha(html, linebreakerString + "1");
        } else {
            result = Jsoup.parse(html.replaceAll("(?i)<br[^>]*>", linebreakerString)).text(); // replace and html line breaks with java linebreak.
            result = result.replaceAll(linebreakerString, "\n");
        }
        return result;
    }

}
