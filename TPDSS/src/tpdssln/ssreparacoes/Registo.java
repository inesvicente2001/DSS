package tpdssln.ssreparacoes;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Registo implements Serializable {

    private String id;
    private String nomeEquipamento;
    private LocalDateTime dataPedido;
    private LocalDateTime dataPendente;
    private LocalDateTime dataNConcluido;
    private LocalDateTime dataConcluido;
    private LocalDateTime dataEntregue;
    private LocalDateTime dataAbandonado;
    private int urgencia;
    private String descricao;
    private String localizacao;
    private Reparacao reparacao;
    private Cliente cliente;

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

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getDataPendente() {
        return dataPendente;
    }

    public void setDataPendente(LocalDateTime dataPendente) {
        this.dataPendente = dataPendente;
    }

    public LocalDateTime getDataNConcluido() {
        return dataNConcluido;
    }

    public void setDataNConcluido(LocalDateTime dataNConcluido) {
        this.dataNConcluido = dataNConcluido;
    }

    public LocalDateTime getDataConcluido() {
        return dataConcluido;
    }

    public void setDataConcluido(LocalDateTime dataConcluido) {
        this.dataConcluido = dataConcluido;
    }

    public LocalDateTime getDataEntregue() {
        return dataEntregue;
    }

    public void setDataEntregue(LocalDateTime dataEntregue) {
        this.dataEntregue = dataEntregue;
    }

    public LocalDateTime getDataAbandonado() {
        return dataAbandonado;
    }

    public void setDataAbandonado(LocalDateTime dataAbandonado) {
        this.dataAbandonado = dataAbandonado;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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
            html.append(list[i]).append("<br/>");
        }
        html.append(list[i] );

        html.append("</body>\n");
        html.append("</html>");


        return html.toString();
    }


}
