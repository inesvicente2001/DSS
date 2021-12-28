package SSReparacoes;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

public class Passo {
    public String nomePasso;
    public Boolean concluido;
    public LocalDateTime dataInicio;
    public LocalDateTime dataFim;
    public Period tempoPrevisto;
    public Set<Peca> pecasEstimadas;
    public Set<Peca> pecasUsadas;
    public Set<Passo> subPassos;

    public Passo(String nomePasso, LocalDateTime dataInicio, LocalDateTime dataFim, Period tempoPrevisto) {
        this.nomePasso = nomePasso;
        this.concluido = false;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tempoPrevisto = tempoPrevisto;
        this.pecasEstimadas = new HashSet<>();
        this.pecasUsadas = new HashSet<>();
    }

    public String getNomePasso() {
        return nomePasso;
    }

    public void setNomePasso(String nomePasso) {
        this.nomePasso = nomePasso;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Period getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(Period tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public Set<Peca> getPecasEstimadas() {
        return pecasEstimadas;
    }

    public void setPecasEstimadas(Set<Peca> pecasEstimadas) {
        this.pecasEstimadas = pecasEstimadas;
    }

    public Set<Peca> getPecasUsadas() {
        return pecasUsadas;
    }

    public void setPecasUsadas(Set<Peca> pecasUsadas) {
        this.pecasUsadas = pecasUsadas;
    }

    public Set<Passo> getSubPassos() {
        return subPassos;
    }

    public void setSubPassos(Set<Passo> subPassos) {
        this.subPassos = subPassos;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public float definirOrcamento() {
        float orcamento = 0;
        for (Peca peca: pecasEstimadas) {
            orcamento = orcamento + peca.custo;
        }
        return orcamento;
    }

    public float definirCustoFinal() {
        float custoFinal = 0;
        for (Peca peca: pecasUsadas) {
            custoFinal = custoFinal + peca.custo;
        }
        return custoFinal;
    }

    public void addPecaEstimada(String nomePeca, float custo) {
        Peca peca = new Peca(nomePeca, custo);
        pecasEstimadas.add(peca);
    }

    public void addPecaUsada(String nomePeca, float custo) {
        Peca peca = new Peca(nomePeca, custo);
        pecasUsadas.add(peca);
    }

    public void addSubPasso(String nomePasso, Period tempoPrevisto){
        Passo passo = new Passo(nomePasso, null, null, tempoPrevisto);
        subPassos.add(passo);
    }

}
