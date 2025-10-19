package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Endereco;
import modelo.Formacao;
import modelo.Paciente;



public class FormacaoDAO {

    
     public static int grava(int id, String curso, String instituicao) {
        int ret = 0;
        try {
            Connection con = GerenteDeConexao.getConnection();
            String sql = "INSERT INTO Formacao (Id, Curso, Instituicao) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, curso);
            st.setString(3, instituicao);
            ret = st.executeUpdate();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    
   public static List<Formacao> leTodos(String curso) {
    List<Formacao> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT * FROM Formacao WHERE Curso LIKE ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, "%" + "%");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Id");
            String cursoName = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            Formacao f = new Formacao(id, cursoName, instituicao);
            lista.add(f);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}



      
    public static List<Formacao> leTodos1(int id1, int id2, int id3) {
    List<Formacao> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT * FROM Formacao WHERE Id IN (?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id1);
        st.setInt(2, id2);
        st.setInt(3, id3);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Id");
            String curso = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            Formacao f = new Formacao(id, curso, instituicao);
            lista.add(f);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}


    
  public static List<Formacao> leTodos2(int idMin, int idMax) {
    List<Formacao> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT * FROM Formacao WHERE Id NOT BETWEEN ? AND ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idMin);
        st.setInt(2, idMax);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("Id");
            String curso = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            Formacao f = new Formacao(id, curso, instituicao);
            lista.add(f);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}

 
   public static Formacao leUm(int id) {
    Formacao formacao = null;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT Id, Curso, Instituicao FROM Formacao WHERE Id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String curso = rs.getString("Curso");
            String instituicao = rs.getString("Instituicao");
            formacao = new Formacao(id, curso, instituicao);
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return formacao;
}


    
    public static int altera(int id, String novoCurso) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Formacao SET curso = ? WHERE Id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novoCurso);
        st.setInt(2, id);
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


   public static int altera2(String novaInstituicao, String curso1, String curso2) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Formacao SET Instituicao = ? WHERE Curso NOT IN (?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novaInstituicao);
        st.setString(2, curso1);
        st.setString(3, curso2);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}




    public static int exclui(int id) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "DELETE FROM Formacao WHERE id = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}
}

