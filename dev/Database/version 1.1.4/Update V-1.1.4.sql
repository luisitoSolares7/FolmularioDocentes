USE [Formularios]
GO
/****** Object:  StoredProcedure [dbo].[pr_desactivacionInvitacion]    Script Date: 21/05/2019 14:24:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[pr_desactivacionInvitacion]
@p_idCuenta int,
@p_token text
as
begin
UPDATE tblInvitacion SET 
	estado = 0,
	token=@p_token
	WHERE fkCuenta=@p_idCuenta;
UPDATE tblCuenta set
	estado=0
	where id=@p_idCuenta;
end;
GO
/****** Object:  StoredProcedure [dbo].[pr_actualizacionCorreoInvitacion]    Script Date: 21/05/2019 14:32:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[pr_actualizacionCorreoInvitacion]
@p_idCuenta int,
@p_nombreCuenta text,
@p_contrasena varchar(30)
as
begin
UPDATE tblCuenta SET 
	nombreCuenta=@p_nombreCuenta,
	estado=1,
	contracena = ([dbo].[p_verificacionLogin](@p_contrasena))
	WHERE id = @p_idCuenta;
UPDATE tblInvitacion SET 
	fechaRespuesta = getDate(),
	estado = 1 
	WHERE fkCuenta=@p_idCuenta;
end;
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
           ,4)
GO