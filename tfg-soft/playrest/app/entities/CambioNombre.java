package entities;

public class CambioNombre extends Cambio{
    private String nombre;

    public CambioNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
