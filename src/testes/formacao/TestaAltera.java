package testes.formacao;

import persistencia.FormacaoDAO;

public class TestaAltera {
    public static void main(String[] args)throws Exception  {
        System.out.println(FormacaoDAO.altera(1, "medicina"));
       
    }

}
