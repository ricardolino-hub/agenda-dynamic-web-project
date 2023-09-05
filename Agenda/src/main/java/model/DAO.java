package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {
	//Parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "4sb84ud2";
	
	//Método de conexão
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	//Teste de conexão
//	public void testConnection() {
//		try {
//			Connection con = connect();
//			System.out.println(con);
//			con.close();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
	
	// Create
	public void insertContact(JavaBeans contact) {
		String create = "insert into contatos (nome, fone, email) values (?,?,?)";
		
		try {
			//Abrir conexão
			Connection con = connect();
			
			// Preparar query
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getFone());
			pst.setString(3, contact.getEmail());
			
			//Executar query
			pst.executeUpdate();
			
			//Fechar conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
