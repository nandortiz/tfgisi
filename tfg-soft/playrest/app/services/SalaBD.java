package services;

import entities.*;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalaBD extends ConexionBD {

    private static SalaBD instance;
    public static SalaBD getInstance() {
        if (instance == null) {
            instance = new SalaBD();
        }
        return instance;
    }


    public Sala addSala(Sala sala, int bibliotecaID) throws SQLException, ClassNotFoundException {
        int identificador= -1;
        if (conector() == true) {
            con.setAutoCommit(false);
            try {
                createStatement.executeUpdate("INSERT INTO sala (id,descripcion,bibliotecaID) VALUES (4,'" + sala.getDescripcionElementoReservable() + "', " + sala.getBibliotecaID()+");",Statement.RETURN_GENERATED_KEYS);

                /**
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
                }**/
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

    public Sala getSala(int id) {
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


    public Collection<SalaShort> getAllSalas(int bibliotecaID) {

        HashMap<Integer,SalaShort> mapa = new HashMap<>();

        try {
            if(conector()==true){
                String queryBD = "select id, url, descripcionSala, bibliotecaid  from sala where bibliotecaid =" + bibliotecaID + " ;";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBD);

                    while (rS.next()) {

                        SalaShort sala;

                        if (mapa.containsKey(Integer.parseInt(rS.getString("id")))){
                            sala=mapa.get(Integer.parseInt(rS.getString("id")));
                        }
                        else{
                            sala = new SalaShort();
                            sala.setId(Integer.parseInt(rS.getString("id")));
                            sala.setUrl(rS.getString("url"));
                            sala.setDescripcionSala(rS.getString("descripcionSala"));
                            sala.setBibliotecaID(Integer.parseInt(rS.getString("bibliotecaid")));
                            mapa.put(sala.getId(), sala);
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    i=0;
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{
                //return new ArrayList<>(mapa.values);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("El tamaño de la lista es" + mapa.values().size());
        return null;
    }

}
