package mx.uv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOR {
    public static Conexion con = new Conexion();

    public static String registroReservacion(Reservacion r) {
        PreparedStatement stm = null;
        Connection c = null;
        String msj = null;

        c = con.getConnection();
        try {
            String sql = "Insert into reservacion (id, fecha, hora, personas, nombre, correo, telefono) values (?,?,?,?,?,?,?)";
            stm = (PreparedStatement) c.prepareStatement(sql);
            stm.setString(1, r.getId());
            stm.setString(2, r.getFecha());
            stm.setString(3, r.getHora());
            stm.setString(4, r.getPersona());
            stm.setString(5, r.getNombre());
            stm.setString(6, r.getCorreo());
            stm.setString(7, r.getTelefono());

            if (stm.executeUpdate() > 0)
                msj = "reservacion Agregado";
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
