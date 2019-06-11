use Formularios;
go
if object_id('[dbo].[AceptarPeticion]') is not null
	drop procedure [dbo].[AceptarPeticion];
go
create PROCEDURE [dbo].[AceptarPeticion] 
@id int,
@fecha Datetime,
@fkAdmin int
AS
BEGIN


UPDATE [dbo].[vistaFormularios]
   SET 
      [estado] = 0
      ,[fecha] = @fecha
      ,[autorizador] = @fkAdmin
 WHERE [id]=@id
 end
go
if object_id('[dbo].[BuscarFormularioVista]') is not null
	drop procedure [dbo].[BuscarFormularioVista];
go
create  PROCEDURE [dbo].[BuscarFormularioVista] 
@fkFormulario int
AS
BEGIN

SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [fkTbl]=@fkFormulario
END

go
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
           ,[telefono])
     VALUES
           (@nombre
           ,@apellidoP
           ,@apellidoM
           ,@correo
           ,@telefono)

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
           ,0
           ,@idCuenta
           ,@idPersona)

END

go
if object_id('[dbo].[insertarPersona]') is not null
	drop procedure [dbo].[insertarPersona];
go
create PROCEDURE [dbo].[insertarPersona]
	@nombre varchar(40),
	@apellidoP varchar(40),
	@apellidoM varchar(40),
	@telefono int,
	@correo varchar(50)
AS
BEGIN

INSERT INTO [dbo].[tblPersona]
           ([nombre]
           ,[apellidoP]
           ,[apellidoM]
           ,[correo]
           ,[telefono])
     VALUES
           (@nombre
           ,@apellidoP
           ,@apellidoM
           ,@correo
           ,@telefono);
END

go
if object_id('[dbo].[insertarCuenta]') is not null
	drop procedure [dbo].[insertarCuenta];
go

create PROCEDURE [dbo].[insertarCuenta] 
	@nombreCuenta varchar(45),
	@contracena text,
	@tipo int
AS
BEGIN

	SET NOCOUNT ON;
INSERT INTO [dbo].[tblCuenta]
           ([nombreCuenta]
           ,[contracena]
           ,[tipo]
           ,[estado])
     VALUES
           (@nombreCuenta
           ,@contracena
           ,@tipo
           ,1)
END

go
if object_id('[dbo].[getPersonaID]') is not null
	drop procedure [dbo].[getPersonaID];
go

create PROCEDURE [dbo].[getPersonaID]
	@idPersona int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

SELECT [id]
      ,[nombre]
      ,[apellidoP]
      ,[apellidoM]
      ,[correo]
      ,[telefono]
  FROM [dbo].[tblPersona]
  where [id]=@idPersona
END

go
if object_id('[dbo].[fkInvitacionCuenta]') is not null
	drop procedure [dbo].[fkInvitacionCuenta];
go
create PROCEDURE [dbo].[fkInvitacionCuenta]
	@fkCuenta int
	AS
BEGIN


SELECT [id]
      ,[token]
      ,[fechaInvitacion]
      ,[fechaRespuesta]
      ,[estado]
      ,[fkCuenta]
      ,[fkPersona]
  FROM [dbo].[tblInvitacion]
  where @fkCuenta=[fkCuenta]
END

go
if object_id('[dbo].[fkInvitacionAdmin]') is not null
	drop procedure [dbo].[fkInvitacionAdmin];
go

create PROCEDURE [dbo].[fkInvitacionAdmin]
	@fkPersona int
	AS
BEGIN


SELECT [id]
      ,[token]
      ,[fechaInvitacion]
      ,[fechaRespuesta]
      ,[estado]
      ,[fkCuenta]
      ,[fkPersona]
  FROM [dbo].[tblInvitacion]
  where @fkPersona=[fkPersona]
END

go
if object_id('[dbo].[eliminarPersona]') is not null
	drop procedure [dbo].[eliminarPersona];
go
create PROCEDURE [dbo].[eliminarPersona]
	@id int
AS
BEGIN


DELETE FROM [tblPersona]
      WHERE @id = [id]

