use Formularios;
if object_id('[dbo].[pr_insertarFormFotocopia]') is not null
  drop procedure [dbo].[pr_insertarFormFotocopia];
GO
create procedure [dbo].[pr_insertarFormFotocopia]
@idCuenta int,
@fecha date,
@cantidad int,
@tipoDocuento text,
@materia text,
@img1 text,
@img2 text,
@img3 text
 as
 BEGIN
 if(@img2 like ' ')
 begin
 insert into [dbo].[tblFormFotocopia] (fecha,cantidad,tipoDocuento,img1,materia)
 values (@fecha,@cantidad,@tipoDocuento,@img1,@materia);
end
else
if(@img3 like ' ')
begin
 insert into [dbo].[tblFormFotocopia] (fecha,cantidad,tipoDocuento,img1,img2,materia)
 values (@fecha,@cantidad,@tipoDocuento,@img1,@img2,@materia);
end;
else
begin
 insert into [dbo].[tblFormFotocopia] (fecha,cantidad,tipoDocuento,img1,img2,img3,materia)
 values (@fecha,@cantidad,@tipoDocuento,@img1,@img2,@img3,@materia);
end;
insert into [dbo].[vistaFormularios](fkCuenta,fkTbl,tipo,nombre)
	 values(@idCuenta,(select MAX(id) from tblFormFotocopia),3,'Form. Fotocopia');
END;
if object_id('[dbo].[pr_getFormularioFotocopia]') is not null
  drop procedure [dbo].[pr_getFormularioFotocopia];
GO
create procedure [dbo].[pr_getFormularioFotocopia]
@id int
 as
 BEGIN
	SELECT * FROM [dbo].[tblFormFotocopia] where id=@id;
 END;

GO
if object_id('[dbo].[pr_getFormularioFotocopiaAllID]') is not null
  drop procedure [dbo].[pr_getFormularioFotocopiaAllID];
GO
create procedure [dbo].[pr_getFormularioFotocopiaAllID]
@id int
 as
 BEGIN
	SELECT tbp.id,tbp.fecha,tbp.cantidad,tbp.tipoDocuento,tbp.img1,tbp.img2,tbp.img3,tbp.materia,tbp.Imagen FROM 
	[dbo].[tblFormFotocopia] tbp join [dbo].[vistaFormularios]  tbv on tbv.fkTbl=tbp.id
	where tbv.tipo=3 and (tbv.estado=1 or tbv.estado=0) and tbv.fkCuenta=@id;
 END;
go
DELETE FROM [dbo].[tblVersion]
GO
INSERT INTO [dbo].[tblVersion]
           ([versionMayor]
           ,[versionMenor]
           ,[patch])
     VALUES
           (1
           ,1
           ,8)
GO
ALTER TABLE tblFormFotocopia drop column codigo;
GO
ALTER TABLE tblFormFotocopia ADD img1 text null;
GO
ALTER TABLE tblFormFotocopia ADD img2 text null;
GO
ALTER TABLE tblFormFotocopia ADD img3 text null;
GO
ALTER TABLE tblFormFotocopia ADD materia text null;
GO
ALTER TABLE tblFormFotocopia ADD Imagen varchar(Max) null;
go
if object_id('[dbo].[ActualizarPersonaCuentaAdmin]') is not null
	drop procedure [dbo].[ActualizarPersonaCuentaAdmin];
go
create PROCEDURE [dbo].[ActualizarPersonaCuentaAdmin] 
	@nombre varchar(40),
	@apellidoP varchar(40),
	@apellidoM varchar(40),
	@correo varchar(50),
	@telefono int,
	@fecha date,
	@nombreCuenta varchar(45),
	@password varchar(45),
	@idPersona int,
	@idCuenta int
	AS
BEGIN

UPDATE [dbo].[tblPersona]
   SET [nombre] =  @nombre
      ,[apellidoP] = @apellidoP
      ,[apellidoM] = @apellidoM
      ,[correo] = @correo
      ,[telefono] = @telefono
 WHERE [id]=@idPersona



