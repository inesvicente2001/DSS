package tpdssln.ssreparacoes;

public class Peca {
    public String nomePeca;
    public float custo;
    public int quantidade;

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
}