END

go
if object_id('[dbo].[eliminarInvitacion]') is not null
	drop procedure [dbo].[eliminarInvitacion];
go
create PROCEDURE [dbo].[eliminarInvitacion]
	@idPersona int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

DELETE FROM [dbo].[tblInvitacion]
      WHERE @idPersona=[fkPersona] and [estado]=0

END

go
if object_id('[dbo].[EiminarPersonaCuentaAdmin]') is not null
	drop procedure [dbo].[EiminarPersonaCuentaAdmin];
go
create PROCEDURE [dbo].[EiminarPersonaCuentaAdmin]
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
if object_id('[dbo].[buscarInvitacionID]') is not null
	drop procedure [dbo].[buscarInvitacionID];
go
create PROCEDURE [dbo].[buscarInvitacionID]
	@idInvitacion int
AS
BEGIN
	SET NOCOUNT ON;

SELECT [id]
      ,[token]
      ,[fechaInvitacion]
      ,[fechaRespuesta]
      ,[estado]
      ,[fkCuenta]
      ,[fkPersona]
  FROM [dbo].[tblInvitacion]
  WHERE [id]=@idInvitacion
END

go
if object_id('[dbo].[GetPeticionesReprogramacionP]') is not null
	drop procedure [dbo].[GetPeticionesReprogramacionP];
go
create PROCEDURE [dbo].[GetPeticionesReprogramacionP]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null and [tipo]=4

END

go
if object_id('[dbo].[GetPeticionesIncidentesP]') is not null
	drop procedure [dbo].[GetPeticionesIncidentesP];
go
create PROCEDURE [dbo].[GetPeticionesIncidentesP]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null and [tipo]=1

END
go
if object_id('[dbo].[GetPeticionesFotocopiaP]') is not null
	drop procedure [dbo].[GetPeticionesFotocopiaP];
go
create PROCEDURE [dbo].[GetPeticionesFotocopiaP]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null and [tipo]=3

END
go
if object_id('[dbo].[GetPeticionesClasesFueraP]') is not null
	drop procedure [dbo].[GetPeticionesClasesFueraP];
go
create PROCEDURE [dbo].[GetPeticionesClasesFueraP]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null and [tipo]=2

END

go
if object_id('[dbo].[GetPeticionesAll]') is not null
	drop procedure [dbo].[GetPeticionesAll];
go
create PROCEDURE [dbo].[GetPeticionesAll]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null

END

go
if object_id('[dbo].[buscarCuenta]') is not null
	drop procedure [dbo].[buscarCuenta];
go
create PROCEDURE [dbo].[buscarCuenta] 
	@id int
AS
BEGIN

	SET NOCOUNT ON;

SELECT [id]
      ,[nombreCuenta]
      ,[contracena]
      ,[tipo]
      ,[estado]
  FROM [dbo].[tblCuenta]
  WHERE @id=[id]

END
go
if object_id('[dbo].[borrarCuenta]') is not null
	drop procedure [dbo].[borrarCuenta];
go
create PROCEDURE [dbo].[borrarCuenta] 
	@id int
AS
BEGIN

	SET NOCOUNT ON;

DELETE FROM [dbo].[tblCuenta]
      WHERE @id=[id]

END

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
if object_id('[dbo].[ActualizarPersona]') is not null
	drop procedure [dbo].[ActualizarPersona];
go
create PROCEDURE [dbo].[ActualizarPersona]
	@nombre varchar(40),
	@apellidoP varchar(40),
	@apellidoM varchar(40),
	@correo varchar(50),
	@telefono int,
	@id int
AS
BEGIN

UPDATE [dbo].[tblPersona]
   SET [nombre] = @nombre
      ,[apellidoP] = @apellidoP
      ,[apellidoM] = @apellidoM
      ,[correo] = @correo
      ,[telefono] = @telefono
 WHERE [id]=@id
END

go
if object_id('[dbo].[actualizarCuenta]') is not null
	drop procedure [dbo].[actualizarCuenta];
