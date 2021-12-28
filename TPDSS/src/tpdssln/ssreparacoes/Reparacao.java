package tpdssln.ssreparacoes;

import java.time.LocalDateTime;
import java.util.Map;

public class Reparacao {
    public LocalDateTime prazoMaximo;

    public Reparacao(LocalDateTime prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }


    public LocalDateTime getPrazoMaximo() {
        return prazoMaximo;
    }

    public void setPrazoMaximo(LocalDateTime prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }



    public Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo) {
        //Nif do cliente, Nome do equipamento, Descricao do problema
        return new Reparacao(prazoMaximo);
    }


    public void registarEntrega(String id) {



    }


    public void repararProduto(String id) {

        //Tenho de pensar bem como vai ser feito

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
