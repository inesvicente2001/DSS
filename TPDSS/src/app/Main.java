package app;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssui.funcionario.FuncionarioMenuPrincipal;

public class Main {
    public static void main(String[] args) {
        //new TecnicoMenuPrincipal();
        ITPDSSLN ln = new TPDSSLNFacade();

        //new Loading(ln);

        new FuncionarioMenuPrincipal(ln);
    }
}