go
create PROCEDURE [dbo].[actualizarCuenta] 
	@nombreCuenta varchar(45),
	@contracena text,
	@estado int,
	@id int
AS
BEGIN

	SET NOCOUNT ON;


UPDATE [dbo].[tblCuenta]
   SET [nombreCuenta] = @nombreCuenta
      ,[contracena] =@contracena
      ,[estado] = @estado
	   WHERE [id]= @id


END
go
if object_id('[dbo].[aceptarInvitacion]') is not null
	drop procedure [dbo].[aceptarInvitacion];
go
create PROCEDURE [dbo].[aceptarInvitacion]
	@fkPersona int,
	@idCuenta int,
	@fecha date
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

UPDATE [dbo].[tblInvitacion]
   SET 
      [fechaRespuesta] = @fecha
      ,[estado] = 1
      ,[fkCuenta] = @idCuenta
 WHERE [fkPersona]=@fkPersona

END
go
if object_id('[dbo].[aceptarInvitacion]') is not null
	drop procedure [dbo].[aceptarInvitacion];
go
create PROCEDURE [dbo].[aceptarInvitacion]
@id int
as
BEGIN


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [id]=@id

END

go
if object_id('[dbo].[GetPeticionID]') is not null
	drop procedure [dbo].[GetPeticionID];
go

create PROCEDURE [dbo].[GetPeticionID]
@id int
as
BEGIN


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [id]=@id

END

go
if object_id('[dbo].[GetPeticionesReprogramacionP]') is not null
	drop procedure [dbo].[GetPeticionesReprogramacionP];
go
create PROCEDURE [dbo].[GetPeticionesReprogramacionP]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null and [tipo]=4

END
go
if object_id('[dbo].[GetPeticionesIncidentesP]') is not null
	drop procedure [dbo].[GetPeticionesIncidentesP];
go
create PROCEDURE [dbo].[GetPeticionesIncidentesP]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null and [tipo]=1

END
go
if object_id('[dbo].[GetPeticionesFotocopiaP]') is not null
	drop procedure [dbo].[GetPeticionesFotocopiaP];
go
create PROCEDURE [dbo].[GetPeticionesFotocopiaP]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null and [tipo]=3

END
go
if object_id('[dbo].[GetPeticionesFotocopiaP]') is not null
	drop procedure [dbo].[GetPeticionesFotocopiaP];
go
create PROCEDURE [dbo].[GetPeticionesClasesFueraP]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null and [tipo]=2

END
go
if object_id('[dbo].[GetPeticionesAll]') is not null
	drop procedure [dbo].[GetPeticionesAll];
go
create PROCEDURE [dbo].[GetPeticionesAll]
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[fkCuenta]
      ,[fkTbl]
      ,[estado]
      ,[fecha]
      ,[autorizador]
      ,[nombre]
      ,[tipo]
  FROM [dbo].[vistaFormularios]
  where [autorizador] is null

END
go
if object_id('[dbo].[GetFormularioID]') is not null
	drop procedure [dbo].[GetFormularioID];
go
create PROCEDURE [dbo].[GetFormularioID]
@id int
as
BEGIN

SELECT [id]
      ,[descripcion]
      ,[fecha]
  FROM [dbo].[tblFormAccidente]
  where [id]=@id

END
go
if object_id('[dbo].[eliminarInvitacion]') is not null
	drop procedure [dbo].[eliminarInvitacion];
go
create PROCEDURE [dbo].[eliminarInvitacion]
	@idPersona int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

DELETE FROM [dbo].[tblInvitacion]
      WHERE @idPersona=[fkPersona] and [estado]=0

END
go
if object_id('[dbo].[loginPersonaAdmin]') is not null
	drop procedure [dbo].[loginPersonaAdmin];
go
create PROCEDURE [dbo].[loginPersonaAdmin]
		@nombreCuenta varchar(40)    
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


SELECT [id]
      ,[nombreCuenta]
      ,[contracena]
      ,[tipo]
      ,[estado]
  FROM [dbo].[tblCuenta]
  where @nombreCuenta = [nombreCuenta]
END


