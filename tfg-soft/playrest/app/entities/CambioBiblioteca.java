package entities;

import java.time.LocalDateTime;

public class CambioBiblioteca {

    private CambioBibliotecaTipo tipo;
    private LocalDateTime franja;

    public CambioBiblioteca(){
        super();

    }

    public CambioBiblioteca(CambioBibliotecaTipo tipo, LocalDateTime franja ){
        super();
        this.tipo=tipo;
        this.franja=franja;

    }

    public CambioBibliotecaTipo getTipo() {
        return tipo;
    }

    public void setTipo(CambioBibliotecaTipo tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFranja() {
        return franja;
    }

    public void setFranja(LocalDateTime franja) {
        this.franja = franja;
    }
}