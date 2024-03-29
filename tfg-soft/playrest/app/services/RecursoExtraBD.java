package services;


import com.fasterxml.jackson.databind.JsonNode;
import entities.*;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import utils.ApplicationUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
                    query = "INSERT INTO recursoextra (nombre, descripcion, tipo, bibliotecaID, numSerie) VALUES ('"+nombre+"', '"+descripcion+"','"+tipo+"','"+bibliotecaID+"', '"+numSerieOrdenador+"');";
                } else if (recursoExtra instanceof Libro) {
                    isbnLibro = ((Libro) recursoExtra).getIsbn();
                    tipoRecursoExtra = "libros";
                    query = "INSERT INTO recursoextra (nombre, descripcion, tipo, bibliotecaID, ISBN) VALUES ('"+nombre+"', '"+descripcion+"','"+tipo+"','"+bibliotecaID+"','"+isbnLibro+"');";
                }

                Statement st = cn.createStatement();
                st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

                // A la nueva entidad hay que "establecerla la URL"
                ResultSet keys = st.getGeneratedKeys();
                keys.next();
                identificador = keys.getInt(1);

                String patronURL="/biblioteca/"+bibliotecaID+"/"+tipoRecursoExtra+"/";
                String urlNuevoRecursoExtra=patronURL+identificador;

                //UPDATE del ordenador con id = id y actualizar la url con urlNuevoOrdenador
                st.executeUpdate("UPDATE recursoextra set url='"+urlNuevoRecursoExtra+"' where id= "+identificador+";");

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
        return getRecursoExtra(identificador);
    }

    public RecursoExtra getRecursoExtra(int id) {
        RecursoExtra recursoExtra = new RecursoExtra();
        try {
            if (conector() == true) {
                String queryBD = "select id, nombre, descripcion, tipo, bibliotecaID, numSerie, ISBN from recursoextra where id='"+id+"';";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        TipoRecursoExtra tipo = TipoRecursoExtra.valueOf(rS.getString("tipo"));
                        if (tipo.equals(TipoRecursoExtra.O)) {
                            Ordenador ordenador = new Ordenador();
                            ordenador.setNumSerie(rS.getString("numSerie"));
                            recursoExtra = ordenador;
                        } else if (tipo.equals(TipoRecursoExtra.L)) {
                            Libro libro = new Libro();
                            libro.setIsbn(rS.getInt("ISBN"));
                            recursoExtra = libro;
                        }
                        recursoExtra.setId(rS.getInt("id"));
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


    public Collection<RecursoExtraShort> getAllRecursosExtras(int bibliotecaID, TipoRecursoExtra tipo) {
        List<RecursoExtraShort> recursosExtras = new ArrayList();

        try {
            if (conector() == true) {
                String queryBD = "select id, url from recursoextra where bibliotecaID = '"+bibliotecaID+"' and tipo= '"+tipo+"';";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        //Cada vuelta while es un línea del resultado de la consulta -> Biblioteca
                        RecursoExtraShort recursoExtra = new RecursoExtraShort();

                        recursoExtra.setId(rS.getInt("id"));
                        recursoExtra.setUrl(rS.getString("url"));

                        recursosExtras.add(recursoExtra);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getAllRecursosExtras");
                    ex.printStackTrace();
                    Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Se usa RecursoExtraBD - getAllRecursosExtras");
        return recursosExtras;
    }


    public boolean deleteRecursoExtra(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBD = "delete from recursoextra where id = '"+id+"';";

                try {
                    createStatement.executeUpdate(queryBD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - deleteRecursoExtra");
                    Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    public RecursoExtra update(RecursoExtra recursoExtra, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector()) {
                if (recursoExtra instanceof Ordenador) {
                    Ordenador ordenador = (Ordenador) recursoExtra;
                    String nombre = recursoExtra.getNombre();
                    String numSerie = ordenador.getNumSerie();
                    String descripcion = ordenador.getDescripcion();
                    createStatement.executeUpdate("update recursoextra set nombre = '"+nombre+"', numSerie = '"+numSerie+"', descripcion = '"+descripcion+"' where id = '"+id+"' ;");
                } else if (recursoExtra instanceof Libro) {
                    String nombre = recursoExtra.getNombre();
                    Libro libro = (Libro) recursoExtra;
                    int isbn = libro.getIsbn();
                    String descripcion = libro.getDescripcion();
                    createStatement.executeUpdate("update recursoextra set nombre = '"+nombre+"', ISBN ='"+isbn+"', descripcion = '"+descripcion+"' where id = '"+id+"' ;");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (recursoExtra instanceof Ordenador) {
            return RecursoExtraBD.getInstance().getRecursoExtra(id);
        } else if (recursoExtra instanceof Libro) {
            return RecursoExtraBD.getInstance().getRecursoExtra(id);
        } else {
            return null;
        }

    }

    public Cambio modifyRecursoExtra(Cambio cam, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector()) {
                if (cam instanceof CambioNombre) {
                    CambioNombre cne = (CambioNombre) cam;
                    String nombre = cne.getNombre();
                    createStatement.executeUpdate("update recursoextra set nombre = '" + nombre + "' where id = '" + id + "';");
                }
                if (cam instanceof CambioDescripcion) {
                    CambioDescripcion cer = (CambioDescripcion) cam;
                    String descripcion = cer.getDescripcion();
                    createStatement.executeUpdate("update recursoextra set descripcion = '" + descripcion + "' where id = '" + id + "';");
                }
                if (cam instanceof CambioNumSerieOrdenador){
                    CambioNumSerieOrdenador cip = (CambioNumSerieOrdenador) cam;
                    String numSerieOrdenador = cip.getNumSerieOrdenador();
                    createStatement.executeUpdate("update recursoextra set numSerie = '"+ numSerieOrdenador+"' where id = '"+id+"';");
                }
                if (cam instanceof CambioIsbnLibro){
                    CambioIsbnLibro cas = (CambioIsbnLibro) cam;
                    int isbnLibro = cas.getIsbnLibro();
                    createStatement.executeUpdate("update recursoextra set ISBN = '"+ isbnLibro+"' where id = '"+id+"';");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursoExtraBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cam;
    }

}
