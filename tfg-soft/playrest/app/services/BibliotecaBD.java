package services;


import com.mysql.cj.protocol.Resultset;
import entities.*;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static consistency.ConexionJDBC.connect;

public class BibliotecaBD extends ConexionBD {

    private static BibliotecaBD instance;

    public static BibliotecaBD getInstance() {
        if (instance == null) {
            instance = new BibliotecaBD();
        }
        return instance;
    }


    public Biblioteca addBiblioteca(Biblioteca biblioteca) throws SQLException, ClassNotFoundException {
        Connection cn = connect();
        int identificador = -1;
        String url = "";
        if (conector() == true) {
            try {

                String nombre = biblioteca.getNombreBiblioteca();
                String descripcion = biblioteca.getDescripcionBiblioteca();
                //ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
              //  disponibilidad = biblioteca.getListaDisponibilidadBiblioteca();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("INSERT INTO biblioteca (nombre,descripcion) VALUES ('asdasd','asdasdasd');");

                //createStatement.executeUpdate("INSERT INTO biblioteca (nombre) VALUES ('asdasd');", Statement.RETURN_GENERATED_KEYS);
               /**ResultSet prueba = createStatement.getGeneratedKeys();
                prueba.next();
                identificador = prueba.getInt(1);
                System.out.println("la fila es " + identificador);
                String patron = "/bibliotecas/";**/
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //return biblioteca;
        return getBiblioteca(identificador);
        //return url;
        }


    public Biblioteca getBiblioteca(int id) {
        Biblioteca biblioteca = new Biblioteca();

        try {
            if (conector() == true) {

                String queryBD = "SELECT id,nombre,descripcion FROM biblioteca WHERE id=1;";//+id;

                try {

                    rS = createStatement.executeQuery(queryBD);

                    //Cogemos el primer valor.
                    rS.next();

                    biblioteca.setId(rS.getInt("id"));
                    System.out.println("Cogemos ID");
                    biblioteca.setNombreBiblioteca(rS.getString("nombre"));
                    biblioteca.setDescripcionBiblioteca(rS.getString("descripcion"));

                } catch (SQLException ex) {
                    System.out.println("Falla esto 0");
                    ex.printStackTrace();
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return biblioteca;
    }

/**
    public Collection<BibliotecaShort> getAllBibliotecas() {

        HashMap<Integer, BibliotecaShort> mapa = new HashMap<>();

        try {
            if (conector() == true) {
                String queryBD = "select id, url, nombreBiblioteca, descripcionBiblioteca from biblioteca";
                int i = 0;
                try {
                    rS = createStatement.executeQuery(queryBD);
/*
                    while (rS.next()) {

                        Biblioteca biblioteca= getBiblioteca(Integer.parseInt(rS.getString("id")));
                        mapa.put(lab.getId(), biblioteca);

                    }



                    while (rS.next()) {
                        BibliotecaShort biblioteca;

                        if (mapa.containsKey(Integer.parseInt(rS.getString("id")))) {
                            biblioteca = mapa.get(Integer.parseInt(rS.getString("id")));
                        } else {
                            biblioteca = new BibliotecaShort();
                            biblioteca.setId(Integer.parseInt(rS.getString("id")));
                            biblioteca.setUrl(rS.getString("url"));
                            biblioteca.setNombreBiblioteca(rS.getString("nombreBiblioteca"));
                            biblioteca.setDescripcionBiblioteca(rS.getString("descripcionBiblioteca"));
                            mapa.put(biblioteca.getId(), biblioteca);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    i = 0;
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                //return new ArrayList<>(mapa.values);
                //return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El tamaño de la lista es" + mapa.values().size());
        return mapa.values();

    }

    public Biblioteca updateBiblioteca(Biblioteca biblioteca, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                // int id = biblioteca.getId();
                //String url = biblioteca.getUrl();
                String nombre = biblioteca.getNombreBiblioteca();
                String descripcion = biblioteca.getDescripcionBiblioteca();

                String queryBD = "update laboratorio set nombreBiblioteca='" + nombre + "', descripcionBiblioteca='" + descripcion + "' where id=" + id + ";";

                try {
                    createStatement.executeUpdate(queryBD);
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getBiblioteca(id);
    }

    public CambioHorario cambioBiblioteca(CambioHorario cam, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                switch (cam.getTipo()) {

                    case ADD:
                        //insert tabla disponibilidadBiblioteca con el id y la franja
                        LocalDateTime franja = cam.getFranja();
                        createStatement.executeUpdate("INSERT INTO disponibilidadbiblioteca (bibliotecaid,disponibilidad) VALUES (" + id + ", '" + franja + "');");
                        break;

                    case REMOVE:
                        //delete tabla disponibilidadBiblioteca con el id y la franja
                        LocalDateTime franjaRemove = cam.getFranja();
                        createStatement.executeUpdate("delete from disponibilidadbiblioteca where bibliotecaid=" + id + " AND disponibilidad='" + franjaRemove + "';");
                        break;


                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cam;
    }

    public boolean deleteBiblioteca(int id) throws SQLException, ClassNotFoundException {
        boolean valor = false;
        try {
            if (conector() == true) {

                String queryBD = "delete from biblioteca where id=" + id + ";";

                try {
                    createStatement.executeUpdate(queryBD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
**/
}