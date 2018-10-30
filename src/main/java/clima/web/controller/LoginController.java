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

import clima.web.model.Pais;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Pais> paises = getPaises();
				
		req.getSession().setAttribute("paises", paises);
		
		resp.sendRedirect("login.jsp");
		
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user =  req.getParameter("exampleInputEmail1");
		String password = req.getParameter("exampleInputPassword1");
		Integer pais = Integer.valueOf(req.getParameter("pais"));
				
		super.doPost(req, resp);
	}





	private List<Pais> getPaises(){
		List<Pais> listaPaises = new ArrayList<>();
		
		Pais arg = new Pais();
		arg.setId(123);
		arg.setNombre("Argentina");
		
		Pais ven = new Pais();
		ven.setId(312);
		ven.setNombre("Venezuela");
		
		Pais ale = new Pais();
		ale.setId(312);
		ale.setNombre("Alemania");

		listaPaises.add(ale);
		listaPaises.add(arg);
		listaPaises.add(ven);
		
		return listaPaises;
	}
	
	
	
}
