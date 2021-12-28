package tpdssln.ssempregados;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SSEmpregadosFacade implements ISSEmpregados {
    private Map<String, Empregado> empregados = new HashMap<>();

    public SSEmpregadosFacade() {
        this.empregados = new HashMap<>();
    }

    public Boolean autenticar(String id, String password) {

        Empregado value = empregados.get(id);
        if (value == null) return false; //TODO exception n existe

        if (password.equals(value.getPassword()))
            return true;
        else return false;
    }

    public Map<String, Empregado> acederTecnicos() {

        Map<String,Empregado> mapT = new HashMap<>();

        for (Empregado empregado : empregados.values()) {
            if (empregado instanceof Tecnico)
                mapT.put(empregado.getId(), empregado);
        }

        return mapT;
    }

    public Map<String, Empregado> acederFuncionario() {

        Map<String,Empregado> mapT = new HashMap<>();

        for (Empregado empregado : empregados.values()) {
            if (empregado instanceof Funcionario)
                mapT.put(empregado.getId(), empregado);
        }

        return mapT;
    }

    public String adicionarFuncionario(String nome, String password) {

        String id = generateID();

        Funcionario funcionario = new Funcionario(id,nome,password);
        empregados.put(id,funcionario);

        return id;
    }

    public String adicionarTecnico(String nome, String password) {

        String id = generateID();

        Tecnico tecnico = new Tecnico(id,nome,password);
        empregados.put(id,tecnico);

        return id;
    }

    public String adicionarGestor(String nome, String password) {

        String id = generateID();

        Gestor gestor = new Gestor(id,nome,password);
        empregados.put(id,gestor);

        return id;
    }

    public Empregado verEmpregado(String id){

        Empregado value = empregados.get(id);
        if (value == null)
            return null; //TODO exception n existe

        return value;
    }

    public void editarNome(String id, String nome) {

        Empregado value = empregados.get(id);
        if (value == null)
            return; //TODO exception n existe

        value.setNome(nome);
    }

    public void editarPassword(String id, String password) {

        Empregado value = empregados.get(id);
        if (value == null)
            return; //TODO exception n existe

        value.setPassword(password);
    }

    public void removerUtilizador(String id) {

        Empregado value = empregados.get(id);
        if (value == null)
            return; //TODO exception n existe

        empregados.remove(id);
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

        if (empregados.containsKey(generatedString))
            generatedString = generateID();

        return generatedString;
    }
}
