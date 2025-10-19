package testes.endereco;

import persistencia.EnderecoDAO;

public class TestaLeTodos1 {
    public static void main(String[] args)throws Exception {
        System.out.println(EnderecoDAO.leTodos1(101010, 202020, 303030));
       
    }

}
