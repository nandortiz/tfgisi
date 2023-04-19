package entities;


public class CambioDescripcion extends Cambio { //TODO DONE, CambioElementoReservable cambiado a CambioDescripcion, así como variable cambioElementoReservable de EltoREsController
    private String descripcion;

    public CambioDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

