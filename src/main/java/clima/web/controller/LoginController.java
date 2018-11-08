package clima.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		List<Pais> paises = service.getPaises();
				
		req.getSession().setAttribute("paises", paises);
		
		resp.sendRedirect("login.jsp");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user =  req.getParameter("exampleInputEmail1");
		String password = req.getParameter("exampleInputPassword1");
		Integer pais = Integer.valueOf(req.getParameter("pais"));
		
		UsuarioService userService = new UsuarioService();
		
		String mensajeError = "<div class=\"alert alert-danger\" role=\"alert\">Usuario o contrasenia invalidos.</div>";
		boolean login = false;
		
		try {
			login = userService.validarUsuario(user, password);
						
		} catch (DBException e) {
			
			mensajeError = "<div class=\\\"alert alert-danger\\\" role=\\\"alert\\\">Hubo un problema en la validacion, intente nuevamente.</div>";
			
		}
		
		if(login) {
			
			CiudadService service = new CiudadService();
			Pais paisSeleccionado = service.getPais(pais);
			
			List<Ciudad> ciudades = service.getCiudades(pais);
			
			Usuario usuario = new Usuario();
			usuario.setName(user);
			
			req.getSession().setAttribute("ciudades", ciudades);
			req.getSession().setAttribute("usuario", usuario);
			req.getSession().setAttribute("nombrePais", paisSeleccionado.getNombre());
			
			resp.sendRedirect("preferences.jsp");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= resp.getWriter();
			out.println(mensajeError);
			rd.include(req, resp);
		}
	}

	



	
	
	
	
}
