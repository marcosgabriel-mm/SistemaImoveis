package Model;

public class DetalhesVendedor {
    private String cpf;
    private String nome;
    private String email;
    private String fone;
    private String contatoPref;

    public DetalhesVendedor(String cpf, String nome, String email, String fone, String contatoPref) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
        this.contatoPref = contatoPref;
    }

    // Getters e Setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getContatoPref() {
        return contatoPref;
    }

    public void setContatoPref(String contatoPref) {
        this.contatoPref = contatoPref;
    }
}
