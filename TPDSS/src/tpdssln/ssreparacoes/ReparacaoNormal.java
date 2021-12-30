package tpdssln.ssreparacoes;

import tpdssln.ssempregados.Tecnico;

import java.time.Duration;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class ReparacaoNormal extends Reparacao implements Serializable {

    private float orcamento;
    private float custoFinal;
    private Map<Integer,Passo> planoTrabalho;

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
            Duration tempoPrevistoPasso = passo.getTempoPrevisto();
            tempoPrevisto = Duration.ofSeconds(tempoPrevisto.getSeconds() + tempoPrevistoPasso.getSeconds());
        }

        return tempoPrevisto;
    }

    public void iniciarPasso() {
        int i = 1;

        for(; planoTrabalho.get(i).getConcluido() ; i++);

        planoTrabalho.get(i).setDataInicio(LocalDateTime.now());
    }

    public boolean concluirPasso(Tecnico tecnico) {
        int i = 1;

        for(; planoTrabalho.get(i).getConcluido() ; i++);

        planoTrabalho.get(i).setConcluido(true);
        planoTrabalho.get(i).setDataFim(LocalDateTime.now());

        definirCustoFinal();

        //TODO
        //if ((custoFinal/orcamento) > 1.2) //fazer a ceninha de contactar o cliente

        //retorna se o plano de trabalhos está td concluido ou se ainda há algum passo por fazer
        if (planoTrabalho.containsKey(i+1)) return false;
        else return true;
    }

    public void addPecaEstimada(Integer passo, String nomePeca, float custo, int quantidade) {
        Passo p = planoTrabalho.get(passo);
        p.addPecaEstimada(nomePeca, custo, quantidade);
    }

    public void addPecaUsada(Integer passo, String nomePeca, float custo, int quantidade) {
        Passo p = planoTrabalho.get(passo);
        p.addPecaUsada(nomePeca, custo, quantidade);
    }

    public void addSubPasso(Integer passo, String nomePaco, Duration tempoPrevisto) {
        Passo p = planoTrabalho.get(passo);
        p.addSubPasso(nomePaco, tempoPrevisto);
    }


    public String toHTMLPlanoTrabalho(){

        StringBuilder html = new StringBuilder();

        html.append("<html>\n");
        html.append("<body>\n");


        html.append("<br/>").append("Passo ").append("1").append(") ").append(this.getPlanoTrabalho().get(1)).append("<br/>");

        int i = 2;
        for(; i<this.getPlanoTrabalho().size() - 1 ;i++){

            html.append("Passo ").append(i).append(") ").append(this.getPlanoTrabalho().get(i)).append("<br/>");
        }

        html.append("Passo ").append(i).append(") ").append(this.getPlanoTrabalho().get(i));

        html.append("</body>\n");
        html.append("</html>");


        return html.toString();
    }


}
