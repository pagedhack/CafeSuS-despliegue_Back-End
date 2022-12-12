package mx.uv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static ProcessBuilder processBuilder = new ProcessBuilder();
    
    private static String Driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://db4free.net:3306/tablaparausuario";
    private static String user= processBuilder.environment().get("USERDB");
    private static String password= processBuilder.environment().get("PASSDB");

    public Connection con;

    public Connection getConnection() {
        try {
            Class.forName(Driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            //System.out.println(e);
        } catch (ClassNotFoundException e){
            //System.out.println("no se encontro");
        }
    return con;
    }
}
