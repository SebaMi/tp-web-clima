package clima.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import clima.web.model.Ciudad;
import clima.web.model.Pais;

public class CiudadService {
	
	private List<Ciudad> ciudades;
	
	public CiudadService() {
		List<Pais> paises = getPaises();
		this.ciudades = new ArrayList<>();
		Ciudad ciudad = null;
		
		for (int i = 0; i < 15; i++) {
			ciudad = new Ciudad();
			ciudad.setId(i);
			ciudad.setNombre("Ciudad-" + i);
			ciudad.setPais(paises.get(i % 3));
			
			this.ciudades.add(ciudad);
		}
	}
	
	public List<Ciudad> getCiudades(Integer idPais){
			
		 List<Ciudad> result = ciudades.stream()               
	                .filter(ciudad -> 
	                		idPais.equals(ciudad.getPais().getId()))     
	                .collect(Collectors.toList());  
		
		return result;
	}
	
	
	public List<Pais> getPaises(){
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
