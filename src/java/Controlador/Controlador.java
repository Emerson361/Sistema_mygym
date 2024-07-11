    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Admin;
import Modelo.AdministradorDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.DetalleMembresia;
import Modelo.DetalleMembresiaDAO;
import Modelo.Dieta;
import Modelo.DietaDAO;
import Modelo.Entrenador;
import Modelo.EntrenadorDAO;
import Modelo.Estado;
import Modelo.EstadoDAO;
import Modelo.Membresia;
import Modelo.MembresiaDAO;
import Modelo.Nutricionista;
import Modelo.NutricionistaDAO;
import Modelo.Rutina;
import Modelo.RutinaDAO;
import Modelo.Ventas;
import Modelo.VentasDAO;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

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

    DietaDAO dietaDAO = new DietaDAO();
    Dieta diet = new Dieta();

    Rutina rut = new Rutina();
    RutinaDAO rutDAO = new RutinaDAO();

    Membresia mem = new Membresia();
    MembresiaDAO memDAO = new MembresiaDAO();

    Cliente cli = new Cliente();
    ClienteDAO cliDAO = new ClienteDAO();

    Ventas venta = new Ventas();
    VentasDAO ventasDAO = new VentasDAO();
    
    Estado estado = new Estado();
    EstadoDAO estadoDAO = new EstadoDAO();
    
    DetalleMembresia detalle = new DetalleMembresia();
    DetalleMembresiaDAO detalleDAO = new DetalleMembresiaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }

        if (menu.equals("PrincipalCliente")) {
            request.getRequestDispatcher("PrincipalCliente.jsp").forward(request, response);
        }

        if (menu.equals("Dieta")) {
            switch (accion) {
                case "Listar":
                    List lista = dietaDAO.listar();
                    request.setAttribute("dietas", lista);
                    List lista2 = nutDAO.listar();
                    request.setAttribute("nutricionistas", lista2);
                    break;

                case "Agregar":
                    String nombre = request.getParameter("txtNombre");
                    String tipDieta = request.getParameter("txtTipoDieta");
                    String duracion = request.getParameter("txtDuracion");
                    String supl = request.getParameter("txtSuplemento");
                    String idNutrS = request.getParameter("txtNutricionista");
                    int idNutr = Integer.parseInt(idNutrS);

                    diet.setNombre(nombre);
                    diet.setTipoDieta(tipDieta);
                    diet.setDuracion(duracion);
                    diet.setSuplemento(supl);
                    diet.setIdNutricionista(idNutr);

                    dietaDAO.agregar(diet);
                    String mensaje1 = "Dieta agregada correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    request.getRequestDispatcher("Controlador?menu=Dieta&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Dieta d = dietaDAO.listarId(ide);
                    request.setAttribute("dieta", d);
                    request.getRequestDispatcher("Controlador?menu=Dieta&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombre1 = request.getParameter("txtNombre");
                    String tipDieta1 = request.getParameter("txtTipoDieta");
                    String duracion1 = request.getParameter("txtDuracion");
                    String supl1 = request.getParameter("txtSuplemento");
                    String idNutrS1 = request.getParameter("txtNutricionista");

                    diet.setNombre(nombre1);
                    diet.setTipoDieta(tipDieta1);
                    diet.setDuracion(duracion1);
                    diet.setSuplemento(supl1);
                    diet.setNutricionista(idNutrS1);

                    diet.setIdDieta(ide);
                    dietaDAO.actualizar(diet);
                    String mensaje2 = "Dieta actualizada correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Dieta&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    dietaDAO.eliminar(ide);
                    String mensaje3 = "Dieta eliminada correctamente";
                    request.setAttribute("eliminar", mensaje3);
                    request.getRequestDispatcher("Controlador?menu=Dieta&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Dieta.jsp").forward(request, response);
        }

        if (menu.equals("Rutina")) {
            switch (accion) {

                case "Listar":
                    List lista = rutDAO.listar();
                    request.setAttribute("rutinas", lista);
                    List lista2 = entDAO.listar(); // capturar los nombres de la tabla entrenador en el combobox
                    request.setAttribute("entrenadores", lista2);
                    break;

                case "Agregar":
                    String nombre = request.getParameter("txtNombre");
                    String descripcion = request.getParameter("txtdescripcion");
                    String dificultad = request.getParameter("txtdificultad");
                    String frecuencia = request.getParameter("txtfrecuencia");
                    String identrenadorStr = request.getParameter("txtentrenador");
                    // Convertir el ID del entrenador a entero para enviarlo al formulario y bd / captura el id/value del combobox
                    int identrenador = Integer.parseInt(identrenadorStr);

                    rut.setNombre(nombre);
                    rut.setDescripcion(descripcion);
                    rut.setDificultad(dificultad);
                    rut.setFrecuencia(frecuencia);
                    rut.setIdentrenador(identrenador);

                    rutDAO.agregar(rut);
                    // Redirigir a la página Rutina y mostrar una alerta
                    String mensaje1 = "Rutina agregada correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    request.getRequestDispatcher("Controlador?menu=Rutina&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Rutina r = rutDAO.listarId(ide);
                    request.setAttribute("rutina", r);
                    request.getRequestDispatcher("Controlador?menu=Rutina&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombre1 = request.getParameter("txtNombre");
                    String descripcion1 = request.getParameter("txtdescripcion");
                    String dificultad1 = request.getParameter("txtdificultad");
                    String frecuencia1 = request.getParameter("txtfrecuencia");
                    String identrenadorStr1 = request.getParameter("txtentrenador");

                    rut.setNombre(nombre1);
                    rut.setDescripcion(descripcion1);
                    rut.setDificultad(dificultad1);
                    rut.setFrecuencia(frecuencia1);
                    rut.setEntrenador(identrenadorStr1);
                    rut.setId(ide);
                    rutDAO.actualizar(rut);
                    // Redirigir a la página Rutina y mostrar una alerta
                    String mensaje2 = "Rutina actualizada correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Rutina&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    rutDAO.eliminar(ide);
                    // Redirigir a la página Rutina y mostrar una alerta
                    String mensaje3 = "Rutina eliminada correctamente";
                    request.setAttribute("eliminar", mensaje3);
                    request.getRequestDispatcher("Controlador?menu=Rutina&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Rutina.jsp").forward(request, response);
        }

        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar":
                    List lista = cliDAO.listar();
                    request.setAttribute("clientes", lista);
                    break;

                case "Agregar":
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String TDoc = request.getParameter("txtTipoDocumento");
                    String NDoc = request.getParameter("txtDocumento");
                    String telefono = request.getParameter("txtTelefono");
                    String correo = request.getParameter("txtCorreo");
                    String genero = request.getParameter("txtgenero");
                    String fecha_nac = request.getParameter("txtfechanac");
                    String NUsuario = request.getParameter("txtNombreUsuario");
                    String contraseña = request.getParameter("txtContrasena");
                    cli.setNombre(nombre);
                    cli.setApellido(apellido);
                    cli.setTipo_doc(TDoc);
                    cli.setNum_doc(NDoc);
                    cli.setTelefono(telefono);
                    cli.setCorreo(correo);
                    cli.setGenero(genero);
                    cli.setFecha_nac(fecha_nac);
                    cli.setUsuario(NUsuario);
                    cli.setPassword(contraseña);
                    cliDAO.agregar(cli);

                    // Redirigir a la página Cliente y mostrar una alerta
                    String mensaje1 = "Cliente agregado correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Cliente c = cliDAO.listarId(ide);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombre1 = request.getParameter("txtNombre");
                    String apellido1 = request.getParameter("txtApellido");
                    String TDoc1 = request.getParameter("txtTipoDocumento");
                    String NDoc1 = request.getParameter("txtDocumento");
                    String telefono1 = request.getParameter("txtTelefono");
                    String correo1 = request.getParameter("txtCorreo");
                    String genero1 = request.getParameter("txtgenero");
                    String fecha_nac1 = request.getParameter("txtfechanac");
                    String NUsuario1 = request.getParameter("txtNombreUsuario");
                    String contraseña1 = request.getParameter("txtContrasena");
                    cli.setNombre(nombre1);
                    cli.setApellido(apellido1);
                    cli.setTipo_doc(TDoc1);
                    cli.setNum_doc(NDoc1);
                    cli.setTelefono(telefono1);
                    cli.setCorreo(correo1);
                    cli.setGenero(genero1);
                    cli.setFecha_nac(fecha_nac1);
                    cli.setUsuario(NUsuario1);
                    cli.setPassword(contraseña1);
                    cli.setId(ide);
                    cliDAO.actualizar(cli);

                    // Redirigir a la página Cliente y mostrar una alerta
                    String mensaje2 = "Cliente actualizado correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    cliDAO.eliminar(ide);
                    // Redirigir a la página Cliente y mostrar una alerta
                    String mensaje3 = "Cliente eliminado correctamente";
                    request.setAttribute("eliminar", mensaje3);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Cliente.jsp").forward(request, response);
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
                    // Redirigir a la página Nutricionista y mostrar una alerta
                    String mensaje1 = "Nutricionista agregado correctamente";
                    request.setAttribute("mensaje", mensaje1);
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
                    // Redirigir a la página Nutricionista y mostrar una alerta
                    String mensaje2 = "Nutricionista actualizado correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Nutricionista&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    nutDAO.eliminar(ide);
                    // Redirigir a la página Nutricionista y mostrar una alerta
                    String mensaje3 = "Nutricionista eliminado correctamente";
                    request.setAttribute("eliminar", mensaje3);
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
                    // Redirigir a la página Entrenador y mostrar una alerta
                    String mensaje1 = "Entrenador agregado correctamente";
                    request.setAttribute("mensaje", mensaje1);
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
                    // Redirigir a la página Entrenador y mostrar una alerta
                    String mensaje2 = "Entrenador actualizado correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Entrenador&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    entDAO.eliminar(ide);
                    // Redirigir a la página Entrenador y mostrar una alerta
                    String mensaje3 = "Entrenador eliminado correctamente";
                    request.setAttribute("eliminar", mensaje3);
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
                    // Redirigir a la página Administrador y mostrar una alerta
                    String mensaje1 = "Administrador agregado correctamente";
                    request.setAttribute("mensaje", mensaje1);
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
                    // Redirigir a la página Administrador y mostrar una alerta
                    String mensaje2 = "Administrador actualizado correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Administrador&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    adminDAO.eliminar(ide);
                    // Redirigir a la página Administrador y mostrar una alerta
                    String mensaje3 = "Administrador eliminado correctamente";
                    request.setAttribute("eliminar", mensaje3);
                    request.getRequestDispatcher("Controlador?menu=Administrador&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Administrador.jsp").forward(request, response);
        }

        //detalle membresia
        if (menu.equals("DetalleMembresia")) {
            switch (accion) {

                case "Listar":
                    List lista = detalleDAO.listar();
                    request.setAttribute("detalles", lista);
                
                    break;

                case "Agregar":
                    String fecha = request.getParameter("txtfecha");
                    String fechatermino = request.getParameter("txtfechatermino");
                    String horario = request.getParameter("txtHorario");
                    

                    detalle.setFecha(fecha);
                    detalle.setFechaTermino(fechatermino);
                    detalle.setHorario(horario);
               
                     
                    detalleDAO.agregar(detalle);
                    // Redirigir a la página detmem y mostrar una alerta
                    String mensaje1 = "Detalle Membresia agregada correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    request.getRequestDispatcher("Controlador?menu=DetalleMembresia&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    DetalleMembresia d = detalleDAO.listarId(ide);
                    request.setAttribute("detalle", d);
                    request.getRequestDispatcher("Controlador?menu=DetalleMembresia&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String fecha1 = request.getParameter("txtfecha");
                    String fechatermino1 = request.getParameter("txtfechatermino");
                    String horario1 = request.getParameter("txtHorario");
                    
                    detalle.setFecha(fecha1);
                    detalle.setFechaTermino(fechatermino1);
                    detalle.setHorario(horario1);
                    
                    detalle.setId(ide);
                    detalleDAO.actualizar(detalle);
                    // Redirigir a la página detmem y mostrar una alerta
                    String mensaje2 = "Detalle Membresia actualizado correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=DetalleMembresia&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    detalleDAO.eliminar(ide);
                    // Redirigir a la página detmem y mostrar una alerta
                    String mensaje3 = "Detalle Membresia eliminada correctamente";
                    request.setAttribute("eliminar", mensaje3);
                    request.getRequestDispatcher("Controlador?menu=DetalleMembresia&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("DetalleMembresia.jsp").forward(request, response);
        }
        
        
        
        if (menu.equals("Membresia")) {
            switch (accion) {
                case "Listar":
                    List lista = memDAO.listar();
                    request.setAttribute("membresias", lista);
                    List lista2 = estadoDAO.listar();
                    request.setAttribute("estados", lista2);
                    List lista3 = detalleDAO.listar();
                    request.setAttribute("detalles", lista3);
                    
                    break;

                case "Agregar":
                    String TipoMembresia = request.getParameter("txtTipoMembresia");
                    String Duracion = request.getParameter("txtDuracion");
                    double Precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    String Acceso = request.getParameter("txtAcceso");
                    String Observacion = request.getParameter("txtObservacion");
                    String horario = request.getParameter("txthorario");
                    String estado = request.getParameter("txtestado");

                    
                     
                    mem.setTipoMembresia(TipoMembresia);
                    mem.setDuracion(Duracion);
                    mem.setPrecio(Precio);
                    mem.setAcceso(Acceso);
                    mem.setObservacion(Observacion);
                    mem.setIdestado(Integer.parseInt(estado));
                    mem.setIddetmem(Integer.parseInt(horario));
             
                    memDAO.agregar(mem);
                    // Redirigir a la página Membresia y mostrar una alerta
                    String mensaje1 = "Membresia agregado correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    request.getRequestDispatcher("Controlador?menu=Membresia&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Membresia m = memDAO.listarId(ide);
                    request.setAttribute("membresia", m);
                    request.getRequestDispatcher("Controlador?menu=Membresia&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String TipoMembresia1 = request.getParameter("txtTipoMembresia");
                    String Duracion1 = request.getParameter("txtDuracion");
                    double Precio1 = Double.parseDouble(request.getParameter("txtPrecio"));
                    String Acceso1 = request.getParameter("txtAcceso");
                    String Observacion1 = request.getParameter("txtObservacion");
                    String horario1 = request.getParameter("txthorario");
                    String estado1 = request.getParameter("txtestado");
                    
                    
                    mem.setTipoMembresia(TipoMembresia1);
                    mem.setDuracion(Duracion1);
                    mem.setPrecio(Precio1);
                    mem.setAcceso(Acceso1);
                    mem.setObservacion(Observacion1);
                    mem.setIdestado(Integer.parseInt(estado1));
                    mem.setIddetmem(Integer.parseInt(horario1));
                    mem.setId(ide);
                    memDAO.actualizar(mem);

                    // Redirigir a la página Membresia y mostrar una alerta
                    String mensaje2 = "Membresia actualizado correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Membresia&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    memDAO.eliminar(ide);
                    // Redirigir a la página Membresia y mostrar una alerta
                    String mensaje3 = "Membresia eliminado correctamente";
                    request.setAttribute("eliminar", mensaje3);
                    request.getRequestDispatcher("Controlador?menu=Membresia&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Membresia.jsp").forward(request, response);
        }

        if (menu.equals("Ventas")) {
            switch (accion) {
                case "Listar":
                    List lista = ventasDAO.listar();
                    request.setAttribute("ventas", lista);
                    List lista2 = cliDAO.listar();
                    request.setAttribute("clientes", lista2);
                    List lista3 = memDAO.listar();
                    request.setAttribute("membresias", lista3);
                    List lista4 = dietaDAO.listar();
                    request.setAttribute("dietas", lista4);
                    List lista5 = rutDAO.listar();
                    request.setAttribute("rutinas", lista5);
                    break;

                case "Agregar":
                    String fechaVenta = request.getParameter("txtFechaVenta");
                    String observacion = request.getParameter("txtObservacion");
                    String fechaInicio = request.getParameter("txtFechaInicio");
                    String fechaFinal = request.getParameter("txtFechaFinal");
                    String horario = request.getParameter("txtHorario");
                    String idMembresia = request.getParameter("txtIdMembresia");
                    String idDieta = request.getParameter("txtIdDieta");
                    String idRutina = request.getParameter("txtIdRutina");
                    String idCliente = request.getParameter("txtIdCliente");

                    venta.setFechaVenta(fechaVenta);
                    venta.setObservacion(observacion);
                    venta.setFechaInicio(fechaInicio);
                    venta.setFechaFinal(fechaFinal);
                    venta.setHorario(horario);
                    venta.setIdMembresia(Integer.parseInt(idMembresia));
                    venta.setIdDieta(Integer.parseInt(idDieta));
                    venta.setIdRutina(Integer.parseInt(idRutina));
                    venta.setIdCliente(Integer.parseInt(idCliente));
                    ventasDAO.agregar(venta);

                    String mensaje1 = "Venta agregada correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    
           
                    
                    request.getRequestDispatcher("Controlador?menu=Ventas&accion=Listar").forward(request, response);
                    
                    
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Ventas v = ventasDAO.listarId(ide);
                    request.setAttribute("venta", v);
                    request.getRequestDispatcher("Controlador?menu=Ventas&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String fechaVenta1 = request.getParameter("txtFechaVenta");
                    String observacion1 = request.getParameter("txtObservacion");
                    String fechaInicio1 = request.getParameter("txtFechaInicio");
                    String fechaFinal1 = request.getParameter("txtFechaFinal");
                    String horario1 = request.getParameter("txtHorario");
                    String idMembresia1 = request.getParameter("txtIdMembresia");
                    String idDieta1 = request.getParameter("txtIdDieta");
                    String idRutina1 = request.getParameter("txtIdRutina");
                    String idCliente1 = request.getParameter("txtIdCliente");

                    venta.setFechaVenta(fechaVenta1);
                    venta.setObservacion(observacion1);
                    venta.setFechaInicio(fechaInicio1);
                    venta.setFechaFinal(fechaFinal1);
                    venta.setHorario(horario1);
                    venta.setIdMembresia(Integer.parseInt(idMembresia1));
                    venta.setIdDieta(Integer.parseInt(idDieta1));
                    venta.setIdRutina(Integer.parseInt(idRutina1));
                    venta.setIdCliente(Integer.parseInt(idCliente1));
                    venta.setIdVentas(ide);
                    ventasDAO.actualizar(venta);
                    // Redirigir a la pÃ¡gina Ventas y mostrar una alerta
                    String mensaje2 = "Venta actualizada correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Ventas&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    ventasDAO.eliminar(ide);
                    // Redirigir a la pÃ¡gina Ventas y mostrar una alerta
                    String mensaje3 = "Venta eliminada correctamente";
                    request.setAttribute("eliminar", mensaje3);
                    request.getRequestDispatcher("Controlador?menu=Ventas&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Ventas.jsp").forward(request, response);
        }

        
        /* INTERFAZ CLIENTE */
        if (menu.equals("VentasCliente")) {
            switch (accion) {

                case "Listar":
                    //mostrar los combobox
                    List lista = ventasDAO.listar();
                    request.setAttribute("ventas", lista);
                    List lista3 = memDAO.listar();
                    request.setAttribute("membresias", lista3);
                    List lista4 = dietaDAO.listar();
                    request.setAttribute("dietas", lista4);
                    List lista5 = rutDAO.listar();
                    request.setAttribute("rutinas", lista5);
                    break;
 
                case "Agregar":
                    String fechaVenta = request.getParameter("txtFechaVenta");
                    String observacion = request.getParameter("txtObservacion");
                    String idCliente = request.getParameter("txtIdCliente");
                    String idMembresia = request.getParameter("txtIdMembresia");
                    String idDieta = request.getParameter("txtIdDieta");
                    String idRutina = request.getParameter("txtIdRutina");
                    
                    
                    venta.setFechaVenta(fechaVenta);
                    venta.setObservacion(observacion);
                   
                    venta.setIdMembresia(Integer.parseInt(idMembresia));
                    venta.setIdDieta(Integer.parseInt(idDieta));
                    venta.setIdRutina(Integer.parseInt(idRutina));
                    venta.setIdCliente(Integer.parseInt(idCliente));
                    ventasDAO.agregar(venta);

                    String mensaje1 = "Venta agregada correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    
                    // reporte
                    // pdf
                    String reportCompiled = FileSystems.getDefault().getPath("../Reporte/Boleta.jasper").toAbsolutePath().toString();
                    JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportCompiled);

                    // PARAMETROS A ENVIAR
                    Map parameters = new HashMap();
                    parameters.put("codcliente", idCliente);

                    // COLECCION DE DATOS
                    //Collection<Boleta> datosReporte = TestReportBoleta.generateBoletaReal();
                    //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datosReporte);
                    Connection conn = null;
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        System.out.println("MySQL JDBC Driver not found.");
                        System.exit(1);
                    }
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/mygym", "root", "");
                        conn.setAutoCommit(false);
                    } catch (SQLException e) {
                        System.out.println("Error de conexión: " + e.getMessage());
                        System.exit(4);
                    }

                    final JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);

                    // RUTA DEL PDF
                    final String reportTarget = FileSystems.getDefault().getPath("../Reporte/Boleta_1.pdf").toAbsolutePath().toString();

                    JasperExportManager.exportReportToPdfFile(print, reportTarget);
                    
                    
                    //reportes/bolete.pdf llamar al pdf
                    request.getRequestDispatcher("../Reporte/boleta_1.pdf").forward(request, response);
                    //response.sendRedirect(request.getContextPath() + "../Reporte/Boleta_1.pdf");
                    break;
            }
            request.getRequestDispatcher("VentasCliente.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JRException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JRException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
