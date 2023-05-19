package entities;

import services.ElementoReservableBD;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalTime; //TODO disponibilidad
import java.util.Comparator;
import java.util.List;


public class ElementoReservable extends RecursoWeb{ //TODO public abstract class? disponibilidad
  //  private DisponibilidadElementoReservable disponibilidad; //TODO disponibilidad

    private String descripcion = "";
    protected TipoElementoReservable tipo;
    private int bibliotecaID;
    private List <LocalTime> horariosDisponibles = new ArrayList<>();


    //horas de apertura y cierre 01/01/2021-10:00 ?¿?¿?¿?¿?¿
    // private ArrayList<Puesto> listaPuesto = new ArrayList<>();
    //  private ArrayList<String> listaPuesto = new ArrayList<>();

    private ArrayList<SalaShort> salas = new ArrayList<>();

    public ElementoReservable() {
       super();
    }

    public ElementoReservable(String descripcionElementoReservable, TipoElementoReservable tipoElementoReservable, int bibliotecaID) {
        this.descripcion = descripcionElementoReservable;
        this.tipo = tipoElementoReservable;
        this.bibliotecaID = bibliotecaID;
    }

    //TODO cambiar en workbench s y p por S y P
    //TODO cambiar nulls por exceptions
//_____________________________________________
    //TODO buscar error ' ' sql PUT SalaBD
    //TODO delete y post salas y puestos

    //:______________________________
    //TODO Después de hacer put y patch de puestobd y salabd, conseguir que sólo haya EltoResBD, y borrar salaBd y puestoBD
    //TODO ...para ello añadir los instances que están en el modify en todos create, modufy, delete,...

    //____________________________________________
    //TODO                  DUDAS:
    //TODO              patch y put sala 200 pero en get no se actualiza;
    //TODO              si hago get idbib13 e idsa 24 debería dar error porque esa sala está en bib1 y no da error


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

    //TODO mirar si se construyen las salas con la S y los puestos con la P
    public int getBibliotecaID() {
        return bibliotecaID;
    }

    public void setBibliotecaID (int bibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }

    public ArrayList<SalaShort> getSalas() { return salas; }

    public void setSalas(ArrayList<SalaShort> salas) { this.salas = salas; }

    public void annadirListaSala(SalaShort sala){ salas.add(sala); }

    @Override
    public String toString () { //TODO Date para apertura y cierre?
        return "ElementoReservable{" +
                "id = '" + id +
                "', descripción ='" + descripcion +
                "', tipo ='" + tipo +
                "', bibliotecaID ='" + bibliotecaID +
                ", url = '" + getUrl() +
                ", disponibilidad = '" + getHorariosDisponibles()+ //TODO disponibilidad
                //", horarios disponibles='" + listaDisponibilidadBiblioteca+
                '}';
    }

    public String getUrl () {
        return "/bibliotecas/" + this.id;
    }

    public void setId ( int id){
        this.id = id;
    }

    public int getId () {
        return this.id;
    }

        //TODO disponibiliad



//TODO if contains horario, remove horario. else false
public boolean reservarHorario(LocalTime horario) {
    if (horariosDisponibles.contains(horario)) {
        horariosDisponibles.remove(horario);
        return true;
    } else {
        return false;
    }
}

//TODO ss



    // getters y setters
        public List<LocalTime> getHorariosDisponibles() {
            return horariosDisponibles;
        }

        public void setHorariosDisponibles(List<LocalTime> horariosDisponibles) {
            this.horariosDisponibles = horariosDisponibles;
        }
      public static LocalTime parsearHorario(String horario) { //TODO es todo localtime, no necesario parsear
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(horario, formatter);
    }

    public static void liberarHorario(LocalTime horario) {
        ElementoReservableBD.horariosDisponibles.add(horario);
        ElementoReservableBD.horariosDisponibles.sort(Comparator.naturalOrder());
    }

    public static void agregarHorarioDisponible(LocalTime horario) {
        ElementoReservableBD.horariosDisponibles.add(horario);
    }


}







