package tpdssln.ssempregados;

import java.io.Serializable;

public class Administrador extends Empregado implements Serializable {
    public Administrador(String id, String nome, String password) {
        super(id,nome,password);
    }
}
