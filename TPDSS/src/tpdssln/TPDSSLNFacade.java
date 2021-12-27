package tpdssln;

import tpdssln.ssempregados.Empregado;
import tpdssln.ssempregados.ISSEmpregados;
import tpdssln.ssempregados.SSEmpregadosFacade;
import tpdssln.ssreparacoes.ISSReparacoes;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Reparacao;
import tpdssln.ssreparacoes.SSReparacoesFacade;

import java.time.LocalDateTime;
import java.util.Map;

public class TPDSSLNFacade implements ITPDSSLN {
    ISSReparacoes reparacoes = new SSReparacoesFacade();
    ISSEmpregados empregados = new SSEmpregadosFacade();

    public TPDSSLNFacade() {}

    @Override
    public Boolean autenticar(String id, String password) {
        return empregados.autenticar(id, password);
    }

    @Override
    public Map<String, Empregado> acederTecnicos() {
        return empregados.acederTecnicos();
    }

    @Override
    public Map<String, Empregado> acederFuncionario() {
        return empregados.acederFuncionario();
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
    public Empregado verEmpregado(String id) {
        return empregados.verEmpregado(id);
    }

    @Override
    public void editarNome(String id, String nome) {
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
        reparacoes.registarEntrega(id);
    }

    @Override
    public void repararProduto(String id) {
        reparacoes.registarEntrega(id);
    }

    @Override
    public void registarPlanoTrabalho(Map<Integer, Passo> planoTrabalho) {
        reparacoes.registarPlanoTrabalho(planoTrabalho);
    }

    @Override
    public void registarConclusao(String idEquipamento) {
        reparacoes.registarConclusao(idEquipamento);
    }

    @Override
    public LocalDateTime obterPrazoMaximo() {
        return reparacoes.obterPrazoMaximo();
    }
}