package testes.endereco;

import persistencia.EnderecoDAO;

public class TestaGrava {
    public static void main(String[] args)throws Exception {
        System.out.println(EnderecoDAO.grava(707070, "asa sul"));
       
    }

}
