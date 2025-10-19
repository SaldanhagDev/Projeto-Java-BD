package modelo;
public class Enfermeiro extends Pessoa {
    private int Matr;
    private Formacao formacao;

    public int getMatr() {
        return Matr;
    }

    public void setMatr(int Matr) {
        this.Matr = Matr;
    }

    public Formacao getFormacao() {
        return formacao;
    }

    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
    }

    public Enfermeiro( Formacao formacao, String Cpf, String nome, Endereco endereco,int Matr) {
        super(Cpf, nome, endereco);
        this.Matr = Matr;
        this.formacao = formacao;
    }
    public String toString(){
        return "\n Dados do Enfermeiro:"+
                "\n CPF:"+getCpf()+
                "\n Nome:"+getNome()+
                "\n Endereço:"+getEndereco()+
                "\n Formacao:"+getFormacao()+
                "\n Matrícula:"+getMatr();
    }

}

    

