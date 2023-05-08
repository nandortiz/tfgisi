package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.*;



public class ConexionBD {

    protected static Connection con;
    protected static final String driver = "com.mysql.cj.jdbc.Driver";
    protected static final String user = "root";
    protected static final String pass = "root";
    protected static final String url = "jdbc:mysql://localhost:3306/gestion?useSSL=false";

    protected Statement createStatement;

    protected ResultSet rS; //----Qué son rS?
    protected ResultSet rS1;

    protected boolean conector() throws SQLException, ClassNotFoundException {
        //Reseteo la conexión a la base de datos a NULL
        con = null;
        boolean valor = false;
        try {
            int i = 0; //----String valor="fallo";
            Class.forName(driver);
            //Nos conectamos a BD
            con= (com.mysql.cj.jdbc.JdbcConnection) DriverManager.getConnection(url, user, pass);
            //con= DriverManager.getConnection(url, user, pass);
            //Si se conecta correctamente, se muestra mensaje de conexión establecida
            if (con != null) ;
            {

                createStatement = con.createStatement();
                  String crear = "create database if not exists gestion;";
                String uso = "use gestion;";
             /*   String crearUsuario = "create table if not exists usuario(\n" +
                        "id int(100)primary key auto_increment not null, \n" +
                        "url varchar (40), \n" +
                        "nombre varchar (40) not null, \n" +
                        "apellidos varchar (40) not null, \n" +
                        "grado varchar (20) not null\n" +
                        ");";
            */
                String crearBiblioteca = "create table if not exists biblioteca(\n" +
                        "id int(100) primary key auto_increment not null,\n" +
                        "url varchar (40) default null,\n" +
                        "nombre varchar (40) not null,\n" +
                        "descripcion varchar (100) not null,\n" +
                        "apertura datetime not null,\n" +
                        "cierre datetime not null \n" +
                        ")";

              /*   String crearPuesto = "create table if not exists puesto(\n" +
                        "id int(100) primary key auto_increment not null,\n" +
                        "info varchar (40) not null,\n" +
                        " )";

               String crearSala = "create table if not exists sala(\n" +
                        "id int(100) primary key auto_increment not null,\n" +
                        "aforo varchar (40) not null,\n" +
                        " )";

                String crearLibro = "create table if not exists libro(\n" +
                        "ISBN int(100) primary key auto_increment not null,\n" +
                        "infoExtra varchar (50) not null,\n" +
                        " )";

                String crearOrdenador = "create table if not exists ordenador(\n" +
                        "numSerie int(100) primary key auto_increment not null,\n" +
                        "modelo varchar (50) not null,\n" +
                        " )";
              */

               /* String crearRecursoExtra = "create table if not exists recursoextra(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "nombre varchar (100) not null,\n" +
                        "url varchar (50),\n" +
                        "descripcion varchar (100) not null,\n" +
                        "tipo varchar (1) default null, \n" +
                        "bibliotecaID int,\n" +
                        "ISBN int (13) default null, \n" +
                        "numSerie varchar(30) default null, \n" +
                        "foreign key (bibliotecaID) references biblioteca (id) on delete cascade,\n" +
                        " );";
*/
                String crearElementoReservable = "create table if not exists elementoreservable(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "url varchar (50) default null,\n" +
                        "descripcion varchar (100) not null,\n" +
                        "tipo varchar (1) default null, \n" +
                        "bibliotecaID int default null,\n" +
                        "aforoSala int (2) default null, \n" +
                        "infoPuesto varchar(30) default null, \n" +
                        "foreign key (bibliotecaID) references biblioteca (id) on delete cascade\n" +
                        ");";


                String crearRecursoExtra = "create table if not exists recursoextra(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "nombre varchar (100) default null, \n" +
                        "url varchar (50) default null,\n" +
                        "descripcion varchar (100) not null,\n" +
                        "tipo varchar (1) default null, \n" +
                        "bibliotecaID int default null,\n" +
                        "numSerie varchar(30) default null, \n" +
                        "ISBN int default null, \n" +
                        "foreign key (bibliotecaID) references biblioteca (id) on delete cascade\n" +
                        ");";
 /*               String crearReserva = "create table if not exists reserva(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "url varchar (40) default null,\n" +
                        "usuarioID int default null,\n" +
                        "elementoReservableID int,\n" +
                        "fecha datetime,\n" +
                        "foreign key (usuarioID) references usuario (id) on delete cascade,\n" +
                        "foreign key (elementoReservableID) references elementoReservable(id) on delete cascade \n" +
                        ")";

                String crearReservaExtra = "create table if not exists reservaExtra(\n" +
                        "RecursoExtraID int (100) not null,\n" +
                        "reservaID int (100) not null,\n" +
                        "foreign key (RecursoExtraID) references recursoextra (id) on delete cascade,\n" +
                        "foreign key (reservaID) references reserva(reservaID) on delete cascade, \n" +
                        "primary key (reservaID, RecursoExtraID)\n" +
                        ")";


                String crearDisponibilidadElementoReservable= "create table if not exists disponibilidadelementoreservable(\n" +
                        "elementoReservableID int,\n" +
                        "fecha datetime,\n" +
                        "foreign key (elementoReservableID) references elementoreservable(id) on delete cascade,\n" +
                        "primary key (elementoReservableID, fecha)\n" +
                        ");";

                String crearDisponibilidadRecursoExtra= "create table if not exists disponibilidadrecursoextra(\n" +
                        "recursoExtraID int,\n" +
                        "fecha datetime,\n" +
                        "foreign key (recursoExtraID) references recursoextra(id) on delete cascade,\n" +
                        "primary key (recursoExtraID, fecha)\n" +
                        ");";

              /*  String crearPuesto = "create table if not exists puesto(\n" +
                        "id int(100) primary key auto_increment not null,\n" +
                        "url varchar (50),\n" +
                        "descripcion varchar (100) not null,\n" +
                         "bibliotecaID int, \n" +
                        "foreign key (bibliotecaID) references biblioteca(id) on delete cascade\n" +
                        " );\n";
               String crearSala = "create table if not exists sala(\n" +
                        "id int(100) primary key auto_increment not null,\n" +
                        "url varchar (50),\n" +
                        "descripcion varchar (100) not null,\n" +
                        "bibliotecaID int, \n" +
                        "foreign key (bibliotecaID) references biblioteca(id) on delete cascade\n" +
                        " );\n";
                        */

             /*  String crearRecursoPuesto = "create table if not exists recursospuesto(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        " url varchar (50),\n" +
                        "nombre varchar (50) not null,\n" +
                        "descripcion varchar (100) not null,\n" +
                        "bibliotecaID int,\n" +
                        "puestoID int,\n" +
                        "foreign key (bibliotecaID) references biblioteca (id) on delete cascade,\n" +
                        "foreign key (puestoID) references puesto (id) on delete cascade\n" +
                       " );";
               String crearRecursoSala="create table if not exists recursossala (\n"+
                        "id int (100) primary key auto_increment not null,\n"+
                        " url varchar (50),\n"+
                        "nombre varchar (50) not null,\n"+
                        "descripcion varchar (100) not null,\n"+
                        "bibliotecaID int,\n"+
                        "salaID int,\n"+
                        "foreign key (bibliotecaID) references biblioteca (id) on delete cascade,\n"+
                        "foreign key (salaID) references sala (id) on delete cascade \n"+
                        " );";
                  */
//'reserva', 'CREATE TABLE `reserva` (\n  `id` int NOT NULL AUTO_INCREMENT,
//\n  `url` varchar(40) DEFAULT NULL,\n
// `usuarioid` int DEFAULT NULL,\n
  //`bibliotecaid` int DEFAULT NULL,\n
  //`puestoid` int DEFAULT NULL,\n
  //`salaid` int DEFAULT NULL,\n
   //`disponibilidad` datetime DEFAULT NULL,\n
    //PRIMARY KEY (`id`),\n  KEY `usuarioid` (`usuarioid`),\n
    //KEY `bibliotecaid` (`bibliotecaid`),\n
    //KEY `puestoid` (`puestoid`),\n
    //KEY `salaid` (`salaid`),\n
   // CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`usuarioid`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,\n
    //CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`bibliotecaid`) REFERENCES `biblioteca` (`id`) ON DELETE CASCADE,\n
    //CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`puestoid`) REFERENCES `puesto` (`id`) ON DELETE CASCADE,\n
  //  CONSTRAINT `reserva_ibfk_4` FOREIGN KEY (`salaid`) REFERENCES `sala` (`id`) ON DELETE CASCADE\n) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci'

             /*    String crearReservaPuesto = "create table if not exists reservapuesto(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "url varchar (50),\n" +
                        "usuarioID int,\n" +
                        "bibliotecaID int,\n" +
                        "puestoID int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (usuarioID) references usuario (id) on delete cascade,\n" +
                        "foreign key (bibliotecaID) references biblioteca(id) on delete cascade,\n" +
                        "foreign key (puestoID) references puesto(id) on delete cascade \n" +
                        ");";
               String crearReservaSala = "create table if not exists reservasala(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "url varchar (50),\n" +
                        "usuarioID int,\n" +
                        "bibliotecaID int,\n" +
                        "salaID int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (usuarioID) references usuario (id) on delete cascade,\n" +
                        "foreign key (bibliotecaID) references biblioteca(id) on delete cascade,\n" +
                        "foreign key (salaID) references sala(id) on delete cascade \n" +
                        ");";
                        */
       /*         String crearReservaRecursoPuesto = "create table if not exists reservarecursospuesto( \n" +
                        "reservaPuestoID int, \n" +
                        "recursoPuestoID int,\n" +
                        "foreign key (reservaPuestoID) references reservapuesto (id) on delete cascade,\n" +
                        "foreign key (recursoPuestoID) references recursospuesto (id) on delete cascade,\n" +
                        "primary key (reservaPuestoID, recursoPuestoID)\n" +
                        ");";
                  String crearReservaRecursoSala = "create table if not exists reservarecursossala(\n" +
                        "reservaSalaID int, \n" +
                        "recursoSalaID int,\n" +
                        "foreign key (reservaSalaID) references reservasala (id) on delete cascade,\n" +
                        "foreign key (recursoSalaID) references recursossala (id) on delete cascade,\n" +
                        "primary key (reservaSalaID, recursoSalaID)\n" +
                        ");";

                String crearDisponibilidadBiblioteca = "create table if not exists disponibilidadbiblioteca(\n" +
                        "bibliotecaID int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (bibliotecaID) references biblioteca(id) on delete cascade,\n" +
                        "primary key (bibliotecaID, disponibilidad)\n" +
                        ");";
                 String crearDisponibilidadPuesto = "create table if not exists disponibilidadpuesto(\n" +
                        "puestoID int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (puestoID) references puesto(id) on delete cascade,\n" +
                        "primary key (puestoID, disponibilidad)\n" +
                        ");";
               String crearDisponibilidadSala = "create table if not exists disponibilidadsala(\n" +
                        "salaID int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (salaID) references sala(id) on delete cascade,\n" +
                        "primary key (salaID, disponibilidad)\n" +
                        ");";
                String crearDisponibilidadRecursoPuesto = "create table if not exists disponibilidadrecursospuesto(\n" +
                        "recursoPuestoID int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (recursoPuestoID) references recursospuesto(id) on delete cascade,\n" +
                        "primary key (recursoPuestoID,disponibilidad)\n" +
                        ");";
                String crearDisponibilidadRecursoSala = "create table if not exists disponibilidadrecursossala(\n" +
                        "recursoSalaID int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (recursoSalaID) references recursossala(id) on delete cascade,\n" +
                        "primary key (recursoSalaID,disponibilidad)\n" +
                        ");";
*/

                createStatement.executeUpdate(crear);
                createStatement.executeUpdate(uso);
                createStatement.executeUpdate(crearBiblioteca);
                createStatement.executeUpdate(crearElementoReservable);
                createStatement.executeUpdate(crearRecursoExtra);
                //    createStatement.executeUpdate(crearUsuario);
                //createStatement.executeUpdate(crearPuesto);
                //createStatement.executeUpdate(crearSala);
                //createStatement.executeUpdate(crearLibro);
                //createStatement.executeUpdate(crearOrdenador);
              //  createStatement.executeUpdate(crearDisponibilidadElementoReservable);
              //  createStatement.executeUpdate(crearDisponibilidadRecursoExtra);
              //  createStatement.executeUpdate(crearReserva);
              //  createStatement.executeUpdate(crearRecursoExtra);
              //  createStatement.executeUpdate(crearReservaExtra);
              //  createStatement.executeUpdate(crearRecurso);
              //  createStatement.executeUpdate(crearRecursoPuesto);
              //  createStatement.executeUpdate(crearRecursoSala);
              //  createStatement.executeUpdate(crearReservaPuesto);
              //  createStatement.executeUpdate(crearReservaSala);
              //  createStatement.executeUpdate(crearReservaRecursoPuesto);
              //  createStatement.executeUpdate(crearReservaRecursoSala);
              //  createStatement.executeUpdate(crearDisponibilidadBiblioteca);
             //   createStatement.executeUpdate(crearDisponibilidadPuesto);
              //  createStatement.executeUpdate(crearDisponibilidadSala);
             //   createStatement.executeUpdate(crearDisponibilidadRecursoPuesto);
             //   createStatement.executeUpdate(crearDisponibilidadRecursoSala);

                valor = true;
            }
        }
        //Si no se realiza correctamente la conexión a la BD, se muestra el mensaje:
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return valor;


    }
}