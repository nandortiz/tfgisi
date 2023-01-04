

    create table if not exists reservaExtra(
                        RecursoExtraID int (100)  not null,
                        reservaID int (100)  not null,
                        foreign key (RecursoExtraID) references recursoextra (id) on delete cascade,
                        foreign key (reservaID) references reserva(reservaID) on delete cascade ,
                        primary key (reservaID, RecursoExtraID)
                        );
                        
                        