package mx.uv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String Driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://db4free.net:3306/tablaparausuario";
    private String user="samuelolmedo";
    private String password="administrador";

    public Connection con;

    public Connection getConnection() {
        try {
            Class.forName(Driver);
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("conexion exitosa");
            //System.out.println(con);
        } catch (SQLException e) {
            //System.out.println(e);
            //System.out.println("ERROR");
        } catch (ClassNotFoundException e){
            //System.out.println("no se encontro");
        }
    return con;
    }
}
