package mx.uv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOP {
    public static Conexion con = new Conexion();

    public static String registroProducto(Producto p) {
        PreparedStatement stm = null;
        Connection c = null;
        String msj = null;

        c = con.getConnection();
        try {
            String sql = "Insert into Productos (id, nombre, cantidad, precio) values (?,?,?,?)";
            stm = (PreparedStatement) c.prepareStatement(sql);
            stm.setString(1, p.getId());
            stm.setString(2, p.getNombre());
            stm.setString(3, p.getCantidad());
            stm.setString(4, p.getPrecio());

            if (stm.executeUpdate() > 0)
                msj = "producto Agregado";
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

    public static List<Producto> listaProductos() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Producto> resultado = new ArrayList<>();

        conn = con.getConnection();

        try {
            String sql = "SELECT * from Productos";
            stm = (Statement) conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Producto p = new Producto(rs.getString("id"), rs.getString("nombre"), rs.getString("cantidad"), rs.getString("precio"));
                resultado.add(p);
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
