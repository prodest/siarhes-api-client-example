package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vinicius Avellar.
 * Classe que representa um provimento do servidor.
 */
public class Provimento {

    private int NUMFUNC;
    private int NUMERO;
    private int CARGO;
    private Date DTINI;
    private Date DTFIM;

    // TODO: Adicionar o restante dos campos.

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


    public int getCargo() {
        return CARGO;
    }

    public void setCargo(int CARGO) {
        this.CARGO = CARGO;
    }

    public Date getDtIni() {
        return DTINI;
    }

    public void setDtIni(Date DTINI) {
        this.DTINI = DTINI;
    }

    public Date getDtFim() {
        return DTFIM;
    }

    public void setDtFim(Date DTFIM) {
        this.DTFIM = DTFIM;
    }

    @Override
    public String toString() {
        DateFormat dateFmt = SimpleDateFormat.getDateInstance();
        String dtiniStr = dateFmt.format(getDtIni());
        return String.format("%s/%s - %s - %s", getNumFunc(), getNumero(), getCargo(), dtiniStr);
    }

}
