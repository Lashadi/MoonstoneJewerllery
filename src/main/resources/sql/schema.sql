drop database moonstonejewerlary;

create database moonstonejewerlary;

use moonstonejewerlary;

create table User(
                     uId Varchar(6) primary key not null,
                     uName Varchar(10),
                     uRole Varchar(20),
                     uPassword Varchar(10) not null
);


create table Customer(
                         cId Varchar(6) primary key,
                         cName Varchar(10),
                         cAddress Varchar(20),
                         cTel Varchar(10),
                         cEmail varchar (20)not null,
                            uId Varchar(6) not null,
                         constraint foreign key(uId) references User(uId) on delete cascade on update cascade
);



create table Payment(
                        pId Varchar(6) primary key,
                        pBill Varchar(10),
                        pAmount Decimal(10,2),
                        date Date,
                        cId Varchar(6) not null,
                        constraint foreign key(cId) references Customer(cId) on delete cascade on update cascade
);


create table Orders(
                       oId Varchar(10) primary key,
                       pId Varchar(6),
                       cId Varchar(6),
                       constraint foreign key(cId) references Customer(cId) on delete cascade on update cascade,
                       constraint foreign key(pId) references Payment(pId) on delete cascade on update cascade
);

create table Item(
                     iCode Varchar(10) primary key,
                     iCategory Varchar(100),
                     iQty Int(20),
                     iPrice Decimal(10,2),
                     iName Varchar(30)
);



create table Employee(
                         eId Varchar(6) primary key,
                         eName Varchar(10),
                         eAddress Varchar(20),
                         eTel Varchar(10),
                         uId Varchar(6) not null,
                         constraint foreign key(uId) references User(uId) on delete cascade on update cascade
);


create table Salary(
                       sId Varchar(6) primary key,
                       sAmount Decimal(10,2),
                       date Date,
                       eId Varchar(6) not null,
                       constraint foreign key(eId) references Employee(eId) on delete cascade on update cascade
);


create table Order_Detail(
                             id Varchar(10) primary key not null,
                             price Decimal(10,2),
                             qty Int(20),
                             iCode Varchar(10) not null,
                             constraint foreign key(iCode) references Item(iCode) on delete cascade on update cascade
);


create table Supplier(
                         supId Varchar(10) primary key not null,
                         supName Varchar(20),
                         uId Varchar(6) not null,
                         iName Varchar(30) not null,
                         constraint foreign key(uId) references User(uId) on delete cascade on update cascade
);



create table Report(
                       rId Varchar(10) primary key,
                       rCetogery Varchar(500)
);


create table Report_Detail(
                              rId Varchar(10) not null,
                              pId Varchar(6) not null,
                              date Date,
                              constraint foreign key(rId) references Report(rId) on delete cascade on update cascade,
                              constraint foreign key(pId) references Payment(pId) on delete cascade on update cascade
);


create table Supplier_Detail(
                                id Varchar(10) primary key not null,
                                qty Int(20),
                                date Date,
                                supId Varchar(10) not null,
                                constraint foreign key(supId) references Supplier(supId) on delete cascade on update cascade
);
