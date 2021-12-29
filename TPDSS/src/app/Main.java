package app;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Administrador;
import tpdssln.ssempregados.Gestor;
import tpdssln.ssempregados.Tecnico;
import tpdssui.Login;
import tpdssui.funcionario.FuncionarioMenuPrincipal;
import tpdssui.gestor.GestorMenuPrincipal;

public class Main {
    public static void main(String[] args) {
        //new TecnicoMenuPrincipal();
        ITPDSSLN ln = new TPDSSLNFacade();

        //new Loading(ln);




        new Login(ln);
    }
}
