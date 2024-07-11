/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Admin;
import Modelo.AdministradorDAO;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EMERSOM APAZA
 */
public class Validar extends HttpServlet {

    AdministradorDAO adminDAO = new AdministradorDAO();
    Admin admin = new Admin();
    
    ClienteDAO cliDAO = new ClienteDAO();
    Cliente cliente = new Cliente();
            

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        String accion = request.getParameter("accion");
        String user = request.getParameter("txtuser");
        String pass = request.getParameter("txtpass");
        
        if (accion.equalsIgnoreCase("Ingresar")) {
            //Validar como administrador
            admin = adminDAO.validar(user, pass);

            if (admin.getUsuario() != null) {
                request.setAttribute("usuario", admin); // valores para la pag Principal | data de admin logeado
                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
                
            } else {
                //Validar como cliente
                cliente = cliDAO.validar(user, pass);
                
                if (cliente.getUsuario() != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("clientes", cliente); // Guardar el cliente en la sesión
                    
                    request.setAttribute("cliente", cliente);// valores para la pag PrincipalCliente | data de cliente logeado
                    request.getRequestDispatcher("Controlador?menu=PrincipalCliente").forward(request, response);
                    
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        
        
    }

        
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
