package SSReparacoes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class ReparacaoNormal extends Reparacao {

    public float orcamento;
    public float custoFinal;
    public Map<Integer,Passo> planoTrabalho;

    public ReparacaoNormal(String equipamento, String descricao, LocalDateTime prazoMaximo) {
        super(equipamento, descricao, prazoMaximo);
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

    public void addPasso(int ordem, Passo passo){
        this.planoTrabalho.put(ordem,passo);
    }


}
