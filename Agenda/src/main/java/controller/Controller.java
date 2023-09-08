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


@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/new"})
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
		}else {
			response.sendRedirect("index.html");
		}
	}
	
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
	
	protected void newContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contact.setName(request.getParameter("name"));
		contact.setFone(request.getParameter("fone"));
		contact.setEmail(request.getParameter("email"));
		
		dao.insertContact(contact);
		
		response.sendRedirect("main");
	}
}
