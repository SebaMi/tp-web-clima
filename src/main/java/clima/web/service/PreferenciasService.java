package clima.web.service;

import java.sql.SQLException;

import clima.web.dao.DBConnection;
import clima.web.exception.DBException;
import clima.web.model.Preferencias;
import clima.web.model.Usuario;

public class PreferenciasService {
	
	public Preferencias verificarPreferencias (String email) throws DBException {
		Preferencias pref = null;
		DBConnection db = new DBConnection();
		
		try {
			pref = db.getPreferences(email);
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new DBException();
		}
		
		return pref;
	}

	public void persistirPreferencias(Preferencias pref) throws DBException  {
		DBConnection db = new DBConnection();
		
		try {
			db.setPreferences(pref);
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new DBException();
		}
		
	}
	
	
}
