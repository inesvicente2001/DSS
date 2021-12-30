package tpdssln;

import tpdssln.ssempregados.*;
import tpdssln.ssempregados.excecoes.CredenciaisErradasException;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssln.ssreparacoes.*;
import tpdssln.ssreparacoes.excecoes.RegistoNaoExisteException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TPDSSLNFacade implements ITPDSSLN {
    private ISSReparacoes reparacoes = new SSReparacoesFacade();
    private ISSEmpregados empregados = new SSEmpregadosFacade();

    public TPDSSLNFacade() {}

    @Override
    public String adicionarPedidoOrcamentoNormal(String nomeEquipamento, int urgencia, String descricao,
                                                 String local, String nomeCliente, String nif, String telemovel, String email) {
        return reparacoes.adicionarPedidoOrcamentoNormal(nomeEquipamento, urgencia, descricao, local, nomeCliente, nif, telemovel, email);
    }

    @Override
    public String adicionarPedidoOrcamentoExpresso(String nomeEquipamento, int urgencia, String descricao,
                                                   String local, float precoFixo,
                                                   Duration duracaoPrevista, String nomeCliente, String nif,
                                                   String telemovel, String email) {
        return reparacoes.adicionarPedidoOrcamentoExpresso(
                nomeEquipamento, urgencia, descricao,
                local, precoFixo,
                duracaoPrevista, nomeCliente, nif,
                telemovel, email);
    }
    @Override
    public Class<? extends Empregado> autenticar(String id, String password) throws CredenciaisErradasException {
        return empregados.autenticar(id, password);
    }

    @Override
    public Map<String, Tecnico> acederTecnicos() {
        return empregados.acederTecnicos();
    }

    @Override
    public Map<String, Funcionario> acederFuncionarios() {
        return empregados.acederFuncionarios();
    }

    @Override
    public String adicionarTecnico(String nome, String password) {
        return empregados.adicionarTecnico(nome, password);
    }

    @Override
    public String adicionarFuncionario(String nome, String password) {
        return empregados.adicionarFuncionario(nome, password);
    }

    @Override
    public String adicionarGestor(String nome, String password) {
        return empregados.adicionarGestor(nome, password);
    }

    @Override
    public Empregado verEmpregado(String id) throws EmpregadoNaoExisteException {
        return empregados.verEmpregado(id);
    }

    @Override
    public boolean existeEmpregado(String id) {
        return empregados.existeEmpregado(id);
    }

    @Override
    public void editarNome(String id, String nome) throws EmpregadoNaoExisteException {
        empregados.editarNome(id, nome);
    }

    @Override
    public void editarPassword(String id, String password) {
        empregados.editarPassword(id, password);
    }

    @Override
    public void removerUtilizador(String id) {
        empregados.removerUtilizador(id);
    }

    @Override
    public Reparacao registarReparacao(String nome, String descricao, LocalDateTime prazoMaximo) {
        return reparacoes.registarReparacao(nome, descricao, prazoMaximo);
    }


    @Override
    public void repararProduto(String id) {

    }

    @Override
    public void registarEntrega(String id) {
        reparacoes.registarEntrega(id);
    }



    @Override
    public void registarPlanoTrabalho(String id, Map<Integer, Passo> planoTrabalho, LocalDateTime prazo) {
        reparacoes.registarPlanoTrabalho(id, planoTrabalho, prazo);
    }

    @Override
    public void registarConclusao(String idEquipamento) {

    }

    @Override
    public void registarConclusao(String idEquipamento, Tecnico tecnico) {
        reparacoes.registarConclusao(idEquipamento, tecnico);
    }

    @Override
    public int numRececoesEmpregado(String id) {
        return empregados.numRececoesEmpregado(id);
    }

    @Override
    public int numEntregasEmpregado(String id) {
        return empregados.numEntregasEmpregado(id);
    }

    public Registo maisUrgente() {
        return reparacoes.maisUrgente();
    }
    @Override
    public List<String> toLstInfosPlanosTrabalho(String idTecnico) {
        return empregados.toLstInfosPlanosTrabalho(idTecnico);
    }

    public Map<String,List<String>> todosPlanosTrabalho(){
        return  empregados.todosPlanosTrabalho();
    }

    @Override
    public void aumentarEntregasEmpregado(String id) {

        empregados.aumentarEntregasEmpregado(id);

    }

    @Override
    public void aumentarRececoesEmpregado(String id) {

        empregados.aumentarRececoesEmpregado(id);

    }

    public String toHTMLDescricao(String id) throws NullPointerException {
        return reparacoes.toHTMLDescricao(id);
    }

    @Override
    public Set<String> getRegistosNConcluidos() {
        return reparacoes.getRegistosNConcluidos();
    }

    @Override
    public String obterInfoRegistoNConcluido(String id) throws RegistoNaoExisteException {
        return reparacoes.obterInfoRegistoNConcluido(id);
    }

    @Override
    public Passo getInfoProximoPasso(String id) {
        return reparacoes.getInfoProximoPasso(id);
    }

    @Override
    public void iniciarPasso(String id) {
        reparacoes.iniciarPasso(id);
    }

    @Override
    public void concluirPasso(String id, Tecnico t) {
        reparacoes.concluirPasso(id,t);
    }

    public int getDisponibilidade() {
        return reparacoes.getDisponibilidade();
    }

    public int getOcupados() {
        return reparacoes.getOcupados();
    }

    public void setOcupados(int ocupados) {
        reparacoes.setOcupados(ocupados);
    }
}
