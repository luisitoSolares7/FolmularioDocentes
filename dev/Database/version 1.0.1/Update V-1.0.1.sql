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
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[buscarInvitacionID]') AND type in (N'P', N'PC'))
drop procedure [dbo].[buscarInvitacionID];
GO
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
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[eliminarInvitacion]') AND type in (N'P', N'PC'))
drop procedure [dbo].[eliminarInvitacion];
GO
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
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[getPersona]') AND type in (N'P', N'PC'))
drop procedure [dbo].[getPersona];
GO
CREATE PROCEDURE [dbo].[getPersona]
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
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[getPersonaID]') AND type in (N'P', N'PC'))
drop procedure [dbo].[getPersonaID];
GO
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

