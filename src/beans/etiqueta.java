/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author Marcos
 */
public class etiqueta {
    
    private String tribunal;
    private String secretariaVara;
    private String numProcesso;
    private String parteRequerente;
    private String comarca;
    private String valorAcao;
    private String dataAcao;
    private String situacao;

    public String getTribunal() {
        return tribunal;
    }

    public void setTribunal(String tribunal) {
        this.tribunal = tribunal;
    }

    public String getSecretariaVara() {
        return secretariaVara;
    }

    public void setSecretariaVara(String secretariaVara) {
        this.secretariaVara = secretariaVara;
    }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
    }

    public String getParteRequerente() {
        return parteRequerente;
    }

    public void setParteRequerente(String parteRequerente) {
        this.parteRequerente = parteRequerente;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getValorAcao() {
        return valorAcao;
    }

    public void setValorAcao(String valorAcao) {
        this.valorAcao = valorAcao;
    }

    public String getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(String dataAcao) {
        this.dataAcao = dataAcao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


}
