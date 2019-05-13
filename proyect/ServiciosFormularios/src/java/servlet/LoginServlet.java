/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CuentaDao;
import dao.InvitacionDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cuenta;
import modelo.Invitacion;

/**
 *
 * @author User
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String accion = request.getParameter("accion");
        String contraseña = request.getParameter("contrasena");
        String usuario = request.getParameter("usuario");
        String token = request.getParameter("token");
        try (PrintWriter out = response.getWriter()) {
            if (accion.equals("Login")) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Cuenta docente = new CuentaDao().getAcceso(usuario, contraseña);
                String json = gson.toJson(docente);
                out.println(json);
            }
            if (accion.equals("Codigo")) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Invitacion invitacion = new InvitacionDao().getRegistro(contraseña);
                String json = gson.toJson(invitacion);
                out.println(json);
            }
            if (accion.equals("Cuenta")) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Cuenta cuenta = new CuentaDao().creacionCuenta(usuario,contraseña,token);
                String json = gson.toJson(cuenta);
                out.println(json);
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
