package modelo;
public class Medico extends Pessoa {
    private int Matr;
    private int NumConsultorio;
    private Formacao formacao;
    private Paciente paciente;

 
    
    public int getMatr() {
        return Matr;
    }

    public void setMatr(int Matr) {
        this.Matr = Matr;
    }

    public int getNumConsultorio() {
        return NumConsultorio;
    }

    public void setNumConsultorio(int NumConsultorio) {
        this.NumConsultorio = NumConsultorio;
    }

    public Formacao getFormacao() {
        return formacao;
    }

    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico(String Cpf, String nome, Endereco endereco,int Matr, int NumConsultorio, Formacao formacao, Paciente paciente) {
        super(Cpf, nome, endereco);
        this.Matr = Matr;
        this.NumConsultorio = NumConsultorio;
        this.formacao = formacao;
        this.paciente = paciente;
    }
    public String toString(){
        return "\n Dados Do Médico:"+
                "\n Matrícula:"+getMatr()+
                "\n Número do Consultorio:"+getNumConsultorio()+
                "\n Formacao:"+getFormacao()+
                "\n Paciente:"+getPaciente();
                
        
    }

}
    