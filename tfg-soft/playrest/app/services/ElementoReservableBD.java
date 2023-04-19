package services;

import entities.*;
import scala.xml.Elem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
*/
    public Cambio modifyElementoReservable(Cambio cam, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector()) {

                if (cam instanceof CambioDescripcion) {
                    CambioDescripcion cer = (CambioDescripcion) cam;
                    String descripcion = cer.getDescripcion();
                    createStatement.executeUpdate("update elementoreservable set descripcion = '" + descripcion + "' where id = '" + id + "';");

                }
                if (cam instanceof CambioInfoPuesto){
                 CambioInfoPuesto cip = (CambioInfoPuesto) cam;
                 String infoPuesto = cip.getInfoPuesto();
                 createStatement.executeUpdate("update elementoreservable set infoPuesto = '"+ infoPuesto+"' where id = '"+id+"';");

                 }
                if (cam instanceof CambioAforoSala){
                    CambioAforoSala cas = (CambioAforoSala) cam;
                    int aforoSala = cas.getAforoSala();
                    createStatement.executeUpdate("update elementoreservable set aforoSala = '"+ aforoSala+"' where id = '"+id+"';");

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cam;
    }


    //TODO ------------------------------------------------------------------------------------

    //añadir create, delete, get, getall, update con los instances del modify


    public boolean deleteElementoReservable(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {
                if(id instanceof Sala){
                    String queryBD = "delete from elementoReservable where id = '"+id+"';";

                    try {
                        createStatement.executeUpdate(queryBD);
                        valor = true;
                        return valor;
                    } catch (SQLException ex) {
                        Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {

                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Error acceso base de datos - deleteSala");
                        Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (id instanceof Puesto){
                    String queryBD = "delete from elementoReservable where id = '"+id+"';";

                    try {
                        createStatement.executeUpdate(queryBD);
                        valor = true;
                        return valor;
                    } catch (SQLException ex) {
                        Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {

                        con.close();
                    } catch (SQLException ex) {
                        System.out.println("Error acceso base de datos - deleteSala");
                        Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }




}



