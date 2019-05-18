GO
use [Formularios]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[p_verificacionCodigo]') AND type in (N'P', N'PC'))
drop Function [dbo].[p_verificacionCodigo];
go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[p_verificacionUsuarios]') AND type in (N'P', N'PC'))
drop Function [dbo].[p_verificacionUsuarios];
go
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_verificacionCodigo]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[pr_verificacionCodigo]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_verificacionUsuarios]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[pr_verificacionUsuarios]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_creacionCuenta]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[pr_creacionCuenta]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_actualizarInvitacion]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[pr_actualizarInvitacion]
GO
GO
-- =============================================
-- Author:luis Solares
-- Create date: 15/05/2019
-- Description:	creacion PR de verficacion de codigo
-- =============================================
CREATE PROCEDURE [dbo].[pr_verificacionCodigo] 
	-- Add the parameters for the stored procedure here
	@p_token text
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
select inv.id as invitacion, concat(per.nombre,' ',per.apellidoP) AS nombre, per.correo as correo, inv.estado as estado 
from tblInvitacion inv join tblPersona per on per.id=inv.fkPersona
where inv.token like @p_token and inv.estado=0;
END  

GO
-- =============================================
-- Author:luis Solares
-- Create date: 15/05/2019
-- Description:	creacion PR de verficacion de Cuenta
-- =============================================
CREATE PROCEDURE [dbo].[pr_verificacionUsuarios]
	-- Add the parameters for the stored procedure here
	@p_usuario varchar (45),
	@p_contra varchar (30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	select * from tblCuenta tblC
    where tblC.nombreCuenta=@p_usuario and tblC.contracena =(select [dbo].[p_verificacionLogin](@p_contra)) and tipo=2 or tipo=3;
END
GO
-- =============================================
-- Author:luis Solares
-- Create date: 15/05/2019
-- Description:	creacion PR de Insertar de Cuenta
-- =============================================
create PROCEDURE [dbo].[pr_creacionCuenta] 
	-- Add the parameters for the stored procedure here
	@p_user varchar (45),
	@p_contracena varchar(30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	insert into tblCuenta(nombreCuenta,contracena,tipo,estado) 
	values (@p_user,(select [dbo].[p_verificacionLogin](@p_contracena)),2,1);
END
GO
-- =============================================
-- Author:luis Solares
-- Create date: 15/05/2019
-- Description:	creacion PR de actualizar Invitacion
-- =============================================
create PROCEDURE [dbo].[pr_actualizarInvitacion] 
	-- Add the parameters for the stored procedure here
	@id_token int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	UPDATE tblInvitacion SET 
	fkCuenta = (select max(id) from tblCuenta), 
	fechaRespuesta = getDate(),
	estado = 1 
	WHERE id = @id_token;
END
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
           ,1)
GO

