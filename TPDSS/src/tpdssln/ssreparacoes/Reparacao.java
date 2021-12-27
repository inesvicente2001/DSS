package tpdssln.ssreparacoes;

import java.time.LocalDateTime;
import java.util.Map;

public  class Reparacao {
    public boolean conclusao;
    public String equipamento;
    public String descricao;
    public LocalDateTime prazoMaximo;

    public Reparacao(String equipamento, String descricao, LocalDateTime prazoMaximo) {
        this.conclusao = false;
        this.equipamento = equipamento;
        this.descricao = descricao;
        this.prazoMaximo = prazoMaximo;
    }

    public boolean isConclusao() {
        return conclusao;
    }

    public void setConclusao(boolean conclusao) {
        this.conclusao = conclusao;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getPrazoMaximo() {
        return prazoMaximo;
    }

    public void setPrazoMaximo(LocalDateTime prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }



    public Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo) {
        //Nif do cliente, Nome do equipamento, Descricao do problema
        return new Reparacao(nome,descricao,prazoMaximo);
    }


    public void registarEntrega(String id) {



    }


    public void repararProduto(String id) {

        //Tenho de pensar bem como vai ser feito

    }




    public void registarConclusao() {

        this.setConclusao(true);

    }


    public LocalDateTime obterPrazoMaximo() {

        return this.getPrazoMaximo();
    }

    public void registarPlanoTrabalho(Map<Integer,Passo> planoTrabalho) {

        if( this instanceof ReparacaoNormal ) {
            ((ReparacaoNormal)this).setPlanoTrabalho(planoTrabalho);
        }

    }
}