UPDATE [dbo].[tblCuenta]
   SET [nombreCuenta] = @nombreCuenta
      ,[contracena] = @password
      ,[tipo] = 3
      ,[estado] = 1
 WHERE [id]=@idCuenta

END
go
if object_id('[dbo].[EiminarPersonaCuentaAdmin]') is not null
	drop procedure [dbo].[EiminarPersonaCuentaAdmin];
go
CREATE PROCEDURE [dbo].[EiminarPersonaCuentaAdmin]
	@idInvitacion int,
	@idPersona int,
	@idCuenta int
	AS
BEGIN


DELETE FROM [dbo].[tblCuenta]
      WHERE @idCuenta=[id]


DELETE FROM [dbo].[tblPersona]
      WHERE [id]=@idPersona

DELETE FROM [dbo].[tblInvitacion]
      WHERE [id]=@idInvitacion

END
go

if object_id('[dbo].[EiminarPersonaCuentaAdmin]') is not null
	drop procedure [dbo].[EiminarPersonaCuentaAdmin];
go
CREATE PROCEDURE [dbo].[EiminarPersonaCuentaAdmin]
	@idInvitacion int,
	@idPersona int,
	@idCuenta int
	AS
BEGIN


DELETE FROM [dbo].[tblCuenta]
      WHERE @idCuenta=[id]


DELETE FROM [dbo].[tblPersona]
      WHERE [id]=@idPersona

DELETE FROM [dbo].[tblInvitacion]
      WHERE [id]=@idInvitacion

END
go
if object_id('[dbo].[GetAdminFotocopia]') is not null
	drop procedure [dbo].[GetAdminFotocopia];
go
CREATE PROCEDURE [dbo].[GetAdminFotocopia]
AS
	SET NOCOUNT ON;
SELECT        tblCuenta.id, tblCuenta.nombreCuenta, tblCuenta.contracena, tblCuenta.tipo, tblCuenta.estado, tblInvitacion.id AS InvitacionID, tblInvitacion.token, tblInvitacion.fechaInvitacion, tblInvitacion.fechaRespuesta, 
                         tblInvitacion.estado AS estadoInvitacion, tblInvitacion.fkCuenta, tblInvitacion.fkPersona, tblPersona.id AS PersonaID, tblPersona.nombre, tblPersona.apellidoP, tblPersona.apellidoM, tblPersona.correo, tblPersona.telefono
FROM            tblCuenta INNER JOIN
                         tblInvitacion ON tblCuenta.id = tblInvitacion.fkCuenta AND tblCuenta.tipo = 3 INNER JOIN
                         tblPersona ON tblInvitacion.fkPersona = tblPersona.id and tblPersona.tipo <> -1

if object_id('[dbo].[GetClasesFuera]') is not null
	drop procedure [dbo].[GetClasesFuera];
go
create PROCEDURE [dbo].[GetClasesFuera] 
AS
	SET NOCOUNT ON;
SELECT        vistaFormularios.id, vistaFormularios.fkCuenta, vistaFormularios.fkTbl, vistaFormularios.estado, vistaFormularios.fecha, vistaFormularios.autorizador, vistaFormularios.nombre, vistaFormularios.tipo, 
                         tblFormClasesFuera.id AS Expr1, tblFormClasesFuera.materia, tblFormClasesFuera.grupo, tblFormClasesFuera.motivoActividad, tblFormClasesFuera.fechaActividad, tblFormClasesFuera.descripActividad, 
                         tblFormClasesFuera.lugarActividad, tblFormClasesFuera.fecha AS Expr2, tblCuenta.id AS Expr3, tblCuenta.nombreCuenta, tblCuenta.contracena, tblCuenta.tipo AS Expr4, tblCuenta.estado AS Expr5, tblInvitacion.id AS Expr6, 
                         tblInvitacion.token, tblInvitacion.fechaInvitacion, tblInvitacion.fechaRespuesta, tblInvitacion.estado AS Expr7, tblInvitacion.fkCuenta AS Expr8, tblInvitacion.fkPersona, tblPersona.id AS Expr9, tblPersona.nombre AS Expr10, 
                         tblPersona.apellidoP, tblPersona.apellidoM, tblPersona.correo, tblPersona.telefono, tblPersona.tipo AS Expr11
