package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	// Parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "4sb84ud2";

	// Método de conexão
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

	// Teste de conexão
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
			// Abrir conexão
			Connection con = connect();

			// Preparar query
			PreparedStatement pst = con.prepareStatement(create);

			pst.setString(1, contact.getName());
			pst.setString(2, contact.getFone());
			pst.setString(3, contact.getEmail());

			// Executar query
			pst.executeUpdate();

			// Fechar conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// List all
	public ArrayList<JavaBeans> listContacts() {
		String read = "select * from contatos";

		ArrayList<JavaBeans> contacts = new ArrayList<>();

		try {
			// Abrir conexão
			Connection con = connect();

			// Preparar query
			PreparedStatement pst = con.prepareStatement(read);

			// Recebendo resultados do banco
			ResultSet rs = pst.executeQuery();

			// Transformando ResultSet em JavaBeans
			while (rs.next()) {
				contacts.add(new JavaBeans(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}

			// Fechar conexão
			con.close();

			return contacts;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Find 1
	public void find(JavaBeans contact) {
		String read = "select * from contatos where idcon = ?";

		try {
			// Conectar no banco
			Connection con = connect();

			// Preparar query
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, contact.getIdcon());

			// Recebendo resultados do banco
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				contact.setIdcon(rs.getString(1));
				contact.setName(rs.getString(2));
				contact.setFone(rs.getString(3));
				contact.setEmail(rs.getString(4));
			}
			
			// Fechar conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Update
	public void update(JavaBeans contact) {
		String update = "update contatos set nome=?,fone=?,email=? where idcon=?";

		try {
			// Conectar no banco
			Connection con = connect();

			// Preparar query
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getFone());
			pst.setString(3, contact.getEmail());
			pst.setString(4, contact.getIdcon());

			// Recebendo resultados do banco
			pst.executeUpdate();
			
			// Fechar conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
