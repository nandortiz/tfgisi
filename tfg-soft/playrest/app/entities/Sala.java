package entities;

import java.util.ArrayList;

public class Sala extends ElementoReservable {

    private int aforo;

    public Sala(String descripcion, int aforoSala, int bibliotecaID) {
    super (descripcion, TipoElementoReservable.S, bibliotecaID);
    this.aforo=aforoSala;

    }

    public Sala() {
        this.tipo=TipoElementoReservable.S;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }


    @Override
    public String toString () {
        return "Sala{" +
                "id = '" + id +
                "' ,aforoSala ='" + aforo +
                "', descripcion ='" + getDescripcion() + '\'' +
                "', tipo ='" + tipo +
                "', bibliotecaID='" + getBibliotecaID() +
                ", url = '" + getUrl() +
                '}';
    }

    public String getUrl () {
        return "/bibliotecas/" + getBibliotecaID() + "/salas/" + this.id;
    }

}


