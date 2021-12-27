package tpdssln.ssreparacoes;

import java.time.LocalDateTime;
import java.util.*;

public class SSReparacoesFacade implements ISSReparacoes {
    public Set<Registo> orcamentosPedidos; //Os orçamentos que foram pedidos
    public Map<String, Registo> registosConcluidos; //Os entregues
    public Map<String, Registo> registosNConcluidos; //Os por fazer

    public SSReparacoesFacade() {
        this.orcamentosPedidos = new HashSet<>();
        this.registosConcluidos = new HashMap<>();
        this.registosNConcluidos = new HashMap<>();
    }

    public Set<Registo> getOrcamentosPedidos() {
        return orcamentosPedidos;
    }

    public void setOrcamentosPedidos(Set<Registo> orcamentosPedidos) {
        this.orcamentosPedidos = orcamentosPedidos;
    }

    public Map<String, Registo> getRegistosConcluidos() {
        return registosConcluidos;
    }

    public void setRegistosConcluidos(Map<String, Registo> registosConcluidos) {
        this.registosConcluidos = registosConcluidos;
    }

    public Map<String, Registo> getRegistosNConcluidos() {
        return registosNConcluidos;
    }

    public void setRegistosNConcluidos(Map<String, Registo> registosNConcluidos) {
        this.registosNConcluidos = registosNConcluidos;
    }





    public void adicionarPedidoOrcamento(){
        ////////
    }



    public void registarPasso(String idEquipamento, int ordem, Passo passo){

        Reparacao reparacaoEditar =  this.registosNConcluidos.get(idEquipamento).getReparacao();

        if( reparacaoEditar instanceof ReparacaoNormal ) {
            ((ReparacaoNormal)reparacaoEditar).addPasso(ordem, passo);
        }

    }

    public void registarPlanoTrabalho(String idEquipamento){

        ////

    }

    @Override
    public void registarConclusao(String idEquipamento){

        Registo concluido =  this.registosNConcluidos.get(idEquipamento);

        this.registosConcluidos.put(idEquipamento,concluido);

        this.registosNConcluidos.remove(idEquipamento);

    }


    @Override
    public Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo) {
        return null;
    }

    @Override
    public void registarEntrega(String id) {

    }

    @Override
    public void repararProduto(String id) {

    }

    @Override
    public void registarPlanoTrabalho(Map<Integer, Passo> planoTrabalho) {

    }



    @Override
    public LocalDateTime obterPrazoMaximo() {
        return null;
    }
}
