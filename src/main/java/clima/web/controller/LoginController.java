package clima.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clima.web.dao.DBConnection;
import clima.web.exception.DBException;
import clima.web.model.Ciudad;
import clima.web.model.Pais;
import clima.web.model.Usuario;
import clima.web.service.CiudadService;
import clima.web.service.UsuarioService;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CiudadService service = new CiudadService();
		List<Pais> paises = null;
		try {
			paises = service.getPaises();
		} catch (DBException e) {
			
			e.printStackTrace();
		}
				
		req.getSession().setAttribute("paises", paises);
		
		resp.sendRedirect("login.jsp");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email =  req.getParameter("exampleInputEmail1");
		String password = req.getParameter("exampleInputPassword1");
		Integer pais = Integer.valueOf(req.getParameter("pais"));
		
		UsuarioService userService = new UsuarioService();
		DBConnection db = new DBConnection();
		
		String mensajeError = "<div class=\"alert alert-danger\" role=\"alert\">Usuario o contrasenia invalidos.</div>";
		boolean login = false;
		Usuario user = new Usuario();
		
		try {
			login = userService.validarUsuario(email, password);
		
		} catch (DBException e) {
			
			e.printStackTrace();
			
			mensajeError = "<div class=\"alert alert-danger\" role=\"alert\">Hubo un problema en la validacion, intente nuevamente.</div>";
		}
		
		if(login) {
			
			CiudadService service = new CiudadService();
			
			Pais paisSeleccionado= null;;
			try {
				paisSeleccionado = service.getPais(pais);
			} catch (DBException e) {
				e.printStackTrace();
			}
			
			List<Ciudad> ciudades = null;
			try {
				ciudades = service.getCiudades(pais);
			} catch (DBException e) {
				e.printStackTrace();
			}
			
			user.setName(email);
			
			req.getSession().setAttribute("ciudades", ciudades);
			req.getSession().setAttribute("usuario", user);
			req.getSession().setAttribute("nombrePais", paisSeleccionado.getNombre());
			
			resp.sendRedirect("/integracion-web/preferences");
			
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out= resp.getWriter();
			out.println(mensajeError);
			rd.include(req, resp);
		}
	}

	



	
	
	
	
}
