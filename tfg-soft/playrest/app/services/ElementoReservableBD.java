package services;

import entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public ElementoReservable addElementoReservable(ElementoReservable elementoReservable, int bibliotecaID) throws SQLException, ClassNotFoundException {
        Connection cn = connect();
        int identificador = -1;
        String url = ""; //TODO no se usa, borrar?
        if (conector() == true) {
            try {

                String descripcion = elementoReservable.getDescripcion();
                TipoElementoReservable tipo = elementoReservable.getTipo();

                //Agregar los nuevos atributos necesarios para crear los diferentes tipos de elementoReservable
                int aforoSala = -1;
                String infoPuesto = "";

                if (elementoReservable instanceof Sala) {
                    aforoSala = ((Sala) elementoReservable).getAforo();
                } else if (elementoReservable instanceof Puesto) {
                    infoPuesto = ((Puesto) elementoReservable).getInfo();
                }
                //ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
                //  disponibilidad = biblioteca.getListaDisponibilidadBiblioteca();
                Statement st = cn.createStatement();
                System.out.println("INSERT INTO elementoReservable (descripcion, tipo, bibliotecaID, aforoSala, infoPuesto) VALUES ('"+descripcion+"','"+tipo+"','"+bibliotecaID+"', '"+aforoSala+"', '"+infoPuesto+"');");
                st.executeUpdate("INSERT INTO elementoReservable (descripcion, tipo, bibliotecaID, aforoSala, infoPuesto) VALUES ('"+descripcion+"','"+tipo+"','"+bibliotecaID+"', '"+aforoSala+"','"+infoPuesto+"');", Statement.RETURN_GENERATED_KEYS);

                // A la nueva entidad hay que "establecerla la URL"
                ResultSet keys = st.getGeneratedKeys();
                keys.next();
                identificador = keys.getInt(1);

                String patronURL="/biblioteca/"+bibliotecaID+"/elementosReservables/";
                String urlNuevoElementoReservable=patronURL+identificador;

                //UPDATE de la sala con id = id y actualizar la url con urlNuevaSala
                st.executeUpdate("UPDATE elementoReservable set url='"+urlNuevoElementoReservable+"' where id= "+identificador+";");

                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - addElementoReservable");
                    Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //return biblioteca;
        return getElementoReservable(identificador);
        //return url;
    }

    public ElementoReservable update(ElementoReservable elementoReservable, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector()) {
                if (elementoReservable instanceof Sala) {
                    Sala sala = (Sala) elementoReservable;
                    int aforo = sala.getAforo();
                    createStatement.executeUpdate("update elementoReservable set aforoSala = '"+aforo+"' where id = '"+id+"' ;");
                } else if (elementoReservable instanceof Puesto) {
                    Puesto puesto = (Puesto) elementoReservable;
                    String info = puesto.getInfo();
                    createStatement.executeUpdate("update elementoReservable set infoPuesto = '"+info+"' where id = '"+id+"' ;");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (elementoReservable instanceof Sala) {
            return (Sala) ElementoReservableBD.getInstance().getElementoReservable(id);
        } else if (elementoReservable instanceof Puesto) {
            return (Puesto) ElementoReservableBD.getInstance().getElementoReservable(id);
        } else {
            return null;
        }

    }


//TODO necesita id, no hace falta diferenciar entre puesto y sala
public boolean deleteElementoReservable(int id) throws SQLException, ClassNotFoundException {
    boolean valor= false;
    try {
        if (conector() == true) {

            String queryBD = "delete from elementoReservable where id = '"+id+"';";

            try {
                createStatement.executeUpdate(queryBD);
                valor = true;
                return valor;
            } catch (SQLException ex) {
                Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {

                con.close();
            } catch (SQLException ex) {
                System.out.println("Error acceso base de datos - deleteElementoReservable");
                Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
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
    //TODO necesita id, no hace falta diferenciar entre puesto y sala
    public ElementoReservable getElementoReservable(int id) {
        ElementoReservable elementoReservable = new ElementoReservable();
        try {
            if (conector() == true) {
                String queryBD = "select id, descripcion, tipo, bibliotecaID, aforoSala, infoPuesto from elementoReservable where id='"+id+"';";
                try {

                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        TipoElementoReservable tipo = TipoElementoReservable.valueOf(rS.getString("tipo"));
                        if (tipo.equals(TipoElementoReservable.S)) {
                            Sala sala = new Sala();
                            sala.setAforo(rS.getInt("aforoSala"));
                            elementoReservable = sala;
                        } else if (tipo.equals(TipoElementoReservable.P)) {
                            Puesto puesto = new Puesto();
                            puesto.setInfo(rS.getString("infoPuesto"));
                            elementoReservable = puesto;
                        }

                        elementoReservable.setId(rS.getInt("id")); //TODO PENSAR EL CAMBIO
                        elementoReservable.setDescripcion(rS.getString("descripcion"));
                        elementoReservable.setBibliotecaID(rS.getInt("bibliotecaID"));
                        elementoReservable.setTipo(tipo);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getElementoReservable");
                    ex.printStackTrace();
                    Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (elementoReservable.getId()==-1){
            return null;
        }
        else {
            return elementoReservable;
        }

    }




    //TODO necesita id, no hace falta diferenciar entre puesto y sala
    public Collection<ElementoReservableShort> getAllElementosReservables(int bibliotecaID, TipoElementoReservable tipo) {
        List<ElementoReservableShort> elementosReservables = new ArrayList();

        try {
            if (conector() == true) {
                String queryBD = "select id, url from elementoReservable where bibliotecaID = '"+bibliotecaID+"' and tipo= '"+tipo+"';";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        //Cada vuelta while es un línea del resultado de la consulta -> Biblioteca
                        ElementoReservableShort elementoReservable = new ElementoReservableShort();
                        elementoReservable.setId(rS.getInt("id"));
                        elementoReservable.setUrl(rS.getString("url"));

                        elementosReservables.add(elementoReservable);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getAllElementosReservables");
                    ex.printStackTrace();
                    Logger.getLogger(ElementoReservableBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Se usa ElementoReservableBD - getAllElementosReservables");
        return elementosReservables;
    }




}



