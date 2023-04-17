package entities;


public class CambioElementoReservable extends Cambio { //TODO CambioDescripcion
    private String descripcion;

    public CambioElementoReservable(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

