package tpdssln.ssempregados;

import tpdssln.ssreparacoes.Reparacao;
import tpdssln.ssreparacoes.ReparacaoNormal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SSEmpregadosFacade implements ISSEmpregados {
    private Map<String, Empregado> empregados;

    public SSEmpregadosFacade() {
        this.empregados = new HashMap<>();


        Tecnico fun1 = new Tecnico("123","Rogerio Bala", "123");
        Map<String, Reparacao> test = new HashMap<>();
        test.put("123", new ReparacaoNormal(LocalDateTime.now()));
        fun1.setReparacoes(test);
        fun1.setMediaDesvio(Duration.ofDays(23));
        fun1.setDuracaoMedia(Duration.ofDays(34));
        Tecnico fun2  = new Tecnico("122222","Tomas F.", "Furry");
       
        Tecnico fun3 = new Tecnico("12","Gui", "0");
       
        Tecnico fun4  = new Tecnico("1","Tomas F.", "Furry");
       
        Tecnico fun5  = new Tecnico("2","Tomas F.", "Furry");
        
        Tecnico fun6  = new Tecnico("3","Tomas F.", "Furry");
        
        this.empregados.put("123",fun1);
        this.empregados.put("122222", fun2 );
        this.empregados.put("12", fun3);
        this.empregados.put("1", fun4);
        this.empregados.put("2", fun5);
        this.empregados.put("3", fun6);
        this.empregados.put("42", new Gestor("42","JBB","monos"));
        this.empregados.put("420",new Administrador("420","Creissac","DSS"));
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

    public Boolean autenticar(String id, String password) {

        Empregado value = empregados.get(id);
        if (value == null) return false; //TODO exception n existe

        if (password.equals(value.getPassword()))
            return true;
        else return false;
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


    public List<String> toLstInfosPlanosTrabalho(String idTecnico){

        List<String> lst = new ArrayList<>();

        if (empregados.get(idTecnico) instanceof Tecnico)
            lst = ((Tecnico) empregados.get(idTecnico)).toLstInfosPlanosTrabalho(idTecnico);

        return lst;
    }

    public Map<String,List<String>> todosPlanosTrabalho(){
        Map<String,List<String>> map= new HashMap<>();

        for (Map.Entry<String, Funcionario> entry : this.acederFuncionarios().entrySet())
            map.put(entry.getKey(),toLstInfosPlanosTrabalho(entry.getKey()));

        return map;

    }
}
