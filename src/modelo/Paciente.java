package modelo;
public class Paciente extends Pessoa {
    private int idade;
    private String sintomas;

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public Paciente( String Cpf, String nome, Endereco endereco,int idade, String sintomas) {
        super(Cpf, nome, endereco);
        this.idade = idade;
        this.sintomas = sintomas;
    }
    public String toString(){
        return "\n Dados Do Paciente:"+
                "\n CPF:"+getCpf()+
                "\n nome:"+getNome()+
                "\n Idade:"+getIdade()+
                "\n Endereço:"+getEndereco()+
                "\n Sintomas:"+getSintomas();
    }
    

}
