package tpdssln;

import tpdssln.ssempregados.*;
import tpdssln.ssreparacoes.ISSReparacoes;
import tpdssln.ssreparacoes.Passo;
import tpdssln.ssreparacoes.Reparacao;
import tpdssln.ssreparacoes.SSReparacoesFacade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TPDSSLNFacade implements ITPDSSLN {
    private ISSReparacoes reparacoes = new SSReparacoesFacade();
    private ISSEmpregados empregados = new SSEmpregadosFacade();

    public TPDSSLNFacade() {}

    @Override
    public Boolean autenticar(String id, String password) {
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


    }

    @Override
    public void repararProduto(String id) {

    }

    @Override
    public void registarEntrega(String id, Funcionario funcionario) {
        reparacoes.registarEntrega(id, funcionario);
    }



    @Override
    public void registarPlanoTrabalho(Map<Integer, Passo> planoTrabalho) {
        reparacoes.registarPlanoTrabalho(planoTrabalho);
    }

    @Override
    public void registarConclusao(String idEquipamento) {

    }

    @Override
    public void registarConclusao(String idEquipamento, Tecnico tecnico) {
        reparacoes.registarConclusao(idEquipamento, tecnico);
    }

    @Override
    public LocalDateTime obterPrazoMaximo() {
        return null;
    }

    @Override
    public int numRececoesEmpregado(String id) {
        return empregados.numRececoesEmpregado(id);
    }

    @Override
    public int numEntregasEmpregado(String id) {
        return empregados.numEntregasEmpregado(id);
    }

    @Override
    public List<String> toLstInfosPlanosTrabalho(String idTecnico) {
        return empregados.toLstInfosPlanosTrabalho(idTecnico);
    }

    public Map<String,List<String>> todosPlanosTrabalho(){
        return  empregados.todosPlanosTrabalho();
    }


}
