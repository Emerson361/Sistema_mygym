/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Admin;
import Modelo.AdministradorDAO;
import Modelo.Boleta;
import Modelo.BoletaDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Dieta;
import Modelo.DietaDAO;
import Modelo.Entrenador;
import Modelo.EntrenadorDAO;
import Modelo.Membresia;
import Modelo.MembresiaDAO;
import Modelo.Nutricionista;
import Modelo.NutricionistaDAO;
import Modelo.Rutina;
import Modelo.RutinaDAO;
import Modelo.Ventas;
import Modelo.VentasDAO;
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
    int ide, est, est1;

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
    
    Boleta boleta = new Boleta();
    BoletaDAO boletaDAO = new BoletaDAO();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
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

   // Registro de usuario
        if (menu.equals("Registro")) {
            switch (accion) {
                case "Mostrar":
                    request.getRequestDispatcher("NuevoUsuario.jsp").forward(request, response);
                    break;
                case "Salir":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

                case "Registrar":
                     String nombre = request.getParameter("txtNombre");
                    String apellidos = request.getParameter("txtApellido");
                    String tipoDocumento = request.getParameter("txtTipoDocumento");
                    String numeroDocumento = request.getParameter("txtDocumento");
                    String telefono = request.getParameter("txtTelefono");
                    String correo = request.getParameter("txtCorreo");
                    String genero = request.getParameter("txtGenero");
                    String fechaNacimiento = request.getParameter("txtfechanac");
                    String nombreUsuario = request.getParameter("txtNombreUsuario");
                    String contrasena = request.getParameter("txtContrasena");
                    est= 0;
                       // Verificar si el correo ya existe
                    Cliente clien = new Cliente();
                    clien=cliDAO.existeCorreo(correo);
                    if (clien.getCorreo()!=null) {
                        request.setAttribute("mensaje1", "El correo electrónico ya está registrado.");
                        request.getRequestDispatcher("Controlador?menu=Registro&accion=Mostrar").forward(request, response);
                    } else {
                      
                    // Crear nuevo cliente
                    cli.setNombre(nombre);
                    cli.setApellido(apellidos);
                    cli.setTipo_doc(tipoDocumento);
                    cli.setNum_doc(numeroDocumento);
                    cli.setTelefono(telefono);
                    cli.setCorreo(correo);
                    cli.setGenero(genero);
                    cli.setFecha_nac(fechaNacimiento);
                    cli.setUsuario(nombreUsuario);
                    cli.setPassword(contrasena);
                    cli.setEstado(est);
                    cliDAO.agregar(cli);
                     // Redirigir a la página Cliente y mostrar una alerta
                    String mensaje1 = "Datos guardados correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    request.getRequestDispatcher("Controlador?menu=Registro&accion=Mostrar").forward(request, response);
                  
                   
                    }
                    break;
//   
            }
              request.getRequestDispatcher("index.jsp").forward(request, response);
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
                    est= Integer.parseInt(request.getParameter("intEstado"));
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
                    cli.setEstado(est);
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
                    est= Integer.parseInt(request.getParameter("intEstado"));
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
                    cli.setEstado(est);
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

        if (menu.equals("Membresia")) {
            switch (accion) {
                case "Listar":
                    List lista = memDAO.listar();
                    request.setAttribute("membresias", lista);
                    break;

                case "Agregar":
                    String TipoMembresia = request.getParameter("txtTipoMembresia");
                    String Duracion = request.getParameter("txtDuracion");
                    double Precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    String Acceso = request.getParameter("txtAcceso");
                    String Observacion = request.getParameter("txtObservacion");

                    mem.setTipoMembresia(TipoMembresia);
                    mem.setDuracion(Duracion);
                    mem.setPrecio(Precio);
                    mem.setAcceso(Acceso);
                    mem.setObservacion(Observacion);
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

                    mem.setTipoMembresia(TipoMembresia1);
                    mem.setDuracion(Duracion1);
                    mem.setPrecio(Precio1);
                    mem.setAcceso(Acceso1);
                    mem.setObservacion(Observacion1);
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
                    String idMembresia = request.getParameter("txtIdMembresia");
                    String idDieta = request.getParameter("txtIdDieta");
                    String idRutina = request.getParameter("txtIdRutina");
                    String idCliente = request.getParameter("txtIdCliente");
                    
                    venta.setFecha_recibo(fechaVenta);
                    venta.setObservaciones(observacion);
                    venta.setId_membresia(Integer.parseInt(idMembresia));
                    venta.setId_dieta(Integer.parseInt(idDieta));
                    venta.setId_rutina(Integer.parseInt(idRutina));
                    venta.setId_cliente(Integer.parseInt(idCliente));
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
                    String idMembresia1 = request.getParameter("txtIdMembresia");
                    String idDieta1 = request.getParameter("txtIdDieta");
                    String idRutina1 = request.getParameter("txtIdRutina");
                    String idCliente1 = request.getParameter("txtIdCliente");
                    
                    venta.setFecha_recibo(fechaVenta1);
                    venta.setObservaciones(observacion1);
                    venta.setId_membresia(Integer.parseInt(idMembresia1));
                    venta.setId_dieta(Integer.parseInt(idDieta1));
                    venta.setId_rutina(Integer.parseInt(idRutina1));
                    venta.setId_cliente(Integer.parseInt(idCliente1));
                    venta.setId_recibo(ide);
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
        
        if (menu.equals("Boleta")) {
            switch (accion) {
                case "Listar":
                    List lista = boletaDAO.listar();
                    request.setAttribute("boletas", lista);
                    List lista2 = ventasDAO.listar();
                    request.setAttribute("ventas", lista2);
                    break;

                case "Agregar":
                    String idVenta = request.getParameter("txtIdRecibo");
                    String fechaPago = request.getParameter("txtFechaPago");
                    String tipoPago = request.getParameter("txtTipoPago");
                    String estadoPago = request.getParameter("txtEstadoPago");
                    
                    boleta.setId_recibo(Integer.parseInt(idVenta));
                    boleta.setFecha_pago(fechaPago);
                    boleta.setTipo_pago(tipoPago);
                    boleta.setEstado_pago(estadoPago);
                    boletaDAO.agregar(boleta);
                    
                    String mensaje1 = "Boleta agregada correctamente";
                    request.setAttribute("mensaje", mensaje1);
                    request.getRequestDispatcher("Controlador?menu=Boleta&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Boleta v = boletaDAO.listarId(ide);
                    request.setAttribute("boleta", v);
                    request.getRequestDispatcher("Controlador?menu=Boleta&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String idVenta1 = request.getParameter("txtIdRecibo");
                    String fechaPago1 = request.getParameter("txtFechaPago");
                    String tipoPago1 = request.getParameter("txtTipoPago");
                    String estadoPago1 = request.getParameter("txtEstadoPago");
                    
                    boleta.setId_recibo(Integer.parseInt(idVenta1));
                    boleta.setFecha_pago(fechaPago1);
                    boleta.setTipo_pago(tipoPago1);
                    boleta.setEstado_pago(estadoPago1);
                    boleta.setId_boleta(ide);
                    boletaDAO.actualizar(boleta);
                    // Redirigir a la pÃ¡gina Ventas y mostrar una alerta
                    String mensaje2 = "Boleta actualizada correctamente";
                    request.setAttribute("mensaje", mensaje2);
                    request.getRequestDispatcher("Controlador?menu=Boleta&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    boletaDAO.eliminar(ide);
                    // Redirigir a la pÃ¡gina Ventas y mostrar una alerta
                    String mensaje3 = "Boleta eliminada correctamente";
                    request.setAttribute("eliminar", mensaje3);
                    request.getRequestDispatcher("Controlador?menu=Boleta&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Boleta.jsp").forward(request, response);
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
