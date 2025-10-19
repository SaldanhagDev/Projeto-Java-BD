package testes.formacao;

import persistencia.FormacaoDAO;

public class TestaLeTodos {
    public static void main(String[] args)throws Exception {
        System.out.println(FormacaoDAO.leTodos(""));
       
    }

}
