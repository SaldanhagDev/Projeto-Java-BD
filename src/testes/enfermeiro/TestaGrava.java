package testes.enfermeiro;

import persistencia.EnfermeiroDAO;

public class TestaGrava {
    public static void main(String[] args)throws Exception {
        System.out.println(EnfermeiroDAO.grava(3,"77777777777", "Igor", "3", "606060"));
       
    }

}
