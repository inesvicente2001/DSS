package tpdssln;

import tpdssln.ssempregados.*;
import tpdssln.ssempregados.excecoes.CredenciaisErradasException;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssln.ssreparacoes.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TPDSSLNFacade implements ITPDSSLN {
    public SSReparacoesFacade reparacoes = new SSReparacoesFacade();
    private ISSEmpregados empregados = new SSEmpregadosFacade();

    public TPDSSLNFacade() {}

    @Override
    public void adicionarPedidoOrcamentoNormal(String nomeEquipamento, int urgencia, String descricao,
                                               String local, LocalDateTime prazo, String nomeCliente, String nif,
                                               String telemovel, String email, Funcionario funcionario) {
        reparacoes.adicionarPedidoOrcamentoNormal(nomeEquipamento, urgencia, descricao, local, prazo, nomeCliente, nif, telemovel, email, funcionario);
    }

    @Override
    public void adicionarPedidoOrcamentoExpresso(String nomeEquipamento, int urgencia, String descricao,
                                                 String local, LocalDateTime prazo, float precoFixo,
                                                 Duration duracaoPrevista, String nomeCliente, String nif,
                                                 String telemovel, String email, Funcionario funcionario) {
        reparacoes.adicionarPedidoOrcamentoExpresso(
                nomeEquipamento, urgencia, descricao,
                local, prazo, precoFixo,
                duracaoPrevista, nomeCliente, nif,
                telemovel, email, funcionario);
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
    public void registarEntrega(String id) {


    }

    @Override
    public void repararProduto(String id) {

    }

    @Override
    public void registarEntrega(String id, Funcionario funcionario) {
        reparacoes.registarEntrega(id, funcionario);
    }



    @Override
    public void registarPlanoTrabalho(String id, Map<Integer, Passo> planoTrabalho) {
        reparacoes.registarPlanoTrabalho(id, planoTrabalho);
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

    public String toHTMLDescricao(String id) throws NullPointerException {
        return reparacoes.toHTMLDescricao(id);
    }
}
