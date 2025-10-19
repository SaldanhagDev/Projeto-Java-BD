package teste.conexao;
import java.sql.SQLException; //importar a biblioteca java.sql.SQLException
import persistencia.GerenteDeConexao; //importar classe GerenteDeConexao do pacote persistencia
public class TestaGerenteDeConexao {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println(GerenteDeConexao.getConnection());

    }

}
