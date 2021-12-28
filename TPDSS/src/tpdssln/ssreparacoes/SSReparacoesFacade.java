package tpdssln.ssreparacoes;

import SSEmpregados.Empregado;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SSReparacoesFacade implements ISSReparacoes {
    public Set<Registo> pedidosOrcamento; //Os orçamentos que foram pedidos
    public Map<String, Registo> registosPendentes;
    public Map<String, Registo> registosNConcluidos; //Os que estão a ser feitos
    public Map<String, Registo> registosConcluidos; //Os concluidos mas não entregues
    public Map<String, Registo> registosEntregues; //Os entregues
    public Map<String, Registo> registosAbandonados;

    public SSReparacoesFacade() {
        this.pedidosOrcamento = new HashSet<>();
        this.registosPendentes = new HashMap<>();
        this.registosNConcluidos = new HashMap<>();
        this.registosConcluidos = new HashMap<>();
        this.registosEntregues = new HashMap<>();
        this.registosAbandonados = new HashMap<>();
    }

    public Set<Registo> getOrcamentosPedidos() {
        return pedidosOrcamento;
    }

    public void setOrcamentosPedidos(Set<Registo> orcamentosPedidos) {
        this.pedidosOrcamento = orcamentosPedidos;
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public void adicionarPedidoOrcamentoNormal(String nomeEquipamento, int urgencia, String descricao,
                                               String local, LocalDateTime prazo, String nomeCliente, String nif,
                                               String telemovel, String email){

        String id = generateID();
        Reparacao reparacao = new ReparacaoNormal(prazo);
        Cliente cliente = new Cliente(nomeCliente, nif, telemovel, email);
        Registo registo = new Registo(id, nomeEquipamento, urgencia, descricao, local, reparacao, cliente);
        pedidosOrcamento.add(registo);
    }

    public void adicionarPedidoOrcamentoExpresso(String nomeEquipamento, int urgencia, String descricao,
                                                 String local, LocalDateTime prazo, float precoFixo, String nomeCliente,
                                                 String nif, String telemovel, String email){

        String id = generateID();
        Reparacao reparacao = new ReparacaoExpresso(prazo, precoFixo);
        Cliente cliente = new Cliente(nomeCliente, nif, telemovel, email);
        Registo registo = new Registo(id, nomeEquipamento, urgencia, descricao, local, reparacao, cliente);
        pedidosOrcamento.add(registo);
    }

    public void registarPlanoTrabalho(){

        Registo aReparar = maisUrgente();

        pedidosOrcamento.remove(aReparar);

        //TODO somehow retornar a localizaçao

        //na interface vai ter de ser possivel adicionar os passos entre estas duas cenas

        //TODO por o sistema a enviar o email

        ReparacaoNormal r = (ReparacaoNormal) aReparar.reparacao;
        r.definirOrcamento();

        registosPendentes.put(aReparar.id, aReparar);
        aReparar.dataPendente = LocalDateTime.now();

    }

    public void confirmarReparacao(String idEquipamento){
        Registo value = this.registosPendentes.get(idEquipamento);
        if (value == null)
            return; //TODO exception n existe

        this.registosPendentes.remove(idEquipamento);
        this.registosNConcluidos.put(idEquipamento,value);
        value.dataNConcluido = LocalDateTime.now();
    }

    @Override
    public void registarConclusao(String idEquipamento, Tecnico tecnico){

        Registo concluido = this.registosNConcluidos.get(idEquipamento);
        if (concluido == null)
            return; //TODO exception n existe

        this.registosNConcluidos.remove(idEquipamento);
        this.registosConcluidos.put(idEquipamento,concluido);
        concluido.dataConcluido = LocalDateTime.now();
        tecnico.addReparacao(concluido.reparacao);

        //TODO
        //if (concluido.reparacao instanceof ReparacaoNormal) //dar n telefone
        //else //mandar mail
    }

    @Override
    public void registarEntrega(String id, Funcionario funcionario) {
        Registo value = registosConcluidos.get(id);
        if (value == null)
            return; //TODO exception n existe

        registosConcluidos.remove(id);
        registosEntregues.put(id, value);
        value.dataEntregue = LocalDateTime.now();
        funcionario.addEntrega();
    }

    public void equipamentoAbandonado(String id) {
        Registo value = registosConcluidos.get(id);
        if (value == null)
            return; //TODO exception n existe

        long daysBetween = ChronoUnit.DAYS.between(value.dataConcluido, LocalDateTime.now());
        if (daysBetween > 90) {
            registosConcluidos.remove(id);
            registosAbandonados.put(id, value);
            value.dataAbandonado = LocalDateTime.now();
        }

    }

    public void iniciarPasso(String id) {

        Registo value = registosNConcluidos.get(id);

        ReparacaoNormal r = (ReparacaoNormal) value.reparacao;

        r.iniciarPasso();
    }

    @Override
    public void concluirPasso(String id, Tecnico tecnico) {

        Registo value = registosNConcluidos.get(id);

        ReparacaoNormal r = (ReparacaoNormal) value.reparacao;

        boolean conc = r.concluirPasso(tecnico);

        if (conc) registarConclusao(id,tecnico);
    }

    public Registo maisUrgente(){

        Registo maisUrgente = null;
        int urgencia = -1;

        for (Registo pedidoOrcamento : pedidosOrcamento)
            if (pedidoOrcamento.urgencia >= urgencia && pedidoOrcamento.reparacao instanceof ReparacaoNormal)
                maisUrgente = pedidoOrcamento;

        //TODO if (idMaisUrgente == null) lancar exceçao

        return maisUrgente;
    }

    public String generateID() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        for (Registo pedidoOrcamento : pedidosOrcamento)
            if (pedidoOrcamento.id == generatedString)
                generatedString = generateID();

        if (registosConcluidos.containsKey(generatedString)
        || registosNConcluidos.containsKey(generatedString))
            generatedString = generateID();

        return generatedString;
    }
}
