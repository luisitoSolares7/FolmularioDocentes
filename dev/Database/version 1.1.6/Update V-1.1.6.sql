use Formularios;
GO
ALTER TABLE [dbo].[tblFormAccidente] DROP COLUMN foto ;
GO
ALTER TABLE [dbo].[tblFormAccidente] DROP COLUMN fecha ;
GO
ALTER TABLE [dbo].[tblFormAccidente] ADD fecha  DateTime null;
GO
ALTER TABLE [dbo].[tblFormClasesFuera] DROP COLUMN fecha ;
GO
ALTER TABLE [dbo].[tblFormClasesFuera] ADD fecha  DateTime null;
GO
ALTER TABLE [dbo].[tblFormClasesFuera] ADD materia  text null;
GO
ALTER TABLE [dbo].[tblFormClasesFuera] ADD grupo  text null;
GO
ALTER TABLE [dbo].[tblFormClasesFuera] ADD motivoActividad  text null;
GO
ALTER TABLE [dbo].[tblFormClasesFuera] ADD fechaActividad  DateTime null;
GO
ALTER TABLE [dbo].[tblFormClasesFuera] ADD descripActividad  text null;
GO
ALTER TABLE [dbo].[tblFormClasesFuera] ADD lugarActividad  text null;
GO
ALTER TABLE [dbo].[tblResAccidente] DROP COLUMN fecha;
GO
ALTER TABLE [dbo].[tblResAccidente] ADD fecha DateTime null;
GO
ALTER TABLE [dbo].[tblResAccidente] ADD autorizador int null;
GO
ALTER TABLE [dbo].[tblResClasesFuera] DROP COLUMN fecha;
GO
ALTER TABLE [dbo].[tblResClasesFuera] ADD fecha DateTime null;
GO
ALTER TABLE [dbo].[tblResClasesFuera] ADD autorizador int null;
GO
ALTER TABLE [dbo].[tblResReprogramacion] DROP COLUMN fecha;
GO
ALTER TABLE [dbo].[tblResReprogramacion] ADD fecha DateTime null;
GO
ALTER TABLE [dbo].[tblResReprogramacion] ADD autorizador int null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] DROP COLUMN fecha;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD carrera  text null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD fecha  Date null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD materia  text null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD grupo  text null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD modalidad  text null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD horaI  Time null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD horaF  Time null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD dias  text null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD motivoSolicitud  text null;
GO
ALTER TABLE [dbo].[tblFormReprogramacion] ADD fechaActividad  Date null;
GO
create table vistaFormularios(
id int IDENTITY primary key,
fkCuenta int,
fkTbl int,
estado bit,
fecha datetime,
autorizador int,
nombre text,
tipo int
);
GO
if object_id('[dbo].[pr_insertarFormClasesF]') is not null
  drop procedure [dbo].[pr_insertarFormClasesF];
GO
create procedure [dbo].[pr_insertarFormClasesF]
@idCuenta int,
@fecha DateTime,
@materia text,
@grupo text,
@motivoActividad text,
@fechaActividad DateTime,
@descripActividad text,
@lugarActividad text
 as
 BEGIN
 insert into [dbo].[tblFormClasesFuera] (fecha,materia,grupo,motivoActividad,fechaActividad,descripActividad,lugarActividad)
 values (@fecha,@materia,@grupo,@motivoActividad,@fechaActividad,@descripActividad,@lugarActividad);
insert into [dbo].[vistaFormularios](fkCuenta,fkTbl,estado,tipo,nombre)
 values(@idCuenta,(select MAX(id) from tblFormClasesFuera),0,1,'Form. Fuera de Clases');
 END;
GO
if object_id('[dbo].[pr_getVistaFormularios]') is not null
  drop procedure [dbo].[pr_getVistaFormularios];
GO
create procedure [dbo].[pr_getVistaFormularios]
@idCuenta int
 as
 BEGIN
 select * from [dbo].[vistaFormularios] where fkCuenta=@idCuenta order by id desc;
 END;
GO
if object_id('[dbo].[pr_getFormularioFueraC]') is not null
  drop procedure [dbo].[pr_getFormularioFueraC];
GO
create procedure [dbo].[pr_getFormularioFueraC]
@id int
 as
 BEGIN
	SELECT * FROM [dbo].[tblFormClasesFuera] where id=@id;
 END;
GO
GO
IF object_id('[dbo].[pr_insertarFormAccidente]') is not null
  drop procedure [dbo].[pr_insertarFormAccidente];
GO
create procedure [dbo].[pr_insertarFormAccidente]
@idCuenta int,
@fecha DateTime,
@descripActividad text
 as
 BEGIN
 insert into [dbo].[tblFormAccidente] (descripcion,fecha)
 values (@descripActividad,@fecha);
insert into [dbo].[vistaFormularios](fkCuenta,fkTbl,estado,tipo,nombre)
 values(@idCuenta,(select MAX(id) from tblFormAccidente),0,0,'Form. de Incidente');
 END;
GO
if object_id('[dbo].[pr_getFormularioAccidente]') is not null
  drop procedure [dbo].[pr_getFormularioAccidente];
GO
create procedure [dbo].[pr_getFormularioAccidente]
@id int
 as
 BEGIN
	SELECT * FROM [dbo].[tblFormAccidente] where id=@id;
 END;
GO
if object_id('[dbo].[pr_getFormularioReprogramacion]') is not null
  drop procedure [dbo].[pr_getFormularioReprogramacion];
GO
create procedure [dbo].[pr_getFormularioReprogramacion]
@id int
 as
 BEGIN
	SELECT * FROM [dbo].[tblFormReprogramacion] where id=@id;
 END;
GO
IF object_id('[dbo].[pr_insertarFormReprogramacion]') is not null
  drop procedure [dbo].[pr_insertarFormReprogramacion];
GO
create procedure [dbo].[pr_insertarFormReprogramacion]
@idCuenta int,
@carrera text,
@materia text,
@grupo text,
@modalidad text,
@horaI Time,
@horaF Time,
@dias text,
@motivoSolicitud text,
@fecha date,
@fechaSolicitud date
 as
 BEGIN
 insert into [dbo].[tblFormReprogramacion] (carrera,materia,grupo,modalidad,horaI,horaF,dias,motivoSolicitud,fechaActividad,fecha)
 values (@carrera,@materia,@grupo,@modalidad,@horaI,@horaF,@dias,@motivoSolicitud,@fechaSolicitud,@fecha);
 insert into [dbo].[vistaFormularios](fkCuenta,fkTbl,estado,tipo,nombre)
 values(@idCuenta,(select MAX(id) from tblFormReprogramacion),0,2,'Form. de Reprogramacion');
 END;
GO
DELETE FROM [dbo].[tblVersion]
GO
INSERT INTO [dbo].[tblVersion]
           ([versionMayor]
           ,[versionMenor]
           ,[patch])
     VALUES
           (1
           ,1
           ,6)
GO