package app;

import tpdssln.ITPDSSLN;
import tpdssln.TPDSSLNFacade;
import tpdssln.ssempregados.Administrador;
import tpdssln.ssempregados.Gestor;
import tpdssln.ssempregados.Tecnico;
import tpdssui.Login;
import tpdssui.funcionario.FuncionarioMenuPrincipal;
import tpdssui.gestor.GestorMenuPrincipal;
import tpdssui.gestor.PormenoresTecnico;
import tpdssui.gestor.ReparacaoInfoCard;
import tpdssui.gestor.TecnicoInfoPormCard;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Login(new TPDSSLNFacade());
    }
}
