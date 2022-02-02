package control;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import bean.Categoria;
import model.*;
import utils.Utility;

@WebServlet("/RimuoviCategoria")
public class RimuoviCategoriaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean loggedIn = request.getSession(false) != null && request.getSession(false).getAttribute("gestoreCatalogoRoles")!= null;
		if(!loggedIn) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login.jsp"));
			return;
		}
		
		String nome = request.getParameter("nome");
		if (nome == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/gestoreCatalogo/rimuoviCategoria.jsp"));
			return;
		}
		
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		CategoriaDAO model = new CategoriaDAO(ds);	

		try {
			Categoria bean = model.doRetrieveByKey(nome);
			model.doDelete(bean);
	    	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/successo.jsp"));
	    	return;

		} catch(SQLException e) {
			Utility.printSQLException(e);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/error/generic.jsp"));
			return;
		}
	}
}


