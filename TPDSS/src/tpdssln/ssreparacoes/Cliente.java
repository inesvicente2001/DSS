package tpdssln.ssreparacoes;

public class Cliente {
    private String nomeCliente;
    private String nif;
    private String telemovel;
    private String email;

    public Cliente(String nomeCliente, String nif, String telemovel, String email) {
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.telemovel = telemovel;
        this.email = email;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
