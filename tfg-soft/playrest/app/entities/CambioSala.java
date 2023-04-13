package entities;

//TODO Hacer un CambioPuesto con la descripcion, pero acabará siendo un CambioElementoReservable
public class CambioSala {

    private String descripcion;

    public CambioSala(){
        super();

    }

    public CambioSala(String descripcion ){
        super();
        this.descripcion=descripcion;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
