package clima.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clima.web.model.Ciudad;
import clima.web.model.Pais;
import clima.web.model.Usuario;
import clima.web.service.CiudadService;

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
		
		CiudadService service = new CiudadService();
		Pais paisSeleccionado = service.getPais(pais);
		
		List<Ciudad> ciudades = service.getCiudades(pais);
		
		Usuario usuario = new Usuario();
		usuario.setName(user);
		
		req.getSession().setAttribute("ciudades", ciudades);
		req.getSession().setAttribute("usuario", usuario);
		req.getSession().setAttribute("nombrePais", paisSeleccionado.getNombre());
		
		resp.sendRedirect("preferences.jsp");
	}

	



	
	
	
	
}
