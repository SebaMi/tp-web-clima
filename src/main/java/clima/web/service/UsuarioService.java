package clima.web.service;

import java.sql.SQLException;

import clima.web.dao.DBConnection;
import clima.web.exception.DBException;
import clima.web.model.Usuario;

public class UsuarioService {

	public boolean validarUsuario(String email, String password) throws DBException {
		
		DBConnection dbConexion = new DBConnection();
		
		try {
			Usuario user = dbConexion.getUsuarioByEmail(email);
			if(user == null) {
				return false;
			} else if (!user.getPassword().equals(password)) {
			//if(!passDB.equals(password)) {
				return false;
			} else {
				return true;
			}
						
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new DBException();
		}
		
	}
	
	
}
