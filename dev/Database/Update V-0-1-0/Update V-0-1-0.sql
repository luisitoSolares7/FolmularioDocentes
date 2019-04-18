USE [formularios]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tblVersion](
	[versionMayor] [int] NOT NULL,
	[versionMenor] [int] NULL,
	[patch] [int] NULL
) ON [PRIMARY]
GO

/****** Object:  StoredProcedure [dbo].[getVersionData]    Script Date: 18/04/2019 2:15:00 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		Jose Elmer Guzman Pacheco
-- Create date: 16/04/2019
-- Description:	Version de la base de datos Formularios
-- =============================================
CREATE PROCEDURE  [dbo].[getVersionData]
	@versionMayor int output,
	@versionMenor int output,
	@patch int output
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

SELECT top 1 @versionMayor=[versionMayor]
      ,@versionMenor=[versionMenor]
      ,@patch=[patch]
  FROM [dbo].[tblVersion]

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
           ,0)
GO