FROM            vistaFormularios INNER JOIN
                         tblFormClasesFuera ON vistaFormularios.fkTbl = tblFormClasesFuera.id AND vistaFormularios.estado IS NULL INNER JOIN
                         tblCuenta ON vistaFormularios.id = tblCuenta.id INNER JOIN
                         tblInvitacion ON tblCuenta.id = tblInvitacion.fkCuenta INNER JOIN
                         tblPersona ON vistaFormularios.id = tblPersona.id


if object_id('[dbo].[GetFotocopia]') is not null
	drop procedure [dbo].[GetFotocopia];
go
create PROCEDURE [dbo].[GetFotocopia] 
AS
	SET NOCOUNT ON;
SELECT        vistaFormularios.id, vistaFormularios.fkCuenta, vistaFormularios.fkTbl, vistaFormularios.estado, vistaFormularios.fecha, vistaFormularios.autorizador, vistaFormularios.nombre, vistaFormularios.tipo, 
                         tblFormFotocopia.id AS Expr1, tblFormFotocopia.fecha AS Expr2, tblFormFotocopia.cantidad, tblFormFotocopia.tipoDocuento, tblFormFotocopia.codigo, tblFormFotocopia.imagen, tblCuenta.id AS Expr3, tblCuenta.nombreCuenta, 
                         tblCuenta.contracena, tblCuenta.tipo AS Expr4, tblCuenta.estado AS Expr5, tblInvitacion.id AS Expr6, tblInvitacion.token, tblInvitacion.fechaInvitacion, tblInvitacion.fechaRespuesta, tblInvitacion.estado AS Expr7, 
                         tblInvitacion.fkCuenta AS Expr8, tblInvitacion.fkPersona, tblPersona.id AS Expr9, tblPersona.nombre AS Expr10, tblPersona.apellidoP, tblPersona.apellidoM, tblPersona.correo, tblPersona.telefono, 
                         tblPersona.tipo AS Expr11
FROM            vistaFormularios INNER JOIN
                         tblFormFotocopia ON vistaFormularios.fkTbl = tblFormFotocopia.id AND vistaFormularios.estado IS NULL INNER JOIN
                         tblCuenta ON vistaFormularios.id = tblCuenta.id INNER JOIN
                         tblInvitacion ON tblCuenta.id = tblInvitacion.fkCuenta INNER JOIN
                         tblPersona ON vistaFormularios.id = tblPersona.id
if object_id('[dbo].[GetReprogramacion]') is not null
	drop procedure [dbo].[GetReprogramacion];
go
create PROCEDURE [dbo].[GetReprogramacion] 
AS
	SET NOCOUNT ON;

SELECT        vistaFormularios.id, vistaFormularios.fkCuenta, vistaFormularios.fkTbl, vistaFormularios.estado, vistaFormularios.fecha, vistaFormularios.autorizador, vistaFormularios.nombre, vistaFormularios.tipo, 
                         tblFormReprogramacion.id AS Expr1, tblFormReprogramacion.carrera, tblFormReprogramacion.materia, tblFormReprogramacion.grupo, tblFormReprogramacion.modalidad, tblFormReprogramacion.horaI, 
                         tblFormReprogramacion.horaF, tblFormReprogramacion.dias, tblFormReprogramacion.motivoSolicitud, tblFormReprogramacion.fechaActividad, tblFormReprogramacion.fecha AS Expr2, tblCuenta.id AS Expr3, 
                         tblCuenta.nombreCuenta, tblCuenta.contracena, tblCuenta.tipo AS Expr4, tblCuenta.estado AS Expr5, tblInvitacion.id AS Expr6, tblInvitacion.token, tblInvitacion.fechaInvitacion, tblInvitacion.fechaRespuesta, 
                         tblInvitacion.estado AS Expr7, tblInvitacion.fkCuenta AS Expr8, tblInvitacion.fkPersona, tblPersona.id AS Expr9, tblPersona.nombre AS Expr10, tblPersona.apellidoP, tblPersona.apellidoM, tblPersona.correo, tblPersona.telefono, 
                         tblPersona.tipo AS Expr11
