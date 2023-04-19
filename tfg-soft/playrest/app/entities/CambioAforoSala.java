package entities;

//TODO Hacer un CambioPuesto con la descripcion, pero acabará siendo un CambioElementoReservable
public class CambioAforoSala extends Cambio { //cambiar nombre clase a CambioAforoSala y CambioInfoPuesto
                                                //TODO DONE, clases cambiadas y variable local de SalaController y PuestoController también
    private int aforoSala;

    public int getAforoSala() {
        return aforoSala;
    }

    public void setAforoSala(int aforoSala) {
        this.aforoSala = aforoSala;
    }
}



