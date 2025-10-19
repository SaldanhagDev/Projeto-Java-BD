package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Paciente;
import modelo.Endereco;
import modelo.Formacao;
import modelo.Medico;

public class MedicoDAO {

    
    public static int grava(int matr, String cpf, String nome, int numConsultorio, int formacao, int endereco, String paciente) {
    int ret = 0;
    Connection con = null;
    PreparedStatement st = null;
    try {
        con = GerenteDeConexao.getConnection();
        String sql = "INSERT INTO Medico (matr, cpf, nome, numConsultorio, formacao, endereco, paciente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        st = con.prepareStatement(sql);
        st.setInt(1, matr);
        st.setString(2, cpf);
        st.setString(3, nome);
        st.setInt(4, numConsultorio);
        st.setInt(5, formacao);
        st.setInt(6, endereco);
        st.setString(7, paciente);
        ret = st.executeUpdate();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    } finally {
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    return ret;
}



    
  public static List<Medico> leTodos(String curso) {
    List<Medico> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT m.matr, m.cpf, m.nome, m.numConsultorio, " +
                     "f.Id, f.Curso, f.Instituicao, " +
                     "end.rua, end.cep, " +
                     "p.cpf AS cpfPaciente, p.nome AS nomePaciente, p.idade, p.sintomas, " +
                     "pend.rua AS ruaPaciente, pend.cep AS cepPaciente " +
                     "FROM Medico m " +
                     "JOIN Formacao f ON m.formacao = f.Id " +
                     "JOIN Endereco end ON m.endereco = end.cep " +
                     "JOIN Paciente p ON m.paciente = p.cpf " +
                     "JOIN Endereco pend ON p.endereco = pend.cep " +
                     "WHERE f.Curso LIKE ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, "%" + curso + "%");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int matr = rs.getInt("matr");
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            int numConsultorio = rs.getInt("numConsultorio");

            int idForm = rs.getInt("Id");
            String cursoForm = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            Formacao formacao = new Formacao(idForm, cursoForm, instituicao);

            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");
            Endereco endereco = new Endereco(rua, cep);

            String cpfPaciente = rs.getString("cpfPaciente");
            String nomePaciente = rs.getString("nomePaciente");
            int idade = rs.getInt("idade");
            String sintomas = rs.getString("sintomas");
            String ruaPaciente = rs.getString("ruaPaciente");
            int cepPaciente = rs.getInt("cepPaciente");
            Endereco enderecoPaciente = new Endereco(ruaPaciente, cepPaciente);
            Paciente paciente = new Paciente(cpfPaciente, nomePaciente, enderecoPaciente, idade, sintomas);

            Medico medico = new Medico(cpf, nome, endereco, matr, numConsultorio, formacao, paciente);
            lista.add(medico);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}





      
   public static List<Medico> leTodos1(int matr1, int matr2, int matr3) {
    List<Medico> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT m.matr, m.cpf, m.nome, m.numConsultorio, " +
                     "f.Id AS fId, f.Curso, f.Instituicao, " +
                     "end.rua, end.cep, " +
                     "p.cpf AS pCpf, p.nome AS pNome, p.idade, p.sintomas, " +
                     "endP.rua AS pRua, endP.cep AS pCep " +
                     "FROM Medico m " +
                     "JOIN Formacao f ON m.formacao = f.Id " +
                     "JOIN Endereco end ON m.endereco = end.cep " +
                     "JOIN Paciente p ON m.paciente = p.cpf " +
                     "JOIN Endereco endP ON p.endereco = endP.cep " +
                     "WHERE m.matr IN (?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, matr1);
        st.setInt(2, matr2);
        st.setInt(3, matr3);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int matr = rs.getInt("matr");
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            int numConsultorio = rs.getInt("numConsultorio");

            int fId = rs.getInt("fId");
            String curso = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            Formacao formacao = new Formacao(fId, curso, instituicao);

            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");
            Endereco endereco = new Endereco(rua, cep);

            String pCpf = rs.getString("pCpf");
            String pNome = rs.getString("pNome");
            int idade = rs.getInt("idade");
            String sintomas = rs.getString("sintomas");
            String pRua = rs.getString("pRua");
            int pCep = rs.getInt("pCep");
            Endereco enderecoPaciente = new Endereco(pRua, pCep);
            Paciente paciente = new Paciente(pCpf, pNome, enderecoPaciente, idade, sintomas);

            Medico medico = new Medico(cpf, nome, endereco, matr, numConsultorio, formacao, paciente);
            lista.add(medico);
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
    return paciente;   // devolve null se não achar
}


    
    public static int altera(String nomeAtual, String novoNome) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Medico SET nome = ? WHERE nome = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novoNome);
        st.setString(2, nomeAtual);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}

   public static int altera1(String cpf, String nome, int novoConsultorio) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Medico SET numConsultorio = ? WHERE cpf = ? AND nome = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, novoConsultorio);
        st.setString(2, cpf);
        st.setString(3, nome);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}


  public static int altera2(int novoNumConsultorio, int matr1, int matr2) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Medico SET numConsultorio = ? WHERE matr NOT IN (?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, novoNumConsultorio);
        st.setInt(2, matr1);
        st.setInt(3, matr2);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}




    public static int exclui(int Matr) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "DELETE FROM Medico WHERE Matr = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, Matr);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}
}