FROM            vistaFormularios INNER JOIN
                         tblFormReprogramacion ON vistaFormularios.fkTbl = tblFormReprogramacion.id AND vistaFormularios.estado IS NULL INNER JOIN
                         tblCuenta ON vistaFormularios.id = tblCuenta.id INNER JOIN
                         tblInvitacion ON tblCuenta.id = tblInvitacion.fkCuenta INNER JOIN
                         tblPersona ON vistaFormularios.id = tblPersona.id							

if object_id('[dbo].[PersonaCuentaAdmin]') is not null
	drop procedure [dbo].[PersonaCuentaAdmin];
go
create PROCEDURE [dbo].[PersonaCuentaAdmin] 
    @nombre varchar(40),
	@apellidoP varchar(40),
	@apellidoM varchar(40),
	@correo varchar(50),
	@telefono int,
	@fecha date,
	@nombreCuenta varchar(45),
	@password varchar(45)
	AS
BEGIN
INSERT INTO [dbo].[tblPersona]
           ([nombre]
           ,[apellidoP]
           ,[apellidoM]
           ,[correo]
           ,[telefono]
		   ,[tipo])
     VALUES
           (@nombre
           ,@apellidoP
           ,@apellidoM
           ,@correo
           ,@telefono
		   ,3)

declare @idPersona int
select @idPersona = scope_identity();

INSERT INTO [dbo].[tblCuenta]
           ([nombreCuenta]
           ,[contracena]
           ,[tipo]
           ,[estado])
     VALUES
           (@nombreCuenta
           ,@password
           ,3
           ,1)

declare @idCuenta int
select @idCuenta = scope_identity();

INSERT INTO [dbo].[tblInvitacion]
           ([token]
           ,[fechaInvitacion]
           ,[fechaRespuesta]
           ,[estado]
           ,[fkCuenta]
           ,[fkPersona])
     VALUES
           ('admin'
           ,@fecha
           ,@fecha
           ,1
           ,@idCuenta
           ,@idPersona)

END
if object_id('[dbo].[GetPeticionesIncidentes]') is not null
	drop procedure [dbo].[GetPeticionesIncidentes];
go

	SET NOCOUNT ON;
SELECT        vistaFormularios.id, vistaFormularios.fkCuenta, vistaFormularios.fkTbl, vistaFormularios.estado, vistaFormularios.fecha, vistaFormularios.autorizador, vistaFormularios.nombre, vistaFormularios.tipo, 
                         tblFormAccidente.id AS IDFormInci, tblFormAccidente.descripcion, tblFormAccidente.fecha AS fechaInci, tblPersona.id AS IDPersona, tblPersona.nombre AS NombrePersona, tblPersona.apellidoP, tblPersona.apellidoM, 
                         tblPersona.correo, tblPersona.telefono, tblPersona.tipo AS PersonaTipo
FROM            vistaFormularios INNER JOIN
                         tblFormAccidente ON vistaFormularios.fkTbl = tblFormAccidente.id AND vistaFormularios.estado IS NULL INNER JOIN
                         tblCuenta ON vistaFormularios.id = tblCuenta.id INNER JOIN
                         tblInvitacion ON tblCuenta.id = tblInvitacion.fkCuenta INNER JOIN
                         tblPersona ON vistaFormularios.id = tblPersona.id;


