using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;
namespace App.Utilities
{
    /// <summary>
    /// Descripción breve de VersionUtilities
    /// </summary>
    public class VersionUtilities
    {
        public VersionUtilities()
        {
            //
            // TODO: Agregar aquí la lógica del constructor
            //
        }
        public static Version GetAppVersion()
        {
            int versionMayor = -1;
            int versionMenor = -1;
            int pach = -1;

            try
            {
                versionMayor = String.IsNullOrEmpty(ConfigurationManager.AppSettings["versionMayor"]) ?
                    -1 : Convert.ToInt32(ConfigurationManager.AppSettings["versionMayor"]);
                versionMenor = String.IsNullOrEmpty(ConfigurationManager.AppSettings["versionMenor"]) ?
                    -1 : Convert.ToInt32(ConfigurationManager.AppSettings["versionMenor"]);
                pach = String.IsNullOrEmpty(ConfigurationManager.AppSettings["patch"]) ?
                    -1 : Convert.ToInt32(ConfigurationManager.AppSettings["pach"]);
            }
            catch(Exception e)
            {

            }

            if (versionMayor<0|| versionMenor < 0 ||pach<0)
            {
                return null;
            }
            return new Version()
            {
                versionMayor=versionMayor,
                versionMenor=versionMenor,
                patch=pach
            };
        }
        public static bool VerificarVersion()
        {
            Version dbVersion = null;
            Version appVersion = null;
            try
            {
                dbVersion = VersionBRL.GetDataBaseVersion();
                appVersion = VersionUtilities.GetAppVersion();
            }
            catch (Exception e)
            {

            }
            if (dbVersion == null || appVersion == null)
            {
                throw new Exception("El Sistema No esta Correctamente Configurado");
            }
            return dbVersion.versionMayor == appVersion.versionMayor &&
                dbVersion.versionMenor == appVersion.versionMenor;
        }
    }
}