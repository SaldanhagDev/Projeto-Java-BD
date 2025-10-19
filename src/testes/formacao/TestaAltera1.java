package testes.formacao;

import persistencia.FormacaoDAO;

public class TestaAltera1 {
    public static void main(String[] args)throws Exception {
        System.out.println(FormacaoDAO.altera1(3, "clinico geral", "Unieuro"));
       
    }

}
