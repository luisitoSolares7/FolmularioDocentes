
INSERT INTO tblPersona (nombre,apellidoP,apellidoM,correo,telefono) VALUES('Jose Carlos','Gutierrez','Marca','Joseco@gmail.com',70056885);

INSERT INTO tblPersona (nombre,apellidoP,apellidoM,correo,telefono) VALUES('Marco Antonio','Terrazas','Morales','terrazas30@gmail.com',79029339);

INSERT INTO tblPersona (nombre,apellidoP,apellidoM,correo,telefono) VALUES('Maria Sandy','Peña','Suarez','Mariasandy@gmail.com',70234345);

INSERT INTO tblPersona (nombre,apellidoP,apellidoM,correo,telefono) VALUES('Claudia ','jaurigui','Gonzales','Jclaudia@gmail.com',70332342);

insert into tblCuenta(nombreCuenta,contracena,tipo,estado) values ('Jose Carlos Gutierrez',(select [dbo].[p_verificacionLogin]('1234567')),2,1);

insert into tblInvitacion (token,fechaInvitacion,fechaRespuesta,estado,fkCuenta,fkPersona) values ('1234567',GETDATE(),GETDATE(),1,3,1);


create function [dbo].[p_verificacionCodigo]
(
@p_token text
) returns table
as
return (select inv.id as invitacion, concat(per.nombre,' ',per.apellidoP) AS nombre, per.correo as correo, inv.estado as estado from tblInvitacion inv  
join tblPersona per on  per.id=inv.fkPersona where inv.token like @p_token);

