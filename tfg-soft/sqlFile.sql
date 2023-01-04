
CREATE TABLE if not exists usuario (id int auto_increment not null, 
url varchar (40),
nombre varchar (40) not null,
apellidos varchar (100) not null ,
grado varchar (20) not null,
primary key (id));
                     
insert into usuario (nombre, apellidos, grado) values ("Fernando", "Ortiz de Pedro", "GISIADE");
insert into usuario (nombre, apellidos, grado) values ("Pedro", "Ortiz de Zúñiga", "GISIADE");
insert into usuario (nombre, apellidos, grado) values ("Álvaro", "Córdoba Moreno", "GISITEL");
insert into usuario (nombre, apellidos, grado) values ("Ignacio", "Goiriena Solana", "GISIADE");
insert into usuario (nombre, apellidos, grado) values ("Javier", "Sancerni Nozaleda", "GISI");
                     
CREATE TABLE if not exists biblioteca (id int auto_increment not null, 
url varchar (40),
nombre varchar (40) not null,
descripcion varchar (100) not null,
primary key (id));

insert into biblioteca (nombre, descripcion) values ("MTPEPS","Biblioteca de la EPS en campus Monteprincipe");
insert into biblioteca (nombre, descripcion) values ("MEDEPS","Biblioteca de la Facultad de Medicina en campus Monteprincipe");
insert into biblioteca (nombre, descripcion) values ("MONECO","Biblioteca de la Facultad de Económicas en campus Moncloa");
insert into biblioteca (nombre, descripcion) values ("MONPER","Biblioteca de la Facultad de Periodismo en campus Moncloa");
insert into biblioteca (nombre, descripcion) values ("MONPUB","Biblioteca de la Facultad de Publicidad en campus Moncloa");

CREATE TABLE if not exists puesto (id int auto_increment not null,
url varchar (40),
descripcion varchar (100) not null,
bibliotecaid int,
primary key(id),
foreign key (bibliotecaid) references biblioteca(id) on delete cascade);

insert into puesto (descripcion, bibliotecaid) values ("Puesto 1", "1");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 2", "1");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 3", "1");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 4", "1");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 5", "1");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 1", "2");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 2", "2");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 3", "2");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 4", "2");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 5", "2");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 1", "3");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 2", "3");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 3", "3");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 4", "3");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 5", "3");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 1", "4");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 2", "4");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 3", "4");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 4", "4");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 5", "4");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 1", "5");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 2", "5");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 3", "5");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 4", "5");
insert into puesto (descripcion, bibliotecaid) values ("Puesto 5", "5");

CREATE TABLE if not exists sala (id int auto_increment not null,
url varchar (40),
descripcion varchar (100) not null,
bibliotecaid int,
primary key(id),
foreign key (bibliotecaid) references biblioteca(id) on delete cascade);

insert into sala (descripcion, bibliotecaid) values ("Sala 1", "1");
insert into sala (descripcion, bibliotecaid) values ("Sala 2", "1");
insert into sala (descripcion, bibliotecaid) values ("Sala 3", "1");
insert into sala (descripcion, bibliotecaid) values ("Sala 4", "1");
insert into sala (descripcion, bibliotecaid) values ("Sala 5", "1");
insert into sala (descripcion, bibliotecaid) values ("Sala 1", "2");
insert into sala (descripcion, bibliotecaid) values ("Sala 2", "2");
insert into sala (descripcion, bibliotecaid) values ("Sala 3", "2");
insert into sala (descripcion, bibliotecaid) values ("Sala 4", "2");
insert into sala (descripcion, bibliotecaid) values ("Sala 5", "2");
insert into sala (descripcion, bibliotecaid) values ("Sala 1", "3");
insert into sala (descripcion, bibliotecaid) values ("Sala 2", "3");
insert into sala (descripcion, bibliotecaid) values ("Sala 3", "3");
insert into sala (descripcion, bibliotecaid) values ("Sala 4", "3");
insert into sala (descripcion, bibliotecaid) values ("Sala 5", "3");
insert into sala (descripcion, bibliotecaid) values ("Sala 1", "4");
insert into sala (descripcion, bibliotecaid) values ("Sala 2", "4");
insert into sala (descripcion, bibliotecaid) values ("Sala 3", "4");
insert into sala (descripcion, bibliotecaid) values ("Sala 4", "4");
insert into sala (descripcion, bibliotecaid) values ("Sala 5", "4");
insert into sala (descripcion, bibliotecaid) values ("Sala 1", "5");
insert into sala (descripcion, bibliotecaid) values ("Sala 2", "5");
insert into sala (descripcion, bibliotecaid) values ("Sala 3", "5");
insert into sala (descripcion, bibliotecaid) values ("Sala 4", "5");
insert into sala (descripcion, bibliotecaid) values ("Sala 5", "5");

create table if not exists recursospuesto(
id int (100) auto_increment not null, 
url varchar (40),
nombre varchar (40) not null,
descripcion varchar (100) not null,
bibliotecaid int,
puestoid int,
primary key(id),
foreign key (bibliotecaid) references biblioteca(id) on delete cascade,
foreign key (puestoid) references puesto(id) on delete cascade);
                        
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("enchufe","enchufe 4 tomas", "1", "1");
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("ventana","ventana ventilación", "1", "4");
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("enchufeventana","enchufe 2 tomas y ventana cerca", "1", "5");
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("enchufe","enchufe 2 tomas", "2", "8");
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("ventana","ventana cerrada", "2", "10");   
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("aire","control aire acondicionado cerca", "1", "1");  
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("enchufe","enchufe 4 tomas", "3", "14");
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("ventana","ventana cerrada", "3", "11");  
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("enchufe","enchufe 4 tomas", "4", "20");
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("enchufeaire","enchufe 1 toma y aire acondicionado cerca", "4", "18");
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("enchufe","enchufe 4 tomas", "5", "23");
insert into recursospuesto (nombre, descripcion, bibliotecaid, puestoid) values ("ventana","ventana ventilación", "5", "25");       

															######################################FALTA RUNNEAR INSERTS
                        
