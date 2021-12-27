package tpdssln.ssreparacoes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Passo {
    public String nomePasso;
    public Boolean concluido;
    public LocalDateTime dataInicio;
    public LocalDateTime dataFim;
    public LocalDateTime tempoPrevisto;
    public Set<Peca> pecasEstimadas;
    public Set<Peca> pecasUsadas;
    public Set<Passo> subPassos;

    public Passo(String nomePasso, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime tempoPrevisto) {
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

    public LocalDateTime getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(LocalDateTime tempoPrevisto) {
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
}
