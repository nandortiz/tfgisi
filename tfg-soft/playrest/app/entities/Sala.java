package entities;

import java.util.ArrayList;

public class Sala extends ElementoReservable {


    private int aforo;

    private ArrayList<SalaShort> salas = new ArrayList<>();

    public Sala (String descripcion, TipoElementoReservable tipoElementoReservable, int bibliotecaID, int aforoSala) {
        super(descripcion, tipoElementoReservable, bibliotecaID);
        this.aforo = aforoSala;
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
                "' ,aforoSala ='" + aforo +
                "', descripcion ='" + getDescripcion() + '\'' + //TODO getters en vez de variables descripcion, tipo,...
                "', tipo ='" + getTipo() + //TODO idem
                "', bibliotecaID='" + getBibliotecaID() + //TODO idem
                ", url = '" + getUrl() +
                '}';
    }

    public String getUrl () {
        return "/bibliotecas/"+getBibliotecaID()+"/salas/" + this.id; } //TODO get en vez de bibliotecaID

    public void setId ( int id){
        this.id = id;
    }
    public int getId () {
        return this.id;
    }


    public void setBibliotecaID(int bibliotecaID) {
        this.setBibliotecaID(bibliotecaID);
    } //TODO this.setBibliotecaID cambiado de biblioteca.ID
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

