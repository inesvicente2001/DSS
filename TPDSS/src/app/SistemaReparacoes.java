package app;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Administrador;
import tpdssln.ssempregados.Empregado;
import tpdssui.Login;
import tpdssui.admin.AdminMenuPrincipal;

public class SistemaReparacoes {
    public static void main(String[] args) {
        //new AdminMenuPrincipal(new TPDSSLNFacade(), "12");
        //TPDSSLNFacade ln = new TPDSSLNFacade();

        new Login(new TPDSSLNFacade());
    }
}