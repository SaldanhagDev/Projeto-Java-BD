package modelo;
public class Endereco {
    private String Rua;
    private int cep;

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }
    
    public Endereco(String Rua,int cep){
        this.Rua=Rua;
        this.cep=cep;
    }
    public String toString(){
        return "\n Dados do Endereço:"+
                "\n Rua:"+getRua()+
                "\n Cep:"+getCep();
    }
    

}
