package SSEmpregados;

import java.util.Map;

public abstract class Empregado {
    public String id;
    public String nome;
    public String password;


    public Boolean autenticar(String id, String password) {
        //fezer cenas
        return null;
    }

    public Map<String, Tecnico> acederTecnicos() {
        return null;
    }

    public Map<String, Funcionario> acederFuncionario() {
        return null;
    }

    public String adicionarUtilizador(String id, String nome) {
        return null;
    }

    public void editarUtilizador(String id) {

    }

    public void removerUtilizador(String id) {

    }
}
