/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Admin;
import Modelo.AdministradorDAO;
import Modelo.Entrenador;
import Modelo.EntrenadorDAO;
import Modelo.Nutricionista;
import Modelo.NutricionistaDAO;
import Modelo.Rutina;
import Modelo.RutinaDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EMERSOM APAZA
 */
public class Controlador extends HttpServlet {

    Admin admin = new Admin();
    AdministradorDAO adminDAO = new AdministradorDAO();
    int ide;
    
    Entrenador ent = new Entrenador();
    EntrenadorDAO entDAO = new EntrenadorDAO();
    
    Nutricionista nut = new Nutricionista();
    NutricionistaDAO nutDAO = new NutricionistaDAO();
    
    Rutina rut = new Rutina();
    RutinaDAO rutDAO = new RutinaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }

        if (menu.equals("Rutina")) {
            switch (accion) {
                case "Listar":
                    List lista = rutDAO.listar();
                    request.setAttribute("rutinas", lista);
                    break;

                case "Agregar":
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String TDoc = request.getParameter("txtTipoDocumento");
                    String NDoc = request.getParameter("txtDocumento");
                    String telefono = request.getParameter("txtTelefono");
                    String correo = request.getParameter("txtCorreo");
                    String fecha = request.getParameter("txtfecha");
                    String fechatermino = request.getParameter("txtfechatermino");
                    String horario = request.getParameter("txthorario");
                    nut.setNombre(nombre);
                    nut.setApellido(apellido);
                    nut.setTipo_doc(TDoc);
                    nut.setNum_doc(NDoc);
                    nut.setTelefono(telefono);
                    nut.setCorreo(correo);
                    nut.setFecha(fecha);
                    nut.setFechaTermino(fechatermino);
                    nut.setHorario(horario);
                    nutDAO.agregar(nut);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Nutricionista n = nutDAO.listarId(ide);
                    request.setAttribute("nutricionista", n);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombre1 = request.getParameter("txtNombre");
                    String apellido1 = request.getParameter("txtApellido");
                    String TDoc1 = request.getParameter("txtTipoDocumento");
                    String NDoc1 = request.getParameter("txtDocumento");
                    String telefono1 = request.getParameter("txtTelefono");
                    String correo1 = request.getParameter("txtCorreo");
                    String fecha1 = request.getParameter("txtfecha");
                    String fechatermino1 = request.getParameter("txtfechatermino");
                    String horario1 = request.getParameter("txthorario");
                    nut.setNombre(nombre1);
                    nut.setApellido(apellido1);
                    nut.setTipo_doc(TDoc1);
                    nut.setNum_doc(NDoc1);
                    nut.setTelefono(telefono1);
                    nut.setCorreo(correo1);
                    nut.setFecha(fecha1);
                    nut.setFechaTermino(fechatermino1);
                    nut.setHorario(horario1);
                    nut.setId(ide);
                    nutDAO.actualizar(nut);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    nutDAO.eliminar(ide);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Rutina.jsp").forward(request, response);
        }
        
        if (menu.equals("Nutricionista")) {
            switch (accion) {
                case "Listar":
                    List lista = nutDAO.listar();
                    request.setAttribute("nutricionistas", lista);
                    break;

                case "Agregar":
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String TDoc = request.getParameter("txtTipoDocumento");
                    String NDoc = request.getParameter("txtDocumento");
                    String telefono = request.getParameter("txtTelefono");
                    String correo = request.getParameter("txtCorreo");
                    String fecha = request.getParameter("txtfecha");
                    String fechatermino = request.getParameter("txtfechatermino");
                    String horario = request.getParameter("txthorario");
                    nut.setNombre(nombre);
                    nut.setApellido(apellido);
                    nut.setTipo_doc(TDoc);
                    nut.setNum_doc(NDoc);
                    nut.setTelefono(telefono);
                    nut.setCorreo(correo);
                    nut.setFecha(fecha);
                    nut.setFechaTermino(fechatermino);
                    nut.setHorario(horario);
                    nutDAO.agregar(nut);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Nutricionista n = nutDAO.listarId(ide);
                    request.setAttribute("nutricionista", n);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombre1 = request.getParameter("txtNombre");
                    String apellido1 = request.getParameter("txtApellido");
                    String TDoc1 = request.getParameter("txtTipoDocumento");
                    String NDoc1 = request.getParameter("txtDocumento");
                    String telefono1 = request.getParameter("txtTelefono");
                    String correo1 = request.getParameter("txtCorreo");
                    String fecha1 = request.getParameter("txtfecha");
                    String fechatermino1 = request.getParameter("txtfechatermino");
                    String horario1 = request.getParameter("txthorario");
                    nut.setNombre(nombre1);
                    nut.setApellido(apellido1);
                    nut.setTipo_doc(TDoc1);
                    nut.setNum_doc(NDoc1);
                    nut.setTelefono(telefono1);
                    nut.setCorreo(correo1);
                    nut.setFecha(fecha1);
                    nut.setFechaTermino(fechatermino1);
                    nut.setHorario(horario1);
                    nut.setId(ide);
                    nutDAO.actualizar(nut);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    nutDAO.eliminar(ide);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Nutricionista.jsp").forward(request, response);
        }
        
        if (menu.equals("Entrenador")) {
            switch (accion) {
                case "Listar":
                    List lista = entDAO.listar();
                    request.setAttribute("entrenadores", lista);
                    break;

                case "Agregar":
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String TDoc = request.getParameter("txtTipoDocumento");
                    String NDoc = request.getParameter("txtDocumento");
                    String telefono = request.getParameter("txtTelefono");
                    String correo = request.getParameter("txtCorreo");
                    String fecha = request.getParameter("txtfecha");
                    String fechatermino = request.getParameter("txtfechatermino");
                    String horario = request.getParameter("txthorario");
                    ent.setNombre(nombre);
                    ent.setApellido(apellido);
                    ent.setTipo_doc(TDoc);
                    ent.setNum_doc(NDoc);
                    ent.setTelefono(telefono);
                    ent.setCorreo(correo);
                    ent.setFecha(fecha);
                    ent.setFechaTermino(fechatermino);
                    ent.setHorario(horario);
                    entDAO.agregar(ent);
                    request.getRequestDispatcher("Controlador?menu=Entrenador&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Entrenador e = entDAO.listarId(ide);
                    request.setAttribute("entrenador", e);
                    request.getRequestDispatcher("Controlador?menu=Entrenador&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombre1 = request.getParameter("txtNombre");
                    String apellido1 = request.getParameter("txtApellido");
                    String TDoc1 = request.getParameter("txtTipoDocumento");
                    String NDoc1 = request.getParameter("txtDocumento");
                    String telefono1 = request.getParameter("txtTelefono");
                    String correo1 = request.getParameter("txtCorreo");
                    String fecha1 = request.getParameter("txtfecha");
                    String fechatermino1 = request.getParameter("txtfechatermino");
                    String horario1 = request.getParameter("txthorario");
                    ent.setNombre(nombre1);
                    ent.setApellido(apellido1);
                    ent.setTipo_doc(TDoc1);
                    ent.setNum_doc(NDoc1);
                    ent.setTelefono(telefono1);
                    ent.setCorreo(correo1);
                    ent.setFecha(fecha1);
                    ent.setFechaTermino(fechatermino1);
                    ent.setHorario(horario1);
                    ent.setId(ide);
                    entDAO.actualizar(ent);
                    request.getRequestDispatcher("Controlador?menu=Entrenador&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    entDAO.eliminar(ide);
                    request.getRequestDispatcher("Controlador?menu=Entrenador&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Entrenador.jsp").forward(request, response);
        }

        if (menu.equals("Administrador")) {
            switch (accion) {
                case "Listar":
                    List lista = adminDAO.listar();
                    request.setAttribute("administradores", lista);
                    break;

                case "Agregar":
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String TDoc = request.getParameter("txtTipoDocumento");
                    String NDoc = request.getParameter("txtDocumento");
                    String telefono = request.getParameter("txtTelefono");
                    String correo = request.getParameter("txtCorreo");
                    String NUsuario = request.getParameter("txtNombreUsuario");
                    String contraseña = request.getParameter("txtContrasena");
                    admin.setNombre(nombre);
                    admin.setApellido(apellido);
                    admin.setTipo_doc(TDoc);
                    admin.setNum_doc(NDoc);
                    admin.setTelefono(telefono);
                    admin.setCorreo(correo);
                    admin.setUsuario(NUsuario);
                    admin.setPassword(contraseña);
                    adminDAO.agregar(admin);
                    request.getRequestDispatcher("Controlador?menu=Administrador&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Admin a = adminDAO.listarId(ide);
                    request.setAttribute("administrador", a);
                    request.getRequestDispatcher("Controlador?menu=Administrador&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombre1 = request.getParameter("txtNombre");
                    String apellido1 = request.getParameter("txtApellido");
                    String TDoc1 = request.getParameter("txtTipoDocumento");
                    String NDoc1 = request.getParameter("txtDocumento");
                    String telefono1 = request.getParameter("txtTelefono");
                    String correo1 = request.getParameter("txtCorreo");
                    String NUsuario1 = request.getParameter("txtNombreUsuario");
                    String contraseña1 = request.getParameter("txtContrasena");
                    admin.setNombre(nombre1);
                    admin.setApellido(apellido1);
                    admin.setTipo_doc(TDoc1);
                    admin.setNum_doc(NDoc1);
                    admin.setTelefono(telefono1);
                    admin.setCorreo(correo1);
                    admin.setUsuario(NUsuario1);
                    admin.setPassword(contraseña1);
                    admin.setId(ide);
                    adminDAO.actualizar(admin);
                    request.getRequestDispatcher("Controlador?menu=Administrador&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    adminDAO.eliminar(ide);
                    request.getRequestDispatcher("Controlador?menu=Administrador&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Administrador.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
