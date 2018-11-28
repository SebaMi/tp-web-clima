package clima.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clima.web.exception.DBException;
import clima.web.model.Pais;
import clima.web.model.Preferencias;
import clima.web.model.Usuario;
import clima.web.service.CiudadService;
import clima.web.service.PreferenciasService;

@WebServlet("/preferences")
public class PreferencesController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PreferenciasService ps = new PreferenciasService();
		
		CiudadService cs = new CiudadService();
		
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		
		try {
			Preferencias pref = ps.verificarPreferencias(user.getName());
			
			if(req.getSession().getAttribute("Temperatura").equals(null))
				req.getSession().setAttribute("Temperatura", "Seleccione escala");
			if(req.getSession().getAttribute("Ciudad").equals(null))
				req.getSession().setAttribute("Ciudad", " Seleccione ciudad" );
			
			
		} catch (DBException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			req.getSession().setAttribute("Temperatura", "Seleccione escala");
			req.getSession().setAttribute("Ciudad", " Seleccione ciudad" );
		}
		
		
		
		
		
		resp.sendRedirect("preferences.jsp");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		
		System.out.println("entro al post");
		
		String ciudad =  req.getParameter("ciudad");
		String temperatura = req.getParameter("temperatura");
		
		System.out.println("blabla "+ciudad + " " + temperatura);
		
		req.getSession().setAttribute("Ciudad", ciudad);
		req.getSession().setAttribute("Temperatura", temperatura);
		
		resp.sendRedirect("/integracion-web/preferences");
	}
}
