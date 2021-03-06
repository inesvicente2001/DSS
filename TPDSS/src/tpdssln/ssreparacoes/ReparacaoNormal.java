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

    public ReparacaoNormal() {
        super();
        this.orcamento = -1;
        this.custoFinal = 0;
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void definirOrcamento() {
        for(Passo passo: planoTrabalho.values()) {
            this.orcamento = this.orcamento + passo.definirOrcamento();
        }
    }

    public float definirCustoFinal() {
        this.custoFinal = 0;
        for(Passo passo: planoTrabalho.values()) {
            this.custoFinal = this.custoFinal + passo.definirCustoFinal();
        }
        System.out.println("adeus: " + custoFinal);
        return this.custoFinal;
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

        planoTrabalho.get(i).iniciarPasso();

    }

    public boolean concluirPasso(Tecnico tecnico) {
        int i = 1;

        for(; planoTrabalho.get(i).getConcluido() ; i++);

        planoTrabalho.get(i).concluirPasso();

        definirCustoFinal();

        //TODO fazer a ceninha de contactar o cliente
        if ((custoFinal/orcamento) > 1.2);

        //retorna se o plano de trabalhos est?? td concluido ou se ainda h?? algum passo por fazer
        if (planoTrabalho.containsKey(i+1)) return false;
        else return true;
    }

    public Passo getInfoProximoPasso() {
        int i = 1;

        if(planoTrabalho.isEmpty()) return null;

        for(; planoTrabalho.get(i).getConcluido(); i++);

        return planoTrabalho.get(i).getInfoProximoPasso();
    }

    public void definirPrazoMaximo() {
        for (Passo passo : planoTrabalho.values()) {
            this.prazoMaximo = this.prazoMaximo.plus(passo.getTempoPrevisto());
        }
    }

    public void addPecaEstimada(Integer passo, String nomePeca, float custo, int quantidade) {
        Passo p = planoTrabalho.get(passo);
        p.addPecaEstimada(nomePeca, custo, quantidade);
    }

    public void addPecaUsada(Integer passo, String nomePeca, float custo, int quantidade) {
        Passo p = planoTrabalho.get(passo);
        p.addPecaUsada(nomePeca, custo, quantidade);
    }

    public String toHTMLPlanoTrabalho(){


        String htmlString = " ";

        if(this.getPlanoTrabalho().size() > 0){

            StringBuilder html = new StringBuilder();

            html.append("<html>\n");
            html.append("<body>\n");

            int i = 1;
            for(; i<this.getPlanoTrabalho().size() - 1 ;i++){

                html.append("Passo ").append(i).append(") ").append(this.getPlanoTrabalho().get(i).getNomePasso()).append("<br/>");
            }

            html.append("Passo ").append(i).append(") ").append(this.getPlanoTrabalho().get(i).getNomePasso());

            html.append("</body>\n");
            html.append("</html>");


            htmlString = html.toString();


        }

        return htmlString;
    }


}
