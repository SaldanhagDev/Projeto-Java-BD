package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Paciente;
import modelo.Endereco;

public class PacienteDAO {

    
    public static int grava(String cpf, String nome,int idade,String sintomas,String endereco) {
        int ret = 0;
        try {
            Connection con = GerenteDeConexao.getConnection();
            String sql = "INSERT INTO Paciente (cpf,nome,idade,sintomas,endereco) VALUES (?, ?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cpf);
            st.setString(2, nome);
            st.setInt(3, idade);
            st.setString(4, sintomas);
            st.setString(5, endereco);
            ret = st.executeUpdate();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return ret;
    }

    
   public static List<Paciente> leTodos(String nome) {
    List<Paciente> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();

        
        String sql = "SELECT p.cpf, p.nome, p.idade, p.sintomas, e.rua, e.cep " +
                     "FROM Paciente p " +
                     "JOIN Endereco e ON p.endereco = e.cep " +
                     "WHERE p.nome LIKE ?";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, "%" + nome + "%");  

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String cpf = rs.getString("cpf");
            String nomePaciente = rs.getString("nome");
            int idade = rs.getInt("idade");
            String sintomas = rs.getString("sintomas");
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");

            
            Endereco endereco = new Endereco(rua, cep);

            
            Paciente paciente = new Paciente(cpf, nomePaciente, endereco, idade, sintomas);

            lista.add(paciente);
        }

        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}


      
    public static List<Paciente> leTodos1(String cpf1, String cpf2, String cpf3) {
    List<Paciente> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();

        String sql = "SELECT p.cpf, p.nome, p.idade, p.sintomas, e.rua, e.cep " +
                     "FROM Paciente p " +
                     "JOIN Endereco e ON p.endereco = e.cep " +
                     "WHERE p.cpf IN (?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, cpf1);
        st.setString(2, cpf2);
        st.setString(3, cpf3);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            int idade = rs.getInt("idade");
            String sintomas = rs.getString("sintomas");
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");

            Endereco endereco = new Endereco(rua, cep);
            Paciente paciente = new Paciente(cpf, nome, endereco, idade, sintomas);
            lista.add(paciente);
        }

        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}

    
  public static List<Paciente> leTodos2(int idadeMin, int idadeMax) {
    List<Paciente> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();

        String sql = "SELECT p.cpf, p.nome, p.idade, p.sintomas, e.rua, e.cep " +
                     "FROM Paciente p " +
                     "JOIN Endereco e ON p.endereco = e.cep " +
                     "WHERE p.idade NOT BETWEEN ? AND ?";

        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idadeMin);
        st.setInt(2, idadeMax);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            int idade = rs.getInt("idade");
            String sintomas = rs.getString("sintomas");
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");

            Endereco endereco = new Endereco(rua, cep);
            Paciente paciente = new Paciente(cpf, nome, endereco, idade, sintomas);
            lista.add(paciente);
        }

        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}




    
    public static Paciente leUm(String cpf) {
    Paciente paciente = null;
    try {
        Connection con = GerenteDeConexao.getConnection();

        String sql = "SELECT p.cpf, p.nome, p.idade, p.sintomas, "
                   + "e.rua, e.cep "
                   + "FROM Paciente p "
                   + "JOIN Endereco e ON p.endereco = e.cep "
                   + "WHERE p.cpf = ?";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, cpf);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            String nome     = rs.getString("nome");
            int    idade    = rs.getInt("idade");
            String sintomas = rs.getString("sintomas");
            String rua      = rs.getString("rua");
            int    cep      = rs.getInt("cep");

            Endereco endereco = new Endereco(rua, cep);
            paciente = new Paciente(cpf, nome, endereco, idade, sintomas);
        }

        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return paciente;  
}


    
    public static int altera(String cpf, String novoNome) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Paciente SET nome = ? WHERE cpf = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novoNome);
        st.setString(2, cpf);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}

    public static int altera1(String cpf, int idade, String novoSintoma) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Paciente SET sintomas = ? WHERE cpf = ? AND idade = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novoSintoma);
        st.setString(2, cpf);
        st.setInt(3, idade);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}

   public static int altera2(String novoSintoma, String cpf1, String cpf2) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Paciente SET sintomas = ? WHERE cpf NOT IN (?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novoSintoma);
        st.setString(2, cpf1);
        st.setString(3, cpf2);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}



    public static int exclui(String cpf) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "DELETE FROM Paciente WHERE cpf = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, cpf);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}
}

