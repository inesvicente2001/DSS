package tpdssln.ssreparacoes;

import tpdssdl.*;
import tpdssln.ssempregados.Funcionario;
import tpdssln.ssempregados.Tecnico;
import tpdssln.ssreparacoes.excecoes.RegistoNaoExisteException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SSReparacoesFacade implements ISSReparacoes {
    private final int disponibilidade = 5; //O SISTEMA SÓ PODE TER 5 REPARAÇÕES EXPRESSO
    private int ocupados = 0;
    private Map<String, Registo> pedidosOrcamento; //Os orçamentos que foram pedidos
    private Map<String, Registo> registosPendentes;
    private Map<String, Registo> registosNConcluidos; //Os que estão a ser feitos
    private Map<String, Registo> registosConcluidos; //Os concluidos mas não entregues
    private Map<String, Registo> registosEntregues; //Os entregues
    private Map<String, Registo> registosAbandonados;


    public int getDisponibilidade() {
        return disponibilidade;
    }

    public int getOcupados() {
        return ocupados;
    }

    public void setOcupados(int ocupados) {
        this.ocupados = ocupados;
    }

    public SSReparacoesFacade() {
        this.pedidosOrcamento = PedidosOrcamento.leFile();
        this.registosPendentes = RegistosPendentes.leFile();
        this.registosNConcluidos = RegistosNConcluidos.leFile();
        this.registosConcluidos = RegistosConcluidos.leFile();
        this.registosEntregues = RegistosEntregues.leFile();
        this.registosAbandonados = RegistosAbandonados.leFile();
    }

    @Override
    public void save() {
        PedidosOrcamento.escreveFile(this.pedidosOrcamento);
        RegistosPendentes.escreveFile(this.registosPendentes);
        RegistosNConcluidos.escreveFile(this.registosNConcluidos);
        RegistosConcluidos.escreveFile(this.registosConcluidos);
        RegistosEntregues.escreveFile(this.registosEntregues);
        RegistosAbandonados.escreveFile(this.registosAbandonados);
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

    public void setRegistosNConcluidos(Map<String, Registo> registosNConcluidos) {
        this.registosNConcluidos = registosNConcluidos;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String adicionarPedidoOrcamentoNormal(String nomeEquipamento, int urgencia, String descricao,
                                               String local, String nomeCliente, String nif,
                                               String telemovel, String email){

        String id = generateID();
        System.out.println(id);
        Reparacao reparacao = new ReparacaoNormal();

        Cliente cliente = new Cliente(nomeCliente, nif, telemovel, email);
        Registo registo = new Registo(id, nomeEquipamento, urgencia, descricao, local, reparacao, cliente);
        pedidosOrcamento.put(registo.getId(), registo);

        return id;
    }

    public String adicionarPedidoOrcamentoExpresso(String nomeEquipamento, int urgencia, String descricao,
                                                   String local, float precoFixo,
                                                   Duration duracaoPrevista, String nomeCliente, String nif,
                                                   String telemovel, String email){

        String id = generateID();
        System.out.println(id);
        Reparacao reparacao = new ReparacaoExpresso(precoFixo, duracaoPrevista);

        Cliente cliente = new Cliente(nomeCliente, nif, telemovel, email);
        Registo registo = new Registo(id, nomeEquipamento, urgencia, descricao, local, reparacao, cliente);
        registosConcluidos.put(registo.getId(), registo);

        int ocup = getOcupados();
        this.setOcupados(ocup + 1);

        return id;
    }

    public void registarPlanoTrabalho(String id, Map<Integer, Passo> planoTrabalho, LocalDateTime prazo){

        Registo aReparar = pedidosOrcamento.get(id);

        pedidosOrcamento.remove(id);

        //TODO somehow retornar a localizaçao
        String local = aReparar.getLocalizacao();

        //na interface vai ter de ser possivel adicionar os passos entre estas duas cenas

        //TODO por o sistema a enviar o email
        String email = aReparar.getCliente().getEmail();

        ReparacaoNormal r = (ReparacaoNormal) aReparar.getReparacao();
        r.definirOrcamento();
        r.setPrazoMaximo(prazo);
        r.setPlanoTrabalho(planoTrabalho);

        registosNConcluidos.put(aReparar.getId(), aReparar);
        aReparar.setDataNConcluido(LocalDateTime.now());
    }

    public void confirmarReparacao(String idEquipamento) {
        Registo value = this.registosPendentes.get(idEquipamento);

        this.registosPendentes.remove(idEquipamento);

        this.registosNConcluidos.put(idEquipamento,value);
        value.setDataNConcluido(LocalDateTime.now());
    }

    @Override
    public void registarConclusao(String idEquipamento, Tecnico tecnico){

        Registo concluido = this.registosNConcluidos.get(idEquipamento);
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
            String nr = concluido.getCliente().getTelemovel();
            System.out.println("Mensagem enviada para: " + nr);
        } else {
            ReparacaoExpresso r = (ReparacaoExpresso) concluido.getReparacao();
            desvio = Duration.ofSeconds(duracao.getSeconds() - r.getDuracaoPrevista().getSeconds());
            tecnico.atualizarMediaDesvio(desvio);
            int ocup = getOcupados();
            this.setOcupados(ocup-1);
            //TODO mandar mail
            String email = concluido.getCliente().getEmail();
            System.out.println("Mail enviado para: " + email);
        }
    }

    @Override
    public String obterInfoRegistoNConcluido(String id) throws RegistoNaoExisteException {
        Registo r = registosNConcluidos.get(id);
        if(r == null) throw new RegistoNaoExisteException();

        return r.getNomeEquipamento() + ";" + r.getUrgencia() + ";" + r.getDataPedido().format(DateTimeFormatter.ISO_DATE) + ";" + r.getReparacao().getPrazoMaximo().format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public void registarEntrega(String id) {
        Registo value = registosConcluidos.get(id);
        registosConcluidos.remove(id);
        registosEntregues.put(id, value);
        value.setDataEntregue(LocalDateTime.now());
    }

    public void registarEntregaDeEquipamentoRecusado(String id, Funcionario funcionario) {
        Registo value = registosPendentes.get(id);
        registosPendentes.remove(id);
        registosEntregues.put(id, value);
        value.setDataEntregue(LocalDateTime.now());
        funcionario.addEntrega();
    }

    public void equipamentoAbandonado(String id) {
        Registo value = registosConcluidos.get(id);
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
    public Passo getInfoProximoPasso(String id) {
        Registo value = registosNConcluidos.get(id);

        if(value == null) return null;

        ReparacaoNormal r = (ReparacaoNormal) value.getReparacao();

        return r.getInfoProximoPasso();
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

    @Override
    public Set<String> getRegistosNConcluidos() {
        return new HashSet<>(registosNConcluidos.keySet());
    }
}
