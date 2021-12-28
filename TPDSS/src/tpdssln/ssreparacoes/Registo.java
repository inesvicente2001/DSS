package tpdssln.ssreparacoes;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Registo implements Serializable {

    public String id;
    public String nomeEquipamento;
    public LocalDateTime dataPedido;
    public LocalDateTime dataPendente;
    public LocalDateTime dataNConcluido;
    public LocalDateTime dataConcluido;
    public LocalDateTime dataEntregue;
    public LocalDateTime dataAbandonado;
    public int urgencia;
    public String descricao;
    public String localizacao;
    public Reparacao reparacao;
    public Cliente cliente;

    public Registo(String id, String nomeEquipamento, int urgencia, String descricao,
                   String localizacao, Reparacao reparacao, Cliente cliente) {
        this.id = id;
        this.nomeEquipamento = nomeEquipamento;
        this.dataPedido = LocalDateTime.now();
        this.dataPendente = null;
        this.dataNConcluido = null;
        this.dataConcluido = null;
        this.dataEntregue = null;
        this.dataAbandonado = null;
        this.urgencia = urgencia;
        this.descricao = descricao;
        this.localizacao = localizacao;
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
        return dataPedido;
    }

    public void setData(LocalDateTime data) {
        this.dataPedido = data;
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String toHTMLDescricao(){

        StringBuilder html = new StringBuilder();

        html.append("<html>\n");
        html.append("<body>\n");

        String[] list = this.descricao.split("\n");

        int i = 0;
        for(; i<list.length - 1 ;i++){
            html.append(list[i] + "<br/>");
        }
        html.append(list[i] + "<br/>");

        html.append("</body>\n");
        html.append("</html>");


        return html.toString();
    }


}
