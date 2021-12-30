package tpdssln.ssreparacoes;

import java.io.Serializable;

public class Peca implements Serializable {
    private String nomePeca;
    private float custo;
    private int quantidade;

    public Peca(String nomePeca, float custo, int quantidade) {
        this.nomePeca = nomePeca;
        this.custo = custo;
        this.quantidade = quantidade;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
