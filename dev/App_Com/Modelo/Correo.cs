using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Net.Mail;
/// <summary>
/// Descripción breve de Correo
/// </summary>
public class Correo
{
    Boolean control = true;
    String mensaje = "";

    public Correo(String Destino, String Asunto, String Mensaje)
    {
        MailMessage correo = new MailMessage();
        SmtpClient protocolo = new SmtpClient();

        correo.To.Add(Destino);
        correo.From = new MailAddress("formulariosemc@gmail.com", "Sala Docente");
        correo.Subject = Asunto;
        correo.SubjectEncoding = System.Text.Encoding.UTF8;
        correo.Body = Mensaje;
        correo.BodyEncoding = System.Text.Encoding.UTF8;
        correo.IsBodyHtml = false;

        protocolo.Credentials = new System.Net.NetworkCredential("formulariosemc@gmail.com", "reputacion");
        protocolo.Port = 587;
        protocolo.Host = "smtp.gmail.com";
        protocolo.EnableSsl = true;

        try
        {
            protocolo.Send(correo);
        }
        catch (SmtpException error)
        {
            mensaje = error.Message.ToString();
            control = false;
        }
    }
    public Boolean error
    {
        get { return control; }
    }

    public String tipoError
    {
        get { return mensaje; }
    }
}