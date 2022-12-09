package mx.uv;

import static spark.Spark.*;

import java.util.List;
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
        //port(80);
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

        post("/reg", (req, res)->{
            String registro = req.body();
            String id = UUID.randomUUID().toString();   
            Usuarios u = gson.fromJson(registro, Usuarios.class);
            u.setId(id);
            return DAO.registroUsuario(u);
        });


        // post("/login", (req, res)->{
        //     String login = req.body();
        //     Usuarios u = gson.fromJson(login, Usuarios.class);
            
        //     //json
        //     //JsonObject objetoJson = new JsonObject();
        //     return DAO.listaUsuarios();
        //     //return objetoJson;
        // });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 80;
    }
    
}
