package mx.uv;

public class Usuarios {
    
    String id;
    String usuario;
    String password;
    String nombre;
    String correo;

    public Usuarios(String usuario, String password){
        this.usuario = usuario;
        this.password = password;
    }

    public Usuarios(String id, String usuario, String password, String nombre, String correo){
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
}
