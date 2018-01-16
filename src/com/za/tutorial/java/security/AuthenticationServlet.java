package com.za.tutorial.java.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthenticationServlet() {
        super();       
        //System.setProperty("java.security.auth.login.config", "jaastutorial.config");
        System.setProperty("java.security.auth.login.config", "aaa.config");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("JAAS Authentication Sample Project");
		System.out.println("1");
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<html><head><title>JAAS WEB</title></head><body>");
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		System.out.println("2");
		if((user != null) || (password != null)){
			System.out.println("3");
			ZaCallbackHandler testCallbackHandler = new ZaCallbackHandler(user, password);
			System.out.println("5");
			boolean authenticationFlag = true; 
			try {
				LoginContext loginContext = new LoginContext("ZaJaasTutorial", testCallbackHandler);
				loginContext.login();
				System.out.println("17");
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				authenticationFlag = false;
			}
			
			if(authenticationFlag){
				System.out.println("18s");
				printWriter.println("Authentication Success...");
			}else{
				System.out.println("18f");
				printWriter.println("Authentication Failed...");
			}
		}else{
			printWriter.println("Invalid Authentication...");
		}		
		printWriter.println("</body></html>");
	}

}
