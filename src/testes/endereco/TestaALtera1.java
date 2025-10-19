package testes.endereco;

import persistencia.EnderecoDAO;

public class TestaALtera1 {
    public static void main(String[] args)throws Exception {
        System.out.println(EnderecoDAO.altera1(101010, "rua da etb", "rua da catolica"));
       
    }

}
