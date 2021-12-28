package tpdssln.ssreparacoes;

import tpdssln.ssempregados.Tecnico;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;


public class ReparacaoNormal extends Reparacao {

    public float orcamento;
    public float custoFinal;
    public Map<Integer,Passo> planoTrabalho;

    public ReparacaoNormal(LocalDateTime prazoMaximo) {
        super(prazoMaximo);
        this.orcamento = -1;
        this.custoFinal = -1;
        this.planoTrabalho = new HashMap<>();
    }

    public float getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public float getCustoFinal() {
        return custoFinal;
    }

    public void setCustoFinal(float custoFinal) {
        this.custoFinal = custoFinal;
    }

    public Map<Integer, Passo> getPlanoTrabalho() {
        return planoTrabalho;
    }

    public void setPlanoTrabalho(Map<Integer, Passo> planoTrabalho) {
        this.planoTrabalho = planoTrabalho;
    }

    public void addPasso(String nomePasso, Duration tempoPrevisto){
        Passo passo = new Passo(nomePasso, null, null, tempoPrevisto);
        this.planoTrabalho.put(planoTrabalho.size() + 1, passo);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void definirOrcamento() {
        for(Passo passo: planoTrabalho.values()) {
            this.orcamento = this.orcamento + passo.definirOrcamento();
        }
    }

    public void definirCustoFinal() {
        for(Passo passo: planoTrabalho.values()) {
            this.custoFinal = this.custoFinal + passo.definirCustoFinal();
        }
    }

    public Duration tempoPrevisto(){
        Duration tempoPrevisto = Duration.ofHours(0);

        for(Passo passo: planoTrabalho.values()) {
            tempoPrevisto = Duration.ofSeconds(tempoPrevisto.getSeconds() + passo.tempoPrevisto.getSeconds());
        }

        return tempoPrevisto;
    }

    public void iniciarPasso() {
        int i = 1;

        for(; planoTrabalho.get(i).concluido ; i++);

        planoTrabalho.get(i).dataInicio = LocalDateTime.now();
    }

    public boolean concluirPasso(Tecnico tecnico) {
        int i = 1;

        for(; planoTrabalho.get(i).concluido ; i++);

        planoTrabalho.get(i).concluido = true;
        planoTrabalho.get(i).dataFim = LocalDateTime.now();

        definirCustoFinal();
        tecnico.addPasso(planoTrabalho.get(i));

        //TODO
        //if ((custoFinal/orcamento) > 1.2) //fazer a ceninha de contactar o cliente

        //retorna se o plano de trabalhos está td concluido ou se ainda há algum passo por fazer
        if (planoTrabalho.containsKey(i+1)) return false;
        else return true;
    }

    public void definirPrazoMaximo() {
        for(Passo passo: planoTrabalho.values()) {
            this.prazoMaximo = this.prazoMaximo.plus(passo.tempoPrevisto);
        }
    }
}
