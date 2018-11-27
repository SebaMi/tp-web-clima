package clima.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clima.web.model.Pais;
import clima.web.service.CiudadService;

@WebServlet("/preferences")
public class PreferencesController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("entro al get");
		
		resp.sendRedirect("preferences.jsp");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("entro al post");
		
		String ciudad =  req.getParameter("ciudad");
		String temperatura = req.getParameter("temperatura");
		System.out.println(ciudad + " " + temperatura);
	}
}
