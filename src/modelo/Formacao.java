package modelo;
public class Formacao {
    private int Id;
    private String curso;
    private String instituicao;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public Formacao(int Id, String curso, String instituicao) {
        this.Id = Id;
        this.curso = curso;
        this.instituicao = instituicao;
    }
    public String toString(){
        return "\n Dados Da Formação:"+
                "\n Id:"+getId()+
                "\n Curso:"+getCurso()+
                "\n instituição:"+getInstituicao();
    }

}
