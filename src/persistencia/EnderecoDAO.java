package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Endereco;

public class EnderecoDAO {

    // Método para gravar um endereço
    public static int grava(int cep, String rua) {
        int ret = 0;
        try {
            Connection con = GerenteDeConexao.getConnection();
            String sql = "INSERT INTO Endereco (cep, rua) VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cep);
            st.setString(2, rua);
            ret = st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return ret;
    }

    // Método para ler todos os endereços
    public static List<Endereco> leTodos(String Rua) {
    List<Endereco> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT * FROM Endereco WHERE rua LIKE ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, "%"  + "%");  // LIKE com % para buscar parte da rua
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");
            lista.add(new Endereco(rua, cep));
        }
        st.close();
    } catch (Exception e) {
        System.out.println("Erro leTodosLike: " + e.getMessage());
    }
    return lista;
}

      
    public static List<Endereco> leTodos1(int cep1, int cep2, int cep3) {
    List<Endereco> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT * FROM Endereco WHERE cep IN (?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);

        st.setInt(1, cep1);
        st.setInt(2, cep2);
        st.setInt(3, cep3);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");
            lista.add(new Endereco(rua, cep));
        }
        st.close();
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
    return lista;
}
    
   public static List<Endereco> leTodos2(int cepMin, int cepMax) {
    List<Endereco> lista = new ArrayList<>();
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "SELECT * FROM Endereco WHERE cep NOT BETWEEN ? AND ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, cepMin);
        st.setInt(2, cepMax);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String rua = rs.getString("rua");
            int cep = rs.getInt("cep");
            lista.add(new Endereco(rua, cep));
        }
        rs.close();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return lista;
}



    // Método para ler um endereço pelo cep
    public static Endereco leUm(int cep) {
        Endereco e = null;
        try {
            Connection con = GerenteDeConexao.getConnection();
            String sql = "SELECT * FROM Endereco WHERE cep = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cep);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String rua = rs.getString("rua");
                e = new Endereco(rua, cep);
            }
            st.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

    // Método para alterar a rua de um cep
    public static int altera(int cep, String novaRua) {
        int ret = 0;
        try {
            Connection con = GerenteDeConexao.getConnection();
            String sql = "UPDATE Endereco SET rua = ? WHERE cep = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, novaRua);
            st.setInt(2, cep);
            ret = st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return ret;
    }
    public static int altera1(int cep, String ruaAntiga, String novaRua) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Endereco SET rua = ? WHERE cep = ? AND rua = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novaRua);
        st.setInt(2, cep);
        st.setString(3, ruaAntiga);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}
   public static int altera2(int cep1, int cep2, String novaRua) {
    int ret = 0;
    try {
        Connection con = GerenteDeConexao.getConnection();
        String sql = "UPDATE Endereco SET rua = ? WHERE cep NOT IN (?, ?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, novaRua);
        st.setInt(2, cep1);
        st.setInt(3, cep2);
        ret = st.executeUpdate();
        st.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return ret;
}


    public static int exclui(int cep) {
        int ret = 0;
        try {
            Connection con = GerenteDeConexao.getConnection();
            String sql = "DELETE FROM Endereco WHERE cep = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cep);
            ret = st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
        return ret;
    }
    

}
