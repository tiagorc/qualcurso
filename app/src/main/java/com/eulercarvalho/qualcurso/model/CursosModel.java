package com.eulercarvalho.qualcurso.model;

import java.io.Serializable;

/**
 * Created by TiagoCarvalho on 20/06/17.
 */

public class CursosModel implements Serializable {
    private String nome;
    private String url;

    public CursosModel (String nome, String url){
        this.nome = nome;
        this.url = url;
    }

    public String getNome() { return this.nome; }
    public String getURL() { return this.url; }

}
