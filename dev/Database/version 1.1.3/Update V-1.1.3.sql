USE [Formularios]
GO
/****** Object:  StoredProcedure [dbo].[pr_verificacionCodigo]    Script Date: 20/05/2019 14:06:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[pr_verificacionCodigo] 
	-- Add the parameters for the stored procedure here
	@p_token text
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
select inv.id as invitacion, concat(per.nombre,' ',per.apellidoP) AS nombre, per.correo as correo, inv.estado as estado, inv.fkCuenta as cuenta
from tblInvitacion inv join tblPersona per on per.id=inv.fkPersona
where inv.token like @p_token and inv.estado=0;
END  
GO
-- =============================================
-- Author:luis Solares
-- Create date: 20/05/2019
-- Description:	creacion PR de verificacion de correo por correo
-- =============================================
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_verificacionCorreo]') AND type in (N'P', N'PC'))
drop procedure [dbo].[pr_verificacionCorreo];
GO
CREATE PROCEDURE pr_verificacionCorreo
@p_correo varchar(50)
as
begin
select inv.fkCuenta as cuenta from tblInvitacion inv  join tblPersona  per on inv.fkPersona=per.id
join tblCuenta Cue on inv.fkCuenta=Cue.id
where per.correo=@p_correo and inv.estado=1 and Cue.tipo=2;
end;
GO
-- =============================================
-- Author:luis Solares
-- Create date: 20/05/2019
-- Description:	creacion PR de creacion y actualizacion de cuenta por id y de la invitacion
-- =============================================
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_actualizacionCorreoInvitacion]') AND type in (N'P', N'PC'))
drop procedure [dbo].[pr_actualizacionCorreoInvitacion];
GO
CREATE PROCEDURE pr_actualizacionCorreoInvitacion
@p_idCuenta int,
@p_nombreCuenta text,
@p_contrasena varchar(30)
as
begin
UPDATE tblCuenta SET 
	nombreCuenta=@p_nombreCuenta,
	contracena = ([dbo].[p_verificacionLogin](@p_contrasena))
	WHERE id = @p_idCuenta;
UPDATE tblInvitacion SET 
	fechaRespuesta = getDate(),
	estado = 1 
	WHERE fkCuenta=@p_idCuenta;
end;

GO
-- =============================================
-- Author:luis Solares
-- Create date: 20/05/2019
-- Description:	creacion PR desactivacion de invitacion
-- =============================================
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_desactivacionInvitacion]') AND type in (N'P', N'PC'))
drop procedure [dbo].[pr_desactivacionInvitacion];
GO
CREATE PROCEDURE pr_desactivacionInvitacion
@p_idCuenta int,
@p_token text
as
begin
UPDATE tblInvitacion SET 
	estado = 0,
	token=@p_token
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
           ,3)
GO