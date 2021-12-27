package SSReparacoes;

import java.time.LocalDateTime;

public class Registo{

    public String id;
    public LocalDateTime data;
    public int urgencia;
    public String descricao;
    public Boolean pagamento;
    public Reparacao reparacao;
    public Cliente cliente;

    public Registo(String id, int urgencia, String descricao, Reparacao reparacao, Cliente cliente) {
        this.id = id;
        this.data = LocalDateTime.now();
        this.urgencia = urgencia;
        this.descricao = descricao;
        this.pagamento = false;
        this.reparacao = reparacao;
        this.cliente = cliente;
    }

    public Registo(String id, LocalDateTime data, int urgencia, String descricao, Boolean pagamento, Reparacao reparacao, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.urgencia = urgencia;
        this.descricao = descricao;
        this.pagamento = pagamento;
        this.reparacao = reparacao;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(Boolean pagamento) {
        this.pagamento = pagamento;
    }

    public Reparacao getReparacao() {
        return reparacao;
    }

    public void setReparacao(Reparacao reparacao) {
        this.reparacao = reparacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



}
