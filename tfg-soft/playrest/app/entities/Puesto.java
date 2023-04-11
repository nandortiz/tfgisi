package entities;

import java.util.ArrayList;

public class Puesto extends ElementoReservable {

    private String info;

    private ArrayList<PuestoShort> puestos = new ArrayList<>();
    private String descripcion;

    public Puesto(String descripcion, String infoPuesto, int bibliotecaID) {
        super (descripcion, TipoElementoReservable.P , bibliotecaID);
        this.info=infoPuesto;
    }

    public Puesto() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<PuestoShort> getPuestos() { return puestos; }

    public void setPuestos(ArrayList<PuestoShort> puestos) { this.puestos = puestos; }

    public void annadirListaPuesto(PuestoShort puesto){ puestos.add(puesto); }

    @Override
    public String toString () {
        return "Puesto{" +
                "id = '" + id +
                ", infoPuesto = '" + info +
                "', descripcion ='" + descripcion + '\'' + //TODO getter en vez de descripcion
                "', tipo ='" + getTipo() + //TODO idem
                "', bibliotecaID ='" + getBibliotecaID() +
                ", url = '" + getUrl() +
                '}';
    }

    public String getUrl () {
        return "/bibliotecas/"+ getBibliotecaID() +"/puestos/" + this.id;
    } //TODO idem

    public void setId ( int id){
        this.id = id;
    }
    public int getId () {
        return this.id;
    }


    public void setBibliotecaID(int bibliotecaID) {
        this.setBibliotecaID(bibliotecaID);
    } //TODO Idem

}