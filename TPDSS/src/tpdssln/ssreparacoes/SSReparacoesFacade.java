package tpdssln.ssreparacoes;

import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SSReparacoesFacade implements ISSReparacoes {
    public Map<String, Registo> pedidosOrcamento; //Os orçamentos que foram pedidos
    public Map<String, Registo> registosPendentes;
    public Map<String, Registo> registosNConcluidos; //Os que estão a ser feitos
    public Map<String, Registo> registosConcluidos; //Os concluidos mas não entregues
    public Map<String, Registo> registosEntregues; //Os entregues
    public Map<String, Registo> registosAbandonados;

    public SSReparacoesFacade() {
        this.pedidosOrcamento = new HashMap<>();
        this.registosPendentes = new HashMap<>();
        this.registosNConcluidos = new HashMap<>();
        this.registosConcluidos = new HashMap<>();
        this.registosEntregues = new HashMap<>();
        this.registosAbandonados = new HashMap<>();
    }

    public Map<String, Registo> getOrcamentosPedidos() {
        return pedidosOrcamento;
    }

    public void setOrcamentosPedidos(Map<String, Registo> orcamentosPedidos) {
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
                                               String telemovel, String email, Funcionario funcionario){

        String id = generateID();
        Reparacao reparacao = new ReparacaoNormal(prazo);
        Cliente cliente = new Cliente(nomeCliente, nif, telemovel, email);
        Registo registo = new Registo(id, nomeEquipamento, urgencia, descricao, local, reparacao, cliente);
        pedidosOrcamento.put(registo.getId(), registo);
        funcionario.addRececao();
    }

    public void adicionarPedidoOrcamentoExpresso(String nomeEquipamento, int urgencia, String descricao,
                                                 String local, LocalDateTime prazo, float precoFixo,
                                                 Duration duracaoPrevista, String nomeCliente, String nif,
                                                 String telemovel, String email, Funcionario funcionario){

        String id = generateID();
        Reparacao reparacao = new ReparacaoExpresso(prazo, precoFixo, duracaoPrevista);
        Cliente cliente = new Cliente(nomeCliente, nif, telemovel, email);
        Registo registo = new Registo(id, nomeEquipamento, urgencia, descricao, local, reparacao, cliente);
        pedidosOrcamento.put(registo.getId(), registo);
        funcionario.addRececao();
    }

    public void registarPlanoTrabalho(String id, Map<Integer, Passo> planoTrabalho){

        Registo aReparar = pedidosOrcamento.get(id);

        pedidosOrcamento.remove(aReparar);

        //TODO somehow retornar a localizaçao

        //na interface vai ter de ser possivel adicionar os passos entre estas duas cenas

        //TODO por o sistema a enviar o email

        ReparacaoNormal r = (ReparacaoNormal) aReparar.getReparacao();
        r.definirOrcamento();

        registosPendentes.put(aReparar.getId(), aReparar);
        aReparar.setDataPendente(LocalDateTime.now());

    }

    public void confirmarReparacao(String idEquipamento){
        Registo value = this.registosPendentes.get(idEquipamento);
        if (value == null)
            return; //TODO exception n existe

        this.registosPendentes.remove(idEquipamento);
        this.registosNConcluidos.put(idEquipamento,value);
        value.setDataNConcluido(LocalDateTime.now());
    }

    @Override
    public void registarConclusao(String idEquipamento, Tecnico tecnico){

        Registo concluido = this.registosNConcluidos.get(idEquipamento);
        if (concluido == null)
            return; //TODO exception n existe

        this.registosNConcluidos.remove(idEquipamento);
        this.registosConcluidos.put(idEquipamento,concluido);
        concluido.setDataConcluido(LocalDateTime.now());
        tecnico.addReparacao(concluido.getId(), concluido.getReparacao());

        Duration duracao = Duration.between(concluido.getDataNConcluido(), concluido.getDataConcluido());
        tecnico.atualizarDuracaoMedia(duracao);
        Duration desvio;

        if (concluido.getReparacao() instanceof ReparacaoNormal) {
            ReparacaoNormal r = (ReparacaoNormal)concluido.getReparacao();
            desvio = Duration.ofSeconds(duracao.getSeconds() - r.tempoPrevisto().getSeconds());
            tecnico.atualizarMediaDesvio(desvio);
            //TODO dar n telefone
        } else {
            ReparacaoExpresso r = (ReparacaoExpresso) concluido.getReparacao();
            desvio = Duration.ofSeconds(duracao.getSeconds() - r.getDuracaoPrevista().getSeconds());
            tecnico.atualizarMediaDesvio(desvio);
            //TODO mandar mail
        }
    }

    @Override
    public void registarEntrega(String id, Funcionario funcionario) {
        Registo value = registosConcluidos.get(id);
        if (value == null)
            return; //TODO exception n existe

        registosConcluidos.remove(id);
        registosEntregues.put(id, value);
        value.setDataEntregue(LocalDateTime.now());
        funcionario.addEntrega();
    }

    public void registarEntregaDeEquipamentoRecusado(String id, Funcionario funcionario) {
        Registo value = registosPendentes.get(id);
        if (value == null)
            return; //TODO exception n existe

        registosPendentes.remove(id);
        registosEntregues.put(id, value);
        value.setDataEntregue(LocalDateTime.now());
        funcionario.addEntrega();
    }

    public void equipamentoAbandonado(String id) {
        Registo value = registosConcluidos.get(id);
        if (value == null)
            return; //TODO exception n existe

        long daysBetween = ChronoUnit.DAYS.between(value.getDataConcluido(), LocalDateTime.now());
        if (daysBetween > 90) {
            registosConcluidos.remove(id);
            registosAbandonados.put(id, value);
            value.setDataAbandonado(LocalDateTime.now());
        }

    }

    public void iniciarPasso(String id) {

        Registo value = registosNConcluidos.get(id);

        ReparacaoNormal r = (ReparacaoNormal) value.getReparacao();

        r.iniciarPasso();
    }

    @Override
    public void concluirPasso(String id, Tecnico tecnico) {

        Registo value = registosNConcluidos.get(id);

        ReparacaoNormal r = (ReparacaoNormal) value.getReparacao();

        boolean conc = r.concluirPasso(tecnico);

        if (conc) registarConclusao(id,tecnico);
    }

    @Override
    public Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo) {
        return null;
    }

    public void addPecaEstimada(Registo registo, Integer passo, String nomePeca, float custo, int quantidade) {
        if (registo.getReparacao() instanceof ReparacaoNormal) {
            ReparacaoNormal r = (ReparacaoNormal) registo.getReparacao();
            r.addPecaEstimada(passo, nomePeca,custo,quantidade);
        }
    }

    public void addPecaUsada(Registo registo, Integer passo, String nomePeca, float custo, int quantidade) {
        if (registo.getReparacao() instanceof ReparacaoNormal) {
            ReparacaoNormal r = (ReparacaoNormal) registo.getReparacao();
            r.addPecaUsada(passo, nomePeca,custo,quantidade);
        }
    }

    public void addSubPasso(Registo registo, Integer passo, String nomePaco, Duration tempoPrevisto) {
        if (registo.getReparacao() instanceof ReparacaoNormal) {
            ReparacaoNormal r = (ReparacaoNormal) registo.getReparacao();
            r.addSubPasso(passo, nomePaco, tempoPrevisto);
        }
    }

    @Override
    public LocalDateTime obterPrazoMaximo(Registo registo) {
        ReparacaoNormal r = (ReparacaoNormal) registo.getReparacao();
        LocalDateTime prazoMaximo = LocalDateTime.now().plusSeconds(r.tempoPrevisto().getSeconds());
        r.setPrazoMaximo(prazoMaximo);
        return prazoMaximo;
    }

    public Registo maisUrgente() {

        Registo maisUrgente = null;
        int urgencia = -1;

        for (Registo pedidoOrcamento : pedidosOrcamento.values())
            if (pedidoOrcamento.getUrgencia() >= urgencia && pedidoOrcamento.getReparacao() instanceof ReparacaoNormal){
                urgencia = pedidoOrcamento.getUrgencia();
                maisUrgente = pedidoOrcamento;
            }

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

        for (Registo pedidoOrcamento : pedidosOrcamento.values())
            if (pedidoOrcamento.getId() == generatedString)
                generatedString = generateID();

        if (registosConcluidos.containsKey(generatedString)
        || registosNConcluidos.containsKey(generatedString))
            generatedString = generateID();

        return generatedString;
    }


    public String toHTMLDescricao(String id) throws NullPointerException{
        return pedidosOrcamento.values().stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null).toHTMLDescricao();
    }

}
