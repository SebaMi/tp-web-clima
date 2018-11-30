package clima.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clima.web.model.Ciudad;
import clima.web.model.Pais;
import clima.web.model.Preferencias;
import clima.web.model.Usuario;

public class DBConnection {

	{
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		System.out.println("MySQL JDBC Driver Registered!");
	}

	public Usuario getUsuarioByEmail(String email) throws SQLException {

		Usuario user = null;

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "javaweb",
				"javaweb");) {

			String query = "SELECT * FROM usuarios where email = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new Usuario();
				String password = rs.getString("password");
				Integer id = rs.getInt("id_usuarios");
				user.setPassword(password);
				user.setEmail(email);
				user.setId(id);

			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				throw ex;
			}
		}
		return user;
	}
	
	public Preferencias getPreferences(String user) throws SQLException {
		
		Preferencias pref = new Preferencias();

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "javaweb",
				"javaweb");) {

			String query = "SELECT * FROM mydb.preferencias inner join mydb.usuarios on mydb.preferencias.USUARIOS_ID_USUARIOS = mydb.usuarios.ID_USUARIOS where mydb.usuarios.ID_USUARIOS = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setString(1, user);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				pref.setGrado(rs.getString("grado"));
				pref.setIdPais(rs.getInt("pais_id_pais"));
				pref.setIdCiudad(rs.getInt("ciudad_id_ciudad"));
				pref.setIdUsuario(rs.getInt("usuarios_id_usuarios"));
				
			}
			
			System.out.println("el usuario no tiene preferencias guardadas");

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				throw ex;
			}
		}
		return pref;
	}
	
	public Boolean setPreferences(Preferencias pref) throws SQLException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "javaweb",
				"javaweb");) {

			String query = "INSERT INTO preferencias VALUES (?, ?,?,?) ";
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setString(1, pref.getGrado());
			pstmt.setInt(2, pref.getIdUsuario());
			pstmt.setInt(3, pref.getIdPais());
			pstmt.setInt(4, pref.getIdCiudad());

			if(pstmt.execute()) {
				return true;
			}
			else {
				return false;
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				throw ex;
			}
		}
		return false;
	}

	public List<Ciudad> getCiudades(Integer idPais) throws SQLException {
		
		List<Ciudad> ciudades = null;

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "javaweb",
				"javaweb");) {

			String query = "SELECT * FROM ciudades  INNER JOIN paises on ciudades.PAIS_ID_PAIS = paises.ID_PAIS where paises.ID_PAIS = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setString(1, idPais.toString());

			ResultSet rs = pstmt.executeQuery();
			
			ciudades = new ArrayList<Ciudad>();
			
			while (rs.next()) {
				
				Ciudad ciudad = new Ciudad();
				ciudad.setNombre(rs.getString("nombre"));
				ciudad.setId(rs.getInt("id_ciudad"));
				ciudad.setPais(rs.getString("pais_id_pais"));
			    ciudades.add(ciudad);
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				throw ex;
			}
		}				
		
		return ciudades;
	}

public List<Ciudad> getCiudades() throws SQLException {
		
		List<Ciudad> ciudades = null;

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "javaweb",
				"javaweb");) {

			String query = "SELECT * FROM ciudades";
			PreparedStatement pstmt = connection.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			
			ciudades = new ArrayList<Ciudad>();
			
			while (rs.next()) {
				
				Ciudad ciudad = new Ciudad();
				ciudad.setNombre(rs.getString("nombre"));
				ciudad.setId(rs.getInt("id_ciudad"));
				ciudad.setPais(rs.getString("pais_id_pais"));
			    ciudades.add(ciudad);
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				throw ex;
			}
		}				
		
		return ciudades;
	}

	public List<Pais> getPaises() throws SQLException {
		
		List<Pais> paises = null;

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "javaweb",
				"javaweb");) {

			String query = "SELECT * FROM paises";
			PreparedStatement pstmt = connection.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			
			paises = new ArrayList<Pais>();
			
			while (rs.next()) {
				
				Pais pais = new Pais();
				pais.setNombre(rs.getString("nombre"));
				pais.setId(rs.getInt("id_pais"));
			    paises.add(pais);
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				throw ex;
			}
		}				

		return paises;
	}

	public Integer getPaisByCiudad(Integer idCiudad) throws SQLException {
		
		Integer idPais = null;
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "javaweb",
				"javaweb");) {

			String query = "SELECT pais_id_pais FROM ciudades WHERE id_ciudad = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, idCiudad);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			
			while (rs.next()) {
				idPais = rs.getInt("pais_id_pais");
				
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				throw ex;
			}
		}	
		
		return idPais;
	}

	public Pais getPais(Integer idPais) throws SQLException {
		
		Pais pais = null;
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "javaweb",
				"javaweb");) {

			String query = "SELECT nombre FROM paises WHERE id_pais = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, idPais);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			
			while (rs.next()) {
				pais.setId(rs.getInt("id_pais")); 
				pais.setNombre(rs.getString("nombre"));
				
			}

		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				throw ex;
			}
		}	
		
		return pais;
	}
	
}
