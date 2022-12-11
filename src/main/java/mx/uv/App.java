package mx.uv;

import static spark.Spark.*;
import java.util.UUID;

import com.google.gson.*;
import com.google.gson.JsonObject;

public class App 
{
    
    public static Gson gson = new Gson();
    
    /**
     * @param args
     */

    public static void main( String[] args )
    {
        port(getHerokuAssignedPort());
        //  CORS
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));
        before((req, res) -> res.type("application/Json"));


        get("/usuario", (req, res)-> gson.toJson(DAO.listaUsuarios()));

        
        post("/registro", (req, res) -> {
            String registro = req.body();
            String id = UUID.randomUUID().toString();   
            Usuarios u = gson.fromJson(registro, Usuarios.class);
            u.setId(id);
            return DAO.registroUsuario(u);
        });

        post("/producto-log", (req, res) -> {
            String producto = req.body();
            System.out.println(producto);

            // Producto p = gson.fromJson(producto, Producto.class);
            // return DAOP.registroProducto(p);
            return true;
        });

        post("/reservacion-log", (req, res) -> {
            String reservacion = req.body();
            String id = UUID.randomUUID().toString();   
            Reservacion r = gson.fromJson(reservacion, Reservacion.class);
            r.setId(id);
            return DAOR.registroReservacion(r);
        });

        post("/login", (req, res)->{
            String login = req.body();
            Usuarios u = gson.fromJson(login, Usuarios.class);
            // devolver una respuesta JSON
            JsonObject objetoJson = new JsonObject();
            for (Usuarios xUsuario : DAO.listaUsuarios()) {
                if (xUsuario.getUsuario().equals(u.getUsuario())) {
                    if (xUsuario.getPassword().equals(u.getPassword())) {
                        objetoJson.addProperty("status", true);
                        return objetoJson;
                    }
                }
            }
            objetoJson.addProperty("status", false);
            return objetoJson;
        });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 80;
    }
    
}