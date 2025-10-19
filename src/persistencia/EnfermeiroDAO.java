package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Endereco;
import modelo.Formacao;
import modelo.Paciente;
import modelo.Enfermeiro;



public class EnfermeiroDAO {

    
     public static int grava(int matr, String cpf, String nome, String formacao, String endereco) {
        int ret = 0;
        try {
            Connection con = GerenteDeConexao.getConnection();
            String sql = "INSERT INTO Enfermeiro (matr, cpf, nome, formacao, endereco) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, matr);
            st.setString(2, cpf);
            st.setString(3, nome);
            st.setString(4, formacao);
            st.setString(5, endereco);
            ret = st.executeUpdate();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    
  public static List<Enfermeiro> leTodos(String curso) {
    List<Enfermeiro> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT e.matr, e.cpf, e.nome, " +
                     "f.Id, f.Curso, f.Instituicao, " +
                     "end.rua, end.cep " +
                     "FROM Enfermeiro e " +
                     "JOIN Formacao f ON e.formacao = f.Id " +
                     "JOIN Endereco end ON e.endereco = end.cep " +
                     "WHERE f.Curso LIKE ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, "%" + curso + "%");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int matr = rs.getInt("matr");
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            int idForm = rs.getInt("Id");
            String cursoForm = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");

            Formacao formacao = new Formacao(idForm, cursoForm, instituicao);
            Endereco endereco = new Endereco(rua, cep);
            Enfermeiro enf = new Enfermeiro(formacao, cpf, nome, endereco, matr);
            lista.add(enf);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}


      
    public static List<Enfermeiro> leTodos1(int id1, int id2, int id3) {
    List<Enfermeiro> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT e.matr, e.cpf, e.nome, " +
                     "f.Id, f.Curso, f.Instituicao, " +
                     "end.rua, end.cep " +
                     "FROM Enfermeiro e " +
                     "JOIN Formacao f ON e.formacao = f.Id " +
                     "JOIN Endereco end ON e.endereco = end.cep " +
                     "WHERE f.Id IN (?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id1);
        st.setInt(2, id2);
        st.setInt(3, id3);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int matr = rs.getInt("matr");
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            int idForm = rs.getInt("Id");
            String curso = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");

            Formacao formacao = new Formacao(idForm, curso, instituicao);
            Endereco endereco = new Endereco(rua, cep);
            Enfermeiro enf = new Enfermeiro(formacao, cpf, nome, endereco, matr);
            lista.add(enf);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}


    
  public static List<Enfermeiro> leTodos2(int idMin, int idMax) {
    List<Enfermeiro> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT e.matr, e.cpf, e.nome, " +
                     "f.Id, f.Curso, f.Instituicao, " +
                     "end.rua, end.cep " +
                     "FROM Enfermeiro e " +
                     "JOIN Formacao f ON e.formacao = f.Id " +
                     "JOIN Endereco end ON e.endereco = end.cep " +
                     "WHERE f.Id NOT BETWEEN ? AND ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idMin);
        st.setInt(2, idMax);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int matr = rs.getInt("matr");
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            int idForm = rs.getInt("Id");
            String curso = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");

            Formacao formacao = new Formacao(idForm, curso, instituicao);
            Endereco endereco = new Endereco(rua, cep);
            Enfermeiro enf = new Enfermeiro(formacao, cpf, nome, endereco, matr);
            lista.add(enf);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}


 
   public static Enfermeiro leUm(int idFormacao) {
    Enfermeiro enfermeiro = null;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT e.matr, e.cpf, e.nome, " +
                     "f.Id, f.Curso, f.Instituicao, " +
                     "end.rua, end.cep " +
                     "FROM Enfermeiro e " +
                     "JOIN Formacao f ON e.formacao = f.Id " +
                     "JOIN Endereco end ON e.endereco = end.cep " +
                     "WHERE f.Id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idFormacao);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            int matr = rs.getInt("matr");
            String cpf = rs.getString("cpf");
            String nome = rs.getString("nome");
            String curso = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");

            Formacao formacao = new Formacao(idFormacao, curso, instituicao);
            Endereco endereco = new Endereco(rua, cep);
            enfermeiro = new Enfermeiro(formacao, cpf, nome, endereco, matr);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return enfermeiro;
}



    
    public static int altera(int matr, int novoIdFormacao) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Enfermeiro SET formacao = ? WHERE matr = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, novoIdFormacao);
        st.setInt(2, matr);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}


   public static int altera1(int id, String curso, String novaInstituicao) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Formacao SET Instituicao = ? WHERE Id = ? AND Curso = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novaInstituicao);
        st.setInt(2, id);
        st.setString(3, curso);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}



   public static int altera2(int matr, int novoId) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Enfermeiro SET formacao = ? WHERE matr = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, novoId);
        st.setInt(2, matr);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}




    public static int exclui(int matr) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "DELETE FROM Enfermeiro WHERE Matr = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, matr);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}
}

