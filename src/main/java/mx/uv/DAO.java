package mx.uv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    public static Conexion con = new Conexion();

    public static String registroUsuario(Usuarios u) {
        PreparedStatement stm = null;
        Connection c = null;
        String msj = null;

        c = con.getConnection();
        try {
            String sql = "Insert into clientes (id, usuario, password, nombre, mail) values (?,?,?,?,?)";
            stm = (PreparedStatement) c.prepareStatement(sql);
            stm.setString(1, u.getId());
            stm.setString(2, u.getUsuario());
            stm.setString(3, u.getPassword());
            stm.setString(4, u.getNombre());
            stm.setString(5, u.getCorreo());

            if (stm.executeUpdate() > 0)
                msj = "usuario Agregado";
            else
                msj = "no agregado";
        } catch (Exception e) {
            System.out.print(e);
        } finally{
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                stm = null;
            }
            try {
                c.close();
                System.out.println("Conexion Cerrada!");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return msj;
    }

    public static List<Usuarios> listaUsuarios() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Usuarios> resultado = new ArrayList<>();

        conn = con.getConnection();

        try {
            String sql = "SELECT * from clientes";
            stm = (Statement) conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Usuarios u = new Usuarios(rs.getString("usuario"), rs.getString("password"));
                resultado.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null)     
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            rs = null;
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return resultado;
    }
    
}