create table if not exists recursossala(
id int (100) auto_increment not null, 
url varchar (40),
nombre varchar (40) not null,
descripcion varchar (100) not null,
bibliotecaid int,
salaid int,
primary key(id),
foreign key (bibliotecaid) references biblioteca(id) on delete cascade,
foreign key (salaid) references sala(id) on delete cascade);

insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("enchufe","enchufe 4 tomas", "1", "1");
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("ventana","ventana ventilación", "1", "4");
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("enchufeventana","enchufe 2 tomas y ventana cerca", "1", "5");
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("enchufe","enchufe 2 tomas", "2", "8");
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("ventana","ventana cerrada", "2", "10");   
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("aire","control aire acondicionado cerca", "1", "1");  
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("enchufe","enchufe 4 tomas", "3", "14");
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("ventana","ventana cerrada", "3", "11");  
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("enchufe","enchufe 4 tomas", "4", "20");
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("enchufeaire","enchufe 1 toma y aire acondicionado cerca", "4", "18");
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("enchufe","enchufe 4 tomas", "5", "23");
insert into recursospuesto (nombre, descripcion, bibliotecaid, salaid) values ("ventana","ventana ventilación", "5", "25"); 

create table if not exists reserva(
id int (100) auto_increment not null,
url varchar (40),
usuarioid int,
bibliotecaid int, 
puestoid int,
salaid int,																
disponibilidad datetime,
primary key(id),
foreign key (usuarioid) references usuario(id) on delete cascade,
foreign key (bibliotecaid) references biblioteca(id) on delete cascade ,
foreign key (puestoid) references puesto(id) on delete cascade,
foreign key (salaid) references sala(id) on delete cascade);			

insert into reserva (usuarioid, bibliotecaid, puestoid, disponibilidad) values ("1", "1", "4", "2022-05-20 10:00:00");
insert into reserva (usuarioid, bibliotecaid, salaid, disponibilidad) values ("1", "1", "1", "2022-05-20 15:04:32");
insert into reserva (usuarioid, bibliotecaid, puestoid, disponibilidad) values ("2", "3", "14", "2022-04-12 08:30:00");
insert into reserva (usuarioid, bibliotecaid, salaid, disponibilidad) values ("2", "3", "12", "2022-03-23 18:00:00");
insert into reserva (usuarioid, bibliotecaid, salaid, disponibilidad) values ("2", "3", "15", "2022-05-20 15:04:32");
insert into reserva (usuarioid, bibliotecaid, puestoid, disponibilidad) values ("3", "2", "8", "2022-05-20 15:00:00");
insert into reserva (usuarioid, bibliotecaid, puestoid, disponibilidad) values ("3", "2", "8", "2022-05-20 16:00:00");
insert into reserva (usuarioid, bibliotecaid, salaid, disponibilidad) values ("4", "4",  "3", "2022-05-20 15:04:32");
insert into reserva (usuarioid, bibliotecaid, salaid, disponibilidad) values ("4", "4", "3", "2022-05-20 15:04:32");
insert into reserva (usuarioid, bibliotecaid, puestoid, disponibilidad) values ("5", "3", "13", "2022-05-20 15:04:32");
insert into reserva (usuarioid, bibliotecaid, salaid, disponibilidad) values ("5", "3", "16", "2022-05-20 15:04:32");

create table if not exists reservarecursospuesto(
reservaid int,
recursopuestoid int,
foreign key (reservaid) references reserva(id) on delete cascade,
foreign key (recursopuestoid) references recursospuesto(id) on delete cascade,
primary key (reservaid,recursopuestoid));

create table if not exists reservarecursossala(
reservaid int,
recursosalaid int,
foreign key (reservaid) references reserva(id) on delete cascade,
foreign key (recursosalaid) references recursossala(id) on delete cascade,
primary key (reservaid,recursosalaid));

create table if not exists disponibilidadbiblioteca(
bibliotecaid int,
disponibilidad datetime,
foreign key (bibliotecaid) references biblioteca(id) on delete cascade,
primary key (bibliotecaid,disponibilidad));

create table if not exists disponibilidadpuesto(
puestoid int,
disponibilidad datetime,
foreign key (puestoid) references puesto(id) on delete cascade,
primary key (puestoid,disponibilidad));

create table if not exists disponibilidadsala(
salaid int,
disponibilidad datetime,
foreign key (salaid) references sala(id) on delete cascade,
primary key (salaid,disponibilidad));

create table if not exists disponibilidadrecursospuesto(
recursopuestoid int,
disponibilidad datetime,
foreign key (recursopuestoid) references recursospuesto(id) on delete cascade,
primary key (recursopuestoid,disponibilidad));

create table if not exists disponibilidadrecursossala(
recursosalaid int,
disponibilidad datetime,
foreign key (recursosalaid) references recursossala(id) on delete cascade,
primary key (recursosalaid,disponibilidad));



