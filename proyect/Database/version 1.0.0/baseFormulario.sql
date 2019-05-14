create database Formularios;
GO
use Formularios;
GO
create table tblCuenta(
id int IDENTITY primary key,
nombreCuenta varchar(45),
contracena varchar(30),
 tipo int,
estado bit
);
GO
create table tblInvitacion(
id int IDENTITY primary key,
token text,
fechaInvitacion date,
fechaRespuesta date,
estado bit,
fkCuenta int,
fkPersona int
);
GO
create table tblPersona(
id int IDENTITY primary key,
nombre varchar(40),
apellidoP varchar(40),
apellidoM varchar(40),
correo varchar(50),
telefono int,
);
GO
create table tblFormAccidente(
id int IDENTITY primary keY,
descripcion text,
foto image,
fecha date
);
GO
create table tblFormReprogramacion(
id int IDENTITY primary keY,
fecha date,
);
GO
create table tblFormFotocopia(
id int IDENTITY primary keY,
fecha date,
cantidad int,
tipoDocuento varchar(45),
codigo text
);
GO
create table tblFormClasesFuera(
id int IDENTITY primary keY,
fecha date,
);
GO
create table tblResAccidente(
id int IDENTITY primary keY,
fkCuenta int,
fkAccidente int,
estado bit,
fecha date
);
GO
create table tblResReprogramacion(
id int IDENTITY primary keY,
fkCuenta int,
fkReprogramacion int,
estado bit,
fecha date,
);
GO
create table tblResFotocopia(
id int IDENTITY primary keY,
fkCuenta int,
fkFotocopia int,
fecha date,
estado bit,
);
GO
create table tblResClasesFuera(
id int IDENTITY primary keY,
fkCuenta int,
fkClaseFuera int,
fecha date,
estado bit,
);

GO
ALTER TABLE tblResClasesFuera
ADD FOREIGN KEY (fkCuenta) REFERENCES tblCuenta(id);
GO
ALTER TABLE tblResFotocopia
ADD FOREIGN KEY (fkCuenta) REFERENCES tblCuenta(id);
GO
ALTER TABLE tblResReprogramacion
ADD FOREIGN KEY (fkCuenta) REFERENCES tblCuenta(id);
GO
ALTER TABLE tblResAccidente
ADD FOREIGN KEY (fkCuenta) REFERENCES tblCuenta(id);
GO

ALTER TABLE tblResClasesFuera
ADD FOREIGN KEY (fkClaseFuera) REFERENCES tblFormClasesFuera(id);
GO
ALTER TABLE tblResFotocopia
ADD FOREIGN KEY (fkFotocopia) REFERENCES tblFormFotocopia(id);
GO
ALTER TABLE tblResReprogramacion
ADD FOREIGN KEY (fkReprogramacion) REFERENCES tblFormReprogramacion(id);
GO
ALTER TABLE tblResAccidente
ADD FOREIGN KEY (fkAccidente) REFERENCES tblFormFotocopia(id);
GO
create function [dbo].[p_verificacionLogin]
(@p_contra varchar(30)
) returns varchar(30)
as
begin
 return CONVERT(VARCHAR(30), HashBytes('MD5', @p_contra), 2);
end;
GO
create function [dbo].[p_verificacionUsuarios]
(@p_usuario varchar(45),
@p_contra varchar(30)
) returns table
as
return (select * from tblCuenta tblC
    where tblC.nombreCuenta=@p_usuario and tblC.contracena=(select [dbo].[p_verificacionLogin](@p_contra)));
GO