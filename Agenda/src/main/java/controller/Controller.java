package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;


@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/new", "/find", "/update", "/delete"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAO dao = new DAO();
	JavaBeans contact = new JavaBeans();

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/main")) {
			contacts(request, response);
		}else if (action.equals("/insert")) {
			newContact(request, response);
		}else if (action.equals("/new")) {
			newContactView(request,response);
		}else if (action.equals("/find")) {
			findContact(request,response);
		}else if (action.equals("/update")) {
			updateContact(request,response);
		}else if (action.equals("/delete")) {
			deleteContact(request,response);
		}else {
			response.sendRedirect("index.html");
		}
	}
	
	// List all
	protected void contacts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<JavaBeans> contacts = dao.listContacts();
		
		// Teste de conexçao
//		for (JavaBeans contact : contacts) {
//			System.out.println(contact.getName());
//		}
		
		// Encaminhar lista de contatos para o documento agenda.jsp
		request.setAttribute("contacts", contacts);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	protected void newContactView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("novo.html");
	}
	
	// Create
	protected void newContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recebendo dados da view
		contact.setName(request.getParameter("name"));
		contact.setFone(request.getParameter("fone"));
		contact.setEmail(request.getParameter("email"));
		
		// Enviar para a classe dao
		dao.insertContact(contact);
		
		response.sendRedirect("main");
	}
	
	// Find 1
	protected void findContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recebendo id da view
		String idcon = request.getParameter("idcon");
		
		// Setar a JavaBeans
		contact.setIdcon(idcon);
		
		// Enviar para a classe dao
		dao.find(contact);
		
		// Encaminhar dados para view
		request.setAttribute("idcon", contact.getIdcon());
		request.setAttribute("name", contact.getName());
		request.setAttribute("fone", contact.getFone());
		request.setAttribute("email", contact.getEmail());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	// Update
	protected void updateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Setar JavaBeans
		contact.setIdcon(request.getParameter("idcon"));
		contact.setName(request.getParameter("name"));
		contact.setFone(request.getParameter("fone"));
		contact.setEmail(request.getParameter("email"));
		
		// Enviar para a classe dao
		dao.update(contact);
		
		response.sendRedirect("main");
	}
	
	// Delete
	protected void deleteContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recebendo id da view
		String idcon = request.getParameter("idcon");
		
		// Setar JavaBeans
		contact.setIdcon(idcon);
		
		// Enviar para a classe dao
		dao.delete(contact);
		
		response.sendRedirect("main");
	}
}
