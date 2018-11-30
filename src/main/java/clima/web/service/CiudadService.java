package clima.web.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import clima.web.dao.DBConnection;
import clima.web.exception.DBException;
import clima.web.model.Ciudad;
import clima.web.model.Pais;

public class CiudadService {
	
	private List<Ciudad> ciudades;
	
//	public CiudadService() {
//		List<Pais> paises = getPaises();
//		this.ciudades = new ArrayList<>();
//		Ciudad ciudad = null;
//		
////		for (int i = 0; i < 15; i++) {
////			ciudad = new Ciudad();
////			ciudad.setId(i);
////			ciudad.setNombre("Ciudad-" + i);
////			ciudad.setPais(paises.get(i % 3));
////			
////			this.ciudades.add(ciudad);
////		}
//		
//		
//	}
	
public List<Ciudad> getCiudades() throws DBException{
		
		DBConnection db = new DBConnection();
		
		List<Ciudad> result;
		try {
			result = db.getCiudades();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new DBException();
		}
		
		return result;
	}
	
	public List<Ciudad> getCiudades(Integer idPais) throws DBException{
		
		DBConnection db = new DBConnection();
		
		List<Ciudad> result;
		try {
			result = db.getCiudades(idPais);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new DBException();
		}
		
		return result;
	}
	
	
	public Ciudad getCiudad(Integer id)  {
		
		List<Ciudad> ciudades = null;
		try {
			ciudades = this.getCiudades();
		} catch (DBException e) {
			
			e.printStackTrace();
		}
		
		for (Ciudad ciudad : ciudades) {
			if (id == ciudad.getId()) {
				return ciudad;
			}
		}
		
		return null;
	}
	
	
	public List<Pais> getPaises() throws DBException{
		
		DBConnection db = new DBConnection();
		
		List<Pais> listaPaises = new ArrayList<>();
		
		try {
		
			listaPaises = db.getPaises();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new DBException();
		}
		
		return listaPaises;
	}

	public Pais getPais(Integer pais) throws DBException {
		
		List<Pais> paises = this.getPaises();
		
		return paises.stream().filter(
				p -> p.getId().equals(pais)).collect(Collectors.toList()).get(0);
		
		
//		for (Pais pais2 : paises) {
//			if(pais2.getId().equals(pais)) return pais2;
//		}
//		
//		return null;
	}
	
	
public Pais getPaisByIdCiudad(Integer idCiudad) throws DBException{
		
		DBConnection db = new DBConnection();
		
		Pais pais = null;
		
		try {
		
			int idPais = db.getPaisByCiudad(idCiudad);
			
			System.out.println(idPais);
			pais = db.getPais(idPais);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new DBException();
		}
		
		return pais;
	}
	
	
}
