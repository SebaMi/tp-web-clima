package clima.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clima.web.model.Usuario;

public class DBConnection {

	public void testDBConnection() {
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

	/*
	 * https://github.com/mlennard-utn/avanzando/blob/master/AvanzadoRepaso/src/jdbc
	 * /ConexionDB.java
	 */
	public Usuario getUsuarioByEmail(String email) throws SQLException {

		Usuario user = null;

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_web", "javaweb",
				"javaweb");) {

			String query = "SELECT * FROM usuario where email = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new Usuario();
				String password = rs.getString("password");
				user.setPassword(password);
				user.setEmail(email);

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

}
