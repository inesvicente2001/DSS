package tpdssln.ssempregados;


import java.io.Serializable;

public class Gestor extends Empregado implements Serializable {

    public Gestor(String id, String nome, String password) {
        super(id,nome,password);
    }
}
