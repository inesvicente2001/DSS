package app;

import tpdssln.TPDSSLNFacade;
import tpdssui.Login;

public class Main {
    public static void main(String[] args) {
        new Login(new TPDSSLNFacade());
    }
}
