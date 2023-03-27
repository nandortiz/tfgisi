package entities;

import java.util.ArrayList;

public class Sala extends ElementoReservable {

    private String descripcion ="";//TODO descripcionSala? y en puesto descripcionPuesto? //TODO estaba a public
    private TipoElementoReservable tipo;
    private int aforo;
    // private String descripcionElementoReservable; //TODO Esto al padre?
    private int bibliotecaID;
    private ArrayList<SalaShort> salas = new ArrayList<>();

    public Sala () {
        super();
    }

    public Sala(int aforoSala) { //TODO bibliotecaID borrado
        this.aforo = aforoSala;
}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoElementoReservable getTipo() {
        return tipo;
    }

    public void setTipo(TipoElementoReservable tipo) {
        this.tipo = tipo;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public ArrayList<SalaShort> getSalas() { return salas; }

    public void setSalas(ArrayList<SalaShort> salas) { this.salas = salas; }

    public void annadirListaSala(SalaShort sala){ salas.add(sala); }

    @Override
    public String toString () {
        return "Sala{" +
                "id = '" + id +
                "', descripcion ='" + descripcion + '\'' +
                "', tipo ='" + tipo +
                ", aforoSala = '" + aforo +
                ", url = '" + getUrl() +
                '}';
    }

    public String getUrl () {
        return "/bibliotecas/"+bibliotecaID+"/salas/" + this.id;
    }

    public void setId ( int id){
        this.id = id;
    }
    public int getId () {
        return this.id;
    }


    public void setBibliotecaID(int bibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }
}

  //  public String getUrl() {
    //return "/salas/" + this.getId();
    //}

    /*public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
*/
/*    public String getDescripcionElementoReservable() {
        return descripcionElementoReservable;
    }

    public void setDescripcionElementoReservable(String descripcionElementoReservable) {
        this.descripcionElementoReservable = descripcionElementoReservable;
    }
*/
/*    public int getBibliotecaID() {
        return 4 ; //TODO bibliotecaID
    }

    public void setBibliotecaID(int bibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }
}
*/

