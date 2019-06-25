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


