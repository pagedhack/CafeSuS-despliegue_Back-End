package mx.uv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
