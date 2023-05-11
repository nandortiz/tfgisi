package entities;

public class CambioGrado extends Cambio{

    private String grado;

    public CambioGrado(){
        super();

    }

    public CambioGrado(String grado) {
        super();
        this.grado = grado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }


}
