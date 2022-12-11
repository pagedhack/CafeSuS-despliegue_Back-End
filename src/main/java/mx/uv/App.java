package mx.uv;

import static spark.Spark.*;
import java.util.UUID;

import com.google.gson.*;

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

        //get("/usuario", (req, res)-> gson.toJson(DAO.listaUsuarios()));

        post("/registro", (req, res) -> {
            String registro = req.body();
            String id = UUID.randomUUID().toString();   
            Usuarios u = gson.fromJson(registro, Usuarios.class);
            u.setId(id);
            return DAO.registroUsuario(u);
        });

        post("/login", (req, res)->{
            Boolean log = false;
            String login = req.body();
            Usuarios u = gson.fromJson(login, Usuarios.class);
            // devolver una respuesta JSON
            JsonObject objetoJson = new JsonObject();

            for (Usuarios xUsuario : DAO.listaUsuarios()) {
                if (xUsuario.getUsuario().equals(u.getUsuario())) {
                    if (xUsuario.getPassword().equals(u.getPassword())) {
                        
                        res.status(200);// Codigo de respuesta
                        // log = true;
                        // return log;
                        objetoJson.addProperty("status", true);
                        objetoJson.addProperty("usuario", gson.toJson(xUsuario));
                        return DAO.listaUsuarios();
                    }
                }
            }
            objetoJson.addProperty("status", false);
            return objetoJson;
            //return log;
        });


        post("/registroProducto", (req, res) -> {
            String producto = req.body();
            String id = UUID.randomUUID().toString();   
            Producto p = gson.fromJson(producto, Producto.class);
            p.setId(id);
            return DAOP.registroProducto(p);
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