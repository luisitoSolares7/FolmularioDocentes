GO
INSERT INTO tblPersona (nombre,apellidoP,apellidoM,correo,telefono) VALUES('Jose Carlos','Gutierrez','Marca','Joseco@gmail.com',70056885);
GO
INSERT INTO tblPersona (nombre,apellidoP,apellidoM,correo,telefono) VALUES('Marco Antonio','Terrazas','Morales','terrazas30@gmail.com',79029339);
GO
INSERT INTO tblPersona (nombre,apellidoP,apellidoM,correo,telefono) VALUES('Maria Sandy','Peña','Suarez','Mariasandy@gmail.com',70234345);
GO
INSERT INTO tblPersona (nombre,apellidoP,apellidoM,correo,telefono) VALUES('Claudia ','jaurigui','Gonzales','Jclaudia@gmail.com',70332342);
GO
insert into tblCuenta(nombreCuenta,contracena,tipo,estado) values ('Jose Carlos Gutierrez',(select [dbo].[p_verificacionLogin]('1234567')),2,1);
GO
insert into tblInvitacion (token,fechaInvitacion,fechaRespuesta,estado,fkCuenta,fkPersona) values ('1234567',GETDATE(),GETDATE(),1,1,1);
go
if object_id('[p_verificacionCodigo]') is not null
drop Function [dbo].[p_verificacionCodigo];
go
create function [dbo].[p_verificacionCodigo](
@p_token text
) returns table
as
return (select inv.id as invitacion, concat(per.nombre,' ',per.apellidoP) AS nombre, per.correo as correo, inv.estado as estado from tblInvitacion inv  
join tblPersona per on  per.id=inv.fkPersona where inv.token like @p_token);
GO
DELETE FROM [dbo].[tblVersion]
GO

USE [formularios]
GO

INSERT INTO [dbo].[tblVersion]
           ([versionMayor]
           ,[versionMenor]
           ,[patch])
     VALUES
           (1
           ,1
           ,0)
GO


