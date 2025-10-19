package testes.endereco;

import persistencia.EnderecoDAO;

public class TestaALtera {
    public static void main(String[] args)throws Exception {
        System.out.println(EnderecoDAO.altera(101010, "Rua da etb"));
    }

}
