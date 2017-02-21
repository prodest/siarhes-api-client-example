package model;

import java.util.List;

/**
 * Created by Vinicius Avellar.
 * Classe que representa um vínculo do servidor.
 */
public class Vinculo {

    private int NUMFUNC;
    private int NUMERO;
    private String SITUACAO;

    // TODO: Adicionar o restante dos campos.

    private transient List<Provimento> listaProvimentos;

    public int getNumFunc() {
        return NUMFUNC;
    }

    public void setNumFunc(int numfunc) {
        this.NUMFUNC = numfunc;
    }

    public int getNumero() {
        return NUMERO;
    }

    public void setNumero(int numero) {
        this.NUMERO = numero;
    }

    public String getSituacao() {
        return SITUACAO;
    }

    public void setSituacao(String situacao) {
        this.SITUACAO = situacao;
    }

    public List<Provimento> getProvimentos() {
        return listaProvimentos;
    }

    public void setProvimentos(List<Provimento> provimentos) {
        listaProvimentos = provimentos;
    }

    @Override
    public String toString() {
        return String.format("%s/%s - %s", getNumFunc(), getNumero(), getSituacao());
    }

}
