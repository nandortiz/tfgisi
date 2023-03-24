package entities;

import java.util.ArrayList;

public class Sala extends RecursoWeb {

    private String descripcion ="";//TODO descripcionSala? y en puesto descripcionPuesto? //TODO estaba a public
    private String tipo ="";
    private int aforo;
    // private String descripcionElementoReservable; //TODO Esto al padre?
   // private int bibliotecaID; //TODO Esto al padre?
    private ArrayList<SalaShort> salas = new ArrayList<>();

    public Sala () {
        super();
    }

    public Sala(String tipoElementoReservable, String descripcionElementoReservable, int aforoSala) { //TODO bibliotecaID borrado
      //  this.id = nombreBiblioteca;
        this.descripcion = descripcionElementoReservable;
       // this.getBibliotecaID()  = aperturaBiblioteca;
        this.tipo = tipoElementoReservable;
        this.aforo = aforoSala;
}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
        return "/bibliotecas/:id/salas" + this.id;
    } //TODO solo /salas/ ???

    public void setId ( int id){
        this.id = id;
    }
    public int getId () {
        return this.id;
    }


    public void setBibliotecaID(int bibliotecaID) { //TODO seguro??? añadido desde SalaBD getSala

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

