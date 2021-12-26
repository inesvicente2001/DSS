package SSReparacoes;

import java.util.HashMap;
import java.util.Map;

public class SSReparacoesFacade {
    public Map<String, Registo> registosConcluidos; //Os entregues
    public Map<String, Registo> registosNConcluidos; //Os por fazer

    public SSReparacoesFacade() {
        this.registosConcluidos = new HashMap<>();
        this.registosNConcluidos = new HashMap<>();
    }



    public void registarPasso(String id, int ordem, Passo passo){

        Reparacao reparacaoEditar =  this.registosNConcluidos.get(id).getReparacao();

        if( reparacaoEditar instanceof ReparacaoNormal ) {
            ((ReparacaoNormal)reparacaoEditar).addPasso(ordem, passo);
        }

    }


}
