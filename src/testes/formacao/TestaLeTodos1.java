package testes.formacao;

import persistencia.FormacaoDAO;

public class TestaLeTodos1 {
    public static void main(String[] args)throws Exception {
        System.out.println(FormacaoDAO.leTodos1(1, 2, 3));
       
    }

}
