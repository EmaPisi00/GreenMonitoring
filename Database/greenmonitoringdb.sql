create database GreenMonitoringDB;
use GreenMonitoringDB;

create table Azienda(
email varchar(100) primary Key,
password varchar(30) not null,
telefono varchar(15) not null,
citta varchar(30) not null,
indirizzo varchar(30) not null,
provincia varchar(30) not null,
nome_azienda varchar(30) unique not null,
codice_associazione varchar(8) unique not null,
partita_iva varchar(11) unique not null
);

create table Dipendente(
email varchar(100) primary Key,
password varchar(30) not null,
telefono varchar(15) not null,
citta varchar(30) not null,
indirizzo varchar(30) not null,
provincia varchar(30) not null,
azienda varchar(100),
nome varchar(10) not null,
cognome varchar(10) not null,
foreign key (azienda) references Azienda(email) on delete set null on update cascade
);

create table Pianta(
id int primary key auto_increment,
azienda varchar(100),
nome varchar(30) not null, 
descrizione varchar(255)not null,
ph_min float not null,
ph_max float not null,
temperatura_min float not null,
temperatura_max float not null,
umidita_min float not null,
umidita_max float not null,
immagine varchar(100),
foreign key (azienda) references Azienda(email) on delete set null on update cascade
);

create table Fisiopatie(
id int primary key auto_increment,
pianta int not null,
nome varchar(20) not null,
descrizione varchar(100) not null,
umidita_terreno_min float not null,
umidita_terreno_max float not null,
temperatura_min float not null,
temperatura_max float not null,
umidita_aria_min float not null,
umidita_aria_max float not null,
foreign key (pianta) references Pianta(id) on delete cascade on update cascade
);

create table Terreno(
id int primary key auto_increment ,
nome varchar(30),
azienda varchar(100) not null,
immagine varchar(100),
latitudine float not null,
longitudine float not null,
superfice varchar(100) not null,
foreign key (azienda) references Azienda(email) on delete cascade on update cascade
);

create table Coltivazione(
id int primary key auto_increment,
pianta integer not null,
terreno integer not null,
stato_archiviazione boolean not null,
data_inizio date not null,
data_fine date,
foreign key (pianta) references Pianta(id) on delete cascade on update cascade,
foreign key (terreno) references Terreno(id) on delete cascade on update cascade
);

create table Sensore(
id int primary key auto_increment,
coltivazione int,
azienda varchar(100),
tipo varchar(20) not null,
idM varchar(20) not null,
foreign key (azienda) references Azienda(email) on delete cascade on update cascade,
foreign key(coltivazione) references Coltivazione(id) on delete set null on update cascade
);

create table Misurazione_sensore(
id int primary key auto_increment,
coltivazione int not null,
valore varchar(20) not null,
data date not null,
ora time not null,
tipo varchar(20) not null,
sensore_id int not null,
foreign key(coltivazione) references Coltivazione(id) on delete cascade on update cascade,
foreign key(sensore_id) references Sensore(id) on update cascade
);

create table Notifica(
id int primary key auto_increment,
coltivazione int not null,
azienda varchar(100),
tipo varchar(20) not null,
data date not null,
contenuto varchar(200) not null,
foreign key(coltivazione) references Coltivazione(id) on delete cascade on update cascade,
foreign key (azienda) references Azienda(email) on delete cascade on update cascade
);