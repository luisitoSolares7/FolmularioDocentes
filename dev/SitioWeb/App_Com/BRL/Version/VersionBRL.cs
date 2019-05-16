using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de VersionBRL
/// </summary>
public class VersionBRL
{
    public VersionBRL()
    {
        //
        // TODO: Agregar aquí la lógica del constructor
        //
    }
    public static Version GetDataBaseVersion()
    {
        App_Com.DAL.Version.VersionDSTableAdapters.VersionDataTableAdapter adapter =
            new App_Com.DAL.Version.VersionDSTableAdapters.VersionDataTableAdapter();

        int? versionMayor = null;
        int? versionMenor = null;
        int? patch = null;

        adapter.GetVersionData(ref  versionMayor,ref versionMenor, ref patch);

        if (versionMayor==null || versionMenor==null || patch==null)
        {
            return null;
        }
        return new Version
        {
            versionMayor = versionMayor.Value,
            versionMenor = versionMenor.Value,
            patch = patch.Value
        };
    }
}