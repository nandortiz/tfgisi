package entities;

import services.UsuarioBD;

public class UsuarioShort extends  RecursoWeb{

    private String nombreUsuario;
    private String apellidosUsuario;
    private String gradoUsuario;

    public UsuarioShort(){
        super();
    }

    public UsuarioShort(int id, String url, String nombre, String apellido, String grado) {
        super(id, url);
        this.nombreUsuario=nombre;
        this.apellidosUsuario =apellido;
        this.gradoUsuario =grado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getGradoUsuario() {
        return gradoUsuario;
    }

    public void setGradoUsuario(String gradoUsuario) {
        this.gradoUsuario = gradoUsuario;
    }

}
