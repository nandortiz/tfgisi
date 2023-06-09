package entities;


public class Usuario extends RecursoWeb{


    private String nombre = "";
    private String apellidos = "";
    private String grado = "";

    public Usuario() {
        super();
    }

    public Usuario(String nombreUsuario, String apellidosUsuario, String gradoUsuario) {
        this.nombre = nombreUsuario;
        this.apellidos = apellidosUsuario;
        this.grado  = gradoUsuario;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }



    @Override
    public String toString () {
        return "Usuario{" +
                "id = '" + id +
                "', nombre ='" + nombre + '\'' +
                "', apellidos ='" + apellidos +
                "', grado ='" + grado +
                ", url = '" + getUrl() +
                '}';
    }

    public String getUrl () {
        return "/usuarios/" + this.id;
    }

    public void setId ( int id){
        this.id = id;
    }
    public Integer getId () {
        return this.id;
    }


}
