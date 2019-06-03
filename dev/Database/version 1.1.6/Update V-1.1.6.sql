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
 insert into [dbo].[tblResClasesFuera](fkCuenta,fkClaseFuera,estado)
 values(@idCuenta,(select MAX(id) from tblFormClasesFuera),0);
 END;
GO
if object_id('[dbo].[vistaFormularios]') is not null
  drop view [dbo].[vistaFormularios];
GO
create view vistaFormularios as
select id,fkCuenta,fkAccidente as fkTbl,estado,fecha,autorizador, 'Formulario Accidente' as nombre,0 as tipo from tblResAccidente
union
select id,fkCuenta,fkClaseFuera as fkTbl,estado,fecha,autorizador, 'Formulario Fuera de Clases' as nombre,1 as tipo from tblResClasesFuera
union
select id,fkCuenta,fkReprogramacion as fkTbl,estado,fecha,autorizador,'Formulario Reprogramacion' as nombre,2 as tipo from tblResReprogramacion
Go
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