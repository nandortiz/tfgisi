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

public class RecursoExtraBD extends ConexionBD {

    private static services.RecursoExtraBD instance;

    public static services.RecursoExtraBD getInstance() {
        if (instance == null) {
            instance = new services.RecursoExtraBD();
        }
        return instance;
    }

    public RecursoExtra addRecursoExtra(RecursoExtra recursoExtra, int bibliotecaID) throws SQLException, ClassNotFoundException {
        Connection cn = connect();
        int identificador = -1;
        String url = ""; //TODO no se usa, borrar?
        String query ="";
        if (conector() == true) {
            try {
                String nombre = recursoExtra.getNombre();
                String descripcion = recursoExtra.getDescripcion();
                TipoRecursoExtra tipo = recursoExtra.getTipo();

                //Agregar los nuevos atributos necesarios para crear los diferentes tipos de recursoExtra
                String numSerieOrdenador = "";
                int isbnLibro = -1;
                String tipoRecursoExtra = "";

                if (recursoExtra instanceof Ordenador) {
                    numSerieOrdenador = ((Ordenador) recursoExtra).getNumSerie();
                    tipoRecursoExtra = "ordenadores";
                    query = "INSERT INTO recursoExtra (nombre, descripcion, tipo, bibliotecaID, numSerie) VALUES ('"+nombre+"', '"+descripcion+"','"+tipo+"','"+bibliotecaID+"', '"+numSerieOrdenador+"');";
                } else if (recursoExtra instanceof Libro) {
                    isbnLibro = ((Libro) recursoExtra).getIsbn();
                    tipoRecursoExtra = "libros";
                    query = "INSERT INTO recursoExtra (nombre, descripcion, tipo, bibliotecaID, ISBN) VALUES ('"+nombre+"', '"+descripcion+"','"+tipo+"','"+bibliotecaID+"','"+isbnLibro+"');";
                }
                //ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
                //  disponibilidad = biblioteca.getListaDisponibilidadBiblioteca();
                Statement st = cn.createStatement();
                System.out.println(query);
                st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

                // A la nueva entidad hay que "establecerla la URL"
                ResultSet keys = st.getGeneratedKeys();
                keys.next();
                identificador = keys.getInt(1);

                String patronURL="/biblioteca/"+bibliotecaID+"/"+tipoRecursoExtra+"/";
                String urlNuevoRecursoExtra=patronURL+identificador;

                //UPDATE del ordenador con id = id y actualizar la url con urlNuevoOrdenador
                st.executeUpdate("UPDATE recursoExtra set url='"+urlNuevoRecursoExtra+"' where id= "+identificador+";");

                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - addRecursoExtra");
                    Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //return biblioteca;
        return getRecursoExtra(identificador);
        //return url;
    }

    //TODO necesita id, no hace falta diferenciar entre puesto y sala
    public RecursoExtra getRecursoExtra(int id) {
        RecursoExtra recursoExtra = new RecursoExtra();
        try {
            if (conector() == true) {
                String queryBD = "select id, nombre, descripcion, tipo, bibliotecaID, numSerie, ISBN from recursoExtra where id='"+id+"';";
                try {

                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        TipoRecursoExtra tipo = TipoRecursoExtra.valueOf(rS.getString("tipo"));
                        if (tipo.equals(TipoRecursoExtra.O)) {
                            Ordenador ordenador = new Ordenador();
                            ordenador.setNumSerie(rS.getInt("numSerie"));
                            recursoExtra = ordenador;
                        } else if (tipo.equals(TipoRecursoExtra.L)) {
                            Libro libro = new Libro();
                            libro.setIsbn(rS.getString("ISBN"));
                            recursoExtra = libro;
                        }

                        recursoExtra.setId(rS.getInt("id")); //TODO PENSAR EL CAMBIO
                        recursoExtra.setNombre(rS.getString("nombre"));
                        recursoExtra.setDescripcion(rS.getString("descripcion"));
                        recursoExtra.setBibliotecaID(rS.getInt("bibliotecaID"));
                        recursoExtra.setTipo(tipo);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getRecursoExtra");
                    ex.printStackTrace();
                    Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (recursoExtra.getId()==-1){
            return null;
        }
        else {
            return recursoExtra;
        }

    }

}
