package entities;

import java.util.ArrayList;

public class Puesto extends ElementoReservable {

    private String info;

    public Puesto(String descripcion, String infoPuesto, int bibliotecaID) {
        super(descripcion, TipoElementoReservable.P, bibliotecaID);
        this.info = infoPuesto;

    }

    public Puesto() {
        tipo=TipoElementoReservable.P;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return "Puesto{" +
                "id = '" + id +
                ", infoPuesto = '" + info +
                "', descripcion ='" + getDescripcion() + '\'' +
                "', tipo ='" + tipo +
                "', bibliotecaID ='" + getBibliotecaID() +
                ", url = '" + getUrl() +
                '}';
    }


    public String getUrl() {
        System.out.println(getId());
        return "/bibliotecas/" + getBibliotecaID() + "/puestos/" + this.id;
    }

}