package br.com.pedroxsqueiroz.ecommerce.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pedroxsqueiroz.ecommerce.models.User;
import br.com.pedroxsqueiroz.ecommerce.services.AuthService;
import br.com.pedroxsqueiroz.ecommerce.services.ClientService;

@WebServlet(urlPatterns = { "auth", "/", "register" } )
public class AuthController extends HttpServlet{
	
	@Inject
	private AuthService auth;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		
		if("/register".equals(servletPath)) 
		{
			
			String name = req.getParameter("name");
			String birthdate =  req.getParameter("birthdate");
			String email = req.getParameter("email");
			String password =  req.getParameter("password");
			String postcode = req.getParameter("postcode");
			String gender =  req.getParameter("gender");
			String address =  req.getParameter("address");
		
			User user = new User(name, birthdate, email, password, address, postcode, gender);
			
			User userRegistered = this.auth.register(user);
			
			req.getSession().setAttribute("user_id", userRegistered.getId());
			resp.sendRedirect("products");
		
		}else if("/auth".equals(servletPath)) {
			
			String email = req.getParameter("email");
			String password =  req.getParameter("password");
			
			User user = this.auth.auth(email, password);
			
			if(user == null) 
			{				
				req.setAttribute("errors", new String[] { "Email ou senha incorretos!" } );
				req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
			}
			else
			{
				req.getSession().setAttribute("user_id", user.getId());
				resp.sendRedirect("products");
			}
			
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		
		if("/register".equals(servletPath)) 
		{
			req.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(req, resp); 
		}else
		{
			Object loggedUserId = (Integer) req.getSession().getAttribute("user_id");
			
			if(loggedUserId == null) 
			{
				req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
			}else
			{
				resp.sendRedirect("products");
			}			
		}
	}
	
}
