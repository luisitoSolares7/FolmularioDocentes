USE [Formularios]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_verificacionCuenta]') AND type in (N'P', N'PC'))
drop Function [dbo].[p_verificacionUsuarios];
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pr_remplazoContraCuenta]') AND type in (N'P', N'PC'))
drop Function [dbo].[p_verificacionUsuarios];
GO
-- =============================================
-- Author:luis Solares
-- Create date: 18/05/2019
-- Description:	creacion PR de verificacion de Cuenta
-- =============================================
CREATE PROCEDURE [dbo].[pr_verificacionCuenta] 
	-- Add the parameters for the stored procedure here
	@p_id int,
	@p_pass varchar(30)

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	select * from tblCuenta tbC
WHERE tbC.id=@p_id and tbC.contracena=(select [dbo].[p_verificacionLogin](@p_pass))
END
GO
-- =============================================
-- Author:luis Solares
-- Create date: 18/05/2019
-- Description:	creacion PR de remplazoContraCuenta
-- =============================================
CREATE PROCEDURE [dbo].[pr_remplazoContraCuenta] 
	-- Add the parameters for the stored procedure here
	@p_id int,
	@p_pass varchar(30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.

	Update tblCuenta SET 
	contracena = (select [dbo].[p_verificacionLogin](@p_pass))
	WHERE id = @p_id;
END
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
           ,2)
GO