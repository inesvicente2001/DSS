package tpdssln.ssreparacoes;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Passo implements Serializable {
    private String id;
    private String nomePasso;
    private Boolean concluido;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Duration tempoPrevisto;
    private Set<Peca> pecasEstimadas;
    private Set<Peca> pecasUsadas;
    private Map<Integer, Passo> subPassos;
    private float custoFinal;

    public Passo(String nomePasso, Duration tempoPrevisto, Set<Peca> pecas, Map<Integer, Passo> subPassos, String id) {
        this.id = id;
        this.nomePasso = nomePasso;
        this.concluido = false;
        this.dataInicio = null;
        this.dataFim = null;
        this.tempoPrevisto = tempoPrevisto;
        this.pecasEstimadas = pecas;
        this.pecasUsadas = new HashSet<>();
        this.subPassos = subPassos;
    }

    public Passo(String nomePasso, LocalDateTime dataInicio, LocalDateTime dataFim, Duration tempoPrevisto, String id) {
        this.id = id;
        this.nomePasso = nomePasso;
        this.concluido = false;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tempoPrevisto = tempoPrevisto;
        this.pecasEstimadas = new HashSet<>();
        this.pecasUsadas = new HashSet<>();
    }

    public void setCustoFinal(float custoFinal) {
        this.custoFinal = custoFinal;
    }

    public float getCustoFinal() {
        return custoFinal;
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

    public Duration getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(Duration tempoPrevisto) {
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

    public Map<Integer, Passo> getSubPassos() {
        return subPassos;
    }

    public void setSubPassos(Map<Integer, Passo> subPassos) {
        this.subPassos = subPassos;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public float definirOrcamento() {
        float orcamento = 0;

        if (subPassos.isEmpty()) {
            for (Peca peca : pecasEstimadas) {
                orcamento = orcamento + (peca.getCusto() * peca.getQuantidade());
            }
        }
        else {
            for (Passo passo : subPassos.values())
                orcamento = orcamento + passo.definirOrcamento();
        }
        return orcamento;
    }

    public float definirCustoFinal() {
        float ret = 0;

        if (subPassos.isEmpty()) {
            ret = custoFinal;
        } else{
            for (Passo passo : subPassos.values())
                ret = ret + passo.definirCustoFinal();
        }

        System.out.println("ola: " + ret);

        return ret;
    }

    public Duration duracao() {
        return Duration.between(this.dataInicio,this.dataFim);
    }

    public void addPecaEstimada(String nomePeca, float custo, int quantidade) {
        boolean flag = false;

        for (Peca peca : pecasEstimadas)
            if (Objects.equals(peca.getNomePeca(), nomePeca)) {
                peca.setQuantidade(peca.getQuantidade() + quantidade);
                flag = true;
            }

        if (!flag) {
            Peca peca = new Peca(nomePeca, custo, quantidade);
            pecasEstimadas.add(peca);
        }
    }

    public void addPecaUsada(String nomePeca, float custo, int quantidade) {
        boolean flag = false;

        for (Peca peca : pecasUsadas)
            if (Objects.equals(peca.getNomePeca(), nomePeca)) {
                peca.setQuantidade(peca.getQuantidade() + quantidade);
                flag = true;
            }

        if (!flag) {
            Peca peca = new Peca(nomePeca, custo, quantidade);
            pecasUsadas.add(peca);
        }
    }

    public void iniciarPasso() {

        if (subPassos.isEmpty()) {
            dataInicio = LocalDateTime.now();
        }
        else {
            int i = 1;
            for(; subPassos.get(i).getConcluido() ; i++);
            subPassos.get(i).iniciarPasso();
            if (i==1) dataInicio = LocalDateTime.now();
        }
    }

    public Passo getInfoProximoPasso() {
        Passo ret;

        if (subPassos.isEmpty()) {
            return this;
        }
        else {
            int i = 1;
            for(; subPassos.get(i).getConcluido() ; i++);

            ret = subPassos.get(i).getInfoProximoPasso();
        }

        return ret;
    }

    public void concluirPasso() {
        if (subPassos.isEmpty()) {
            concluido = true;
            dataFim = LocalDateTime.now();
        }
        else {
            int i = 1;
            for(; subPassos.get(i).getConcluido() ; i++);
            subPassos.get(i).concluirPasso();
            if (!subPassos.containsKey(i+1)){
                concluido = true;
                dataFim = LocalDateTime.now();
            }
        }
    }

    public String getID() {
        return id;
    }
}
