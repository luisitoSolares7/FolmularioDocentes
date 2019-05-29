use Formularios
go
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[usp_PROD_UpdatePersona]') AND type in (N'P', N'PC'))
drop procedure [dbo].[usp_PROD_UpdatePersona];
GO
CREATE PROCEDURE [dbo].[usp_PROD_UpdatePersona]
	@intPersonaId	INT,
	@varnombre		NVARCHAR(250),
	@varapellidoP		NVARCHAR(250),
	@varapellidoM		NVARCHAR(250),
	@varcorreo		NVARCHAR(250),
	@inttelefono		int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	UPDATE [dbo].[tblPersona]
	   SET [nombre] = @varnombre
		  ,[apellidoP] = @varapellidoM
		  ,[apellidoM] = @varapellidoM
		  ,[correo] = @varcorreo
		  ,[telefono] = @inttelefono
	WHERE [id] = @intPersonaId

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
           ,5)
GO