package tpdssln.ssreparacoes;

public class Peca {
    public String nomePeca;
    public float custo;

    public Peca(String nomePeca, float custo) {
        this.nomePeca = nomePeca;
        this.custo = custo;
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
