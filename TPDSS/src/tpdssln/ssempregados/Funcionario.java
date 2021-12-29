package tpdssln.ssempregados;

import java.io.Serializable;

public class Funcionario extends Empregado implements Serializable {

    public int nEntregas;
    public int nRececoes;

    public Funcionario(String id, String nome, String password) {
        super(id,nome,password);
        nEntregas = 0;
        nRececoes = 0;
    }


    public int getnEntregas() {
        return nEntregas;
    }

    public void setnEntregas(int nEntregas) {
        this.nEntregas = nEntregas;
    }

    public int getnRececoes() {
        return nRececoes;
    }

    public void setnRececoes(int nRececoes) {
        this.nRececoes = nRececoes;
    }

    public void addEntrega() {
        nEntregas++;
    }

    public void addRececao() {
        nRececoes++;
    }
}
