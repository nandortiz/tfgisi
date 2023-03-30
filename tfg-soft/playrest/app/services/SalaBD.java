package services;

import entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static consistency.ConexionJDBC.connect;

public class SalaBD extends ConexionBD {

    private static SalaBD instance;
    public static SalaBD getInstance() {
        if (instance == null) {
            instance = new SalaBD();
        }
        return instance;
    }

/*
    public Sala addSala(Sala sala, int bibliotecaID) throws SQLException, ClassNotFoundException {
        int identificador= -1;
        if (conector() == true) {
            con.setAutoCommit(false);
            try {
                createStatement.executeUpdate("INSERT INTO sala (id,descripcion,bibliotecaID) VALUES (4,'" + sala.getDescripcionElementoReservable() + "', " + sala.getBibliotecaID()+");",Statement.RETURN_GENERATED_KEYS);

                //TODO Full comment
                String descripcion = sala.getDescripcionSala();
                ArrayList<LocalDateTime> disponibilidadSala = new ArrayList<>();
                disponibilidadSala= sala.getListaDisponibilidadSala();

                System.out.println("El identificador de la biblioteca es: " + bibliotecaID);
                createStatement.executeUpdate("INSERT INTO sala (descripcion,bibliotecaID) VALUES ('" + descripcion + "', " + bibliotecaID+");",Statement.RETURN_GENERATED_KEYS);
                ResultSet prueba = createStatement.getGeneratedKeys();
                //hacer 3 insterts: uno para eltodisp, otro para eltoreservable, y otro para sala
                prueba.next();
                identificador=prueba.getInt(1);
                System.out.println("la fila es " + identificador );
                String patron = "/bibliotecas/" + bibliotecaID + "/salas/";
                String url = patron+identificador;
                createStatement.executeUpdate("UPDATE  sala set url ='" + url + "' where id = "+ identificador + ";");

                for (LocalDateTime dispo:disponibilidadSala) {

                    createStatement.executeUpdate("INSERT INTO disponibilidadsala (salaid,disponibilidad) VALUES (" + identificador + ", '" + dispo +  "');");
                }//TODO Fin full comment
                con.commit();
                con.setAutoCommit(true);
                con.close();
            }
            catch(SQLException e){
                con.rollback();
            }

        }
        // return sala;
        return getSala(4);
    }
*/
   /* public Sala getSala(int id) {
        Sala sala = new Sala();
        try {
            if (conector() == true) {
                String queryBD = "select id, url, descripcionSala, bibliotecaid  from sala where id =" + id + " ;";

                //String queryBD = "select sala.id, sala.url, sala.descripcionSala, sala.bibliotecaid, disponibilidadsala.disponibilidad  from sala inner join disponibilidadsala on sala.id = disponibilidadsala.salaid where sala.id =" + id + " AND sala.bibliotecaid =" + bibliotecaID +" ;";
                //String queryBD1="select sala.id, sala.url, sala.descripcionSala, sala.bibliotecaid , recursosala.id as recursoSalaID , recursosala.url as recursoSalaURL, recursosala.nombre as recursoSalaNombre, recursosala.descripcion as recursoSalaDescripcion, recursosala.bibliotecaid as recursoBibliotecaID, recursosala.salaid as recursoSalaID from sala INNER JOIN recursosala on sala.id = recursosala.salaid  where sala.id =" + id + " AND sala.bibliotecaid =" + bibliotecaID +" ;";
                //3 inner joins, con reltoresr, eltodisp, y disponibilidad
                int i = 0;

                try {


                    rS = createStatement.executeQuery(queryBD);
                    sala.setId(rS.getInt("id"));
                    sala.setDescripcionElementoReservable(rS.getString("descripcionSala"));
                    sala.setBibliotecaID(rS.getInt("bibliotecaid"));
                } catch (SQLException ex) {
                    Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null) {
                    return null;
                }
                con.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sala;
    }
*/
public Sala addSala(Sala sala, int bibliotecaID) throws SQLException, ClassNotFoundException {
    Connection cn = connect();
    int identificador = -1;
    String url = "";
    if (conector() == true) {
        try {

            String descripcion = sala.getDescripcion();
            TipoElementoReservable tipo = sala.getTipo();
            int aforoSala = sala.getAforo();

            //ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
            //  disponibilidad = biblioteca.getListaDisponibilidadBiblioteca();
            Statement st = cn.createStatement();
            System.out.println("INSERT INTO elementoReservable (descripcion, tipo, bibliotecaID, aforoSala) VALUES ('"+descripcion+"','"+tipo+"','"+bibliotecaID+"', '"+aforoSala+"');");
            st.executeUpdate("INSERT INTO elementoReservable (descripcion, tipo, bibliotecaID, aforoSala) VALUES ('"+descripcion+"','"+tipo+"','"+bibliotecaID+"', '"+aforoSala+"');", Statement.RETURN_GENERATED_KEYS);

            // A la nueva entidad hay que "establecerla la URL"
            ResultSet keys = st.getGeneratedKeys();
            keys.next();
            identificador = keys.getInt(1);

            String patronURL="/biblioteca/"+bibliotecaID+"/salas/";
            String urlNuevaSala=patronURL+identificador;

            //UPDATE de la sala con id = id y actualizar la url con urlNuevaSala
            st.executeUpdate("UPDATE elementoReservable set url='" + urlNuevaSala+ "' where id= "+ identificador +";");

            try {

                con.close();
            } catch (SQLException ex) {
                System.out.println("Error acceso base de datos - addSala");
                Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //return biblioteca;
    return getSala(identificador);
    //return url;
}

    public Sala getSala(int id) {Sala sala = new Sala();

        try {
            if (conector() == true) {
                String queryBD = "select id, descripcion, tipo, bibliotecaID, aforoSala from elementoReservable where id="+id+";";
                try {

                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()){
                        sala.setId(rS.getInt("id"));
                        sala.setDescripcion(rS.getString("descripcion"));
                        sala.setTipo(TipoElementoReservable.valueOf(rS.getString("tipo")));
                        sala.setBibliotecaID(rS.getInt("bibliotecaID"));
                        sala.setAforo(rS.getInt("aforoSala"));
                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getSala");
                    ex.printStackTrace();
                    Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (sala.getId()==-1){
            return null;
        }
        else {
            return sala;
        }

    }

    public Collection<SalaShort> getAllSalas(int bibliotecaID) {
        List<SalaShort> salas = new ArrayList();

        try {
            if (conector() == true) {
                String queryBD = "select id, url from elementoreservable where bibliotecaID = '"+bibliotecaID+"';";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        //Cada vuelta while es un línea del resultado de la consulta -> Biblioteca
                        SalaShort sala = new SalaShort();
                        sala.setId(rS.getInt("id"));
                        sala.setUrl(rS.getString("url"));

                        salas.add(sala);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getAllSalas");
                    ex.printStackTrace();
                    Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return salas;
    }

    public boolean deleteSala(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

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
                    System.out.println("Error acceso base de datos - deleteSala"); //TODO staba deleteBiblioteca
                    Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex); //TODO estaba BibliotecaBD
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex); //TODO estaba BibliotecaBD
        }
        return valor;
    }

}
