package tpdssln.ssempregados;

import tpdssln.ssempregados.excecoes.CredenciaisErradasException;
import tpdssln.ssempregados.excecoes.EmpregadoNaoExisteException;
import tpdssln.ssreparacoes.Reparacao;
import tpdssln.ssreparacoes.ReparacaoNormal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SSEmpregadosFacade implements ISSEmpregados {
    private Map<String, Empregado> empregados;

    public SSEmpregadosFacade() {
        this.empregados = new HashMap<>();
        this.empregados.put("Admin",new Administrador("Admin","Admin","Admin"));
    }


    public int numRececoesEmpregado(String id){

        int numRececoes = -1;

        if (empregados.get(id) instanceof Funcionario)
            numRececoes = ((Funcionario) empregados.get(id)).getnRececoes();

        return numRececoes;
    }

    public int numEntregasEmpregado(String id){

        int numEntregas = -1;

        if (empregados.get(id) instanceof Funcionario)
            numEntregas = ((Funcionario) empregados.get(id)).getnEntregas();

        return numEntregas;

    }

    public void aumentarEntregasEmpregado(String id){


        if (empregados.get(id) instanceof Funcionario)
            ((Funcionario) empregados.get(id)).setnEntregas(((Funcionario) empregados.get(id)).getnEntregas() + 1);


    }


    public void aumentarRececoesEmpregado(String id){

        if (empregados.get(id) instanceof Funcionario)
            ((Funcionario) empregados.get(id)).setnRececoes(((Funcionario) empregados.get(id)).getnRececoes() + 1);

    }




    public Class<? extends Empregado> autenticar(String id, String password) throws CredenciaisErradasException {

        Empregado value = empregados.get(id);
        if (value == null || !value.getPassword().equals(password))
            throw new CredenciaisErradasException();

        return value.getClass();
    }

    public Map<String,Tecnico> acederTecnicos() {

        Map<String,Tecnico> mapT = new HashMap<>();

        for (Empregado empregado : empregados.values()) {
            if (empregado instanceof Tecnico)
                mapT.put(empregado.getId(), (Tecnico) empregado);
        }

        return mapT;
    }

    public Map<String, Funcionario> acederFuncionarios() {

        Map<String,Funcionario> mapT = new HashMap<>();

        for (Empregado empregado : empregados.values()) {
            if (empregado instanceof Funcionario)
                mapT.put(empregado.getId(), (Funcionario) empregado);
        }

        return mapT;
    }

    public String adicionarFuncionario(String nome, String password) {

        String id = generateID();

        System.out.println("Funcionário: " + nome + " | ID: " + id + " | " + password);

        Funcionario funcionario = new Funcionario(id,nome,password);
        empregados.put(id,funcionario);

        return id;
    }

    public String adicionarTecnico(String nome, String password) {

        String id = generateID();

        System.out.println("Técnico: " + nome + " | ID: " + id + " | " + password);

        Tecnico tecnico = new Tecnico(id,nome,password);
        empregados.put(id,tecnico);

        return id;
    }

    public String adicionarGestor(String nome, String password) {

        String id = generateID();
        System.out.println("Gestor: " + nome + " | ID: " + id + " | " + password);

        Gestor gestor = new Gestor(id,nome,password);
        empregados.put(id,gestor);

        return id;
    }

    public Empregado verEmpregado(String id) throws EmpregadoNaoExisteException {

        Empregado value = empregados.get(id);
        if (value == null)
            throw new EmpregadoNaoExisteException();

        return value;
    }

    @Override
    public boolean existeEmpregado(String id) {
        return empregados.containsKey(id);
    }

    public void editarNome(String id, String nome) throws EmpregadoNaoExisteException {

        Empregado value = empregados.get(id);
        if (value == null)
            throw new EmpregadoNaoExisteException();

        value.setNome(nome);
    }

    public void editarPassword(String id, String password) {

        Empregado value = empregados.get(id);
        value.setPassword(password);
    }

    public void removerUtilizador(String id) {

        Empregado value = empregados.get(id);
        empregados.remove(id);
    }

    public int nReparacoesNormaisTecnico(String id) {
        Tecnico t = (Tecnico)empregados.get(id);
        return t.nReparacoesNormais();
    }

    public int nReparacoesExpressoTecnico(String id) {
        Tecnico t = (Tecnico)empregados.get(id);
        return t.nReparacoesExpresso();
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


    public List<String> toLstInfosPlanosTrabalho(String idTecnico){

        List<String> lst = new ArrayList<>();

        if (empregados.get(idTecnico) instanceof Tecnico)
            lst = ((Tecnico) empregados.get(idTecnico)).toLstInfosPlanosTrabalho(idTecnico);

        return lst;
    }

    public Map<String,List<String>> todosPlanosTrabalho(){
        Map<String,List<String>> map= new HashMap<>();

        for (Map.Entry<String, Tecnico> entry : this.acederTecnicos().entrySet())
            map.put(entry.getKey(),toLstInfosPlanosTrabalho(entry.getKey()));

        return map;

    }
}
