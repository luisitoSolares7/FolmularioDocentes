USE [Formularios]
GO
/****** Object:  StoredProcedure [dbo].[buscarInvitacionID]    Script Date: 19/04/2019 18:50:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Jose Elmer Guzman Pacheco
-- Create date: 15/04/2019
-- Description:	Buscar una Invitacion con su ID
-- =============================================
CREATE PROCEDURE [dbo].[buscarInvitacionID]
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

GO
/****** Object:  StoredProcedure [dbo].[eliminarInvitacion]    Script Date: 19/04/2019 18:50:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Jose Elmer Guzman Pacheco
-- Create date: 19/04/2019
-- Description:	Elimina la invitacion que previamente fue invitado
-- =============================================
CREATE PROCEDURE [dbo].[eliminarInvitacion]
	@idPersona int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

DELETE FROM [dbo].[tblInvitacion]
      WHERE @idPersona=[fkPersona] and [estado]=0

END

GO
/****** Object:  StoredProcedure [dbo].[getPersona]    Script Date: 19/04/2019 18:51:20 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
<<<<<<< HEAD
 CREATE PROCEDURE [dbo].[getPersona]
=======
CREATE PROCEDURE [dbo].[getPersona]
>>>>>>> a603054ee8d222c907053b2750d367dec0c473e7

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



END

GO
/****** Object:  StoredProcedure [dbo].[getPersonaID]    Script Date: 19/04/2019 18:52:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Jose Elmer Guzman Pacheco
-- Create date: 19/04/2019
-- Description:	Elimina la invitacion que previamente fue invitado
-- =============================================
CREATE PROCEDURE [dbo].[getPersonaID]
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
           ,0
           ,1)
GO

