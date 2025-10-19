package modelo;
public abstract class Pessoa {
    private String Cpf;
    private String nome;
    private Endereco endereco;

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa(String Cpf, String nome, Endereco endereco) {
        this.Cpf = Cpf;
        this.nome = nome;
        this.endereco = endereco;
    }
    
    
}
