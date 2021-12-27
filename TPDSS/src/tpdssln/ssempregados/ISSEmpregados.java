package tpdssln.ssempregados;

import java.util.Map;

public interface ISSEmpregados {
    public Boolean autenticar(String id, String password);
    public Map<String, Empregado> acederTecnicos();
    public Map<String, Empregado> acederFuncionario();
    public String adicionarTecnico(String nome, String password);
    public String adicionarFuncionario(String nome, String password);
    public String adicionarGestor(String nome, String password);
    public Empregado verEmpregado(String id);
    public void editarNome(String id, String nome);
    public void editarPassword(String id, String password);
    public void removerUtilizador(String id);

}
