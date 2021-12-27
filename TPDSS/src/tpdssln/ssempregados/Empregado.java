package tpdssln.ssempregados;


public abstract class Empregado {
    public String id;
    public String nome;
    public String password;

    public Empregado(String id, String nome, String password) {
        this.id = id;
        this.nome = nome;
        this.password = password;
    }
}
