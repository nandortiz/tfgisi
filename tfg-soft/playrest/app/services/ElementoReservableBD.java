/*package services;

import services.ConexionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static consistency.ConexionJDBC.connect;

public class ElementoReservableBD  extends ConexionBD {
    private static services.ElementoReservableBD instance;

    public static services.ElementoReservableBD getInstance() {
        if (instance == null) {
            instance = new services.ElementoReservableBD();
        }
        return instance;
    }
*/
/*
public ElementoReservable addElementoReservable(ElementoReservable elementoReservable) throws SQLException, ClassNotFoundException {
        Connection cn = connect();
        int identificador = -1;
        String url = "";
        if (conector() == true) {
            try {

                String nombre = biblioteca.getNombre();
                String descripcion = biblioteca.getDescripcion();
                LocalDateTime apertura = biblioteca.getApertura();
                LocalDateTime cierre = biblioteca.getCierre();

                //ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
                //  disponibilidad = biblioteca.getListaDisponibilidadBiblioteca();
                Statement st = cn.createStatement();
                st.executeUpdate("INSERT INTO biblioteca (nombre,descripcion, apertura, cierre) VALUES ('" + nombre + "', '" + descripcion + "', '" + apertura + "', '" + cierre + "')", Statement.RETURN_GENERATED_KEYS);

                // A la nueva entidad hay que "establecerla la URL"
                ResultSet keys = st.getGeneratedKeys();
                keys.next();
                identificador = keys.getInt(1);

                String patronURL="/bibliotecas/";
                String urlNuevaBiblioteca=patronURL+identificador;

                //UPDATE de la biblioteca con id = id y actualizar la url con urlNuevaBiblioteca
                st.executeUpdate("UPDATE biblioteca set url = '"+urlNuevaBiblioteca+"' where id= '"+identificador+"'");

                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - addBiblioteca");
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //return biblioteca;
        return getBiblioteca(identificador);
        //return url;
    }
}
*/


