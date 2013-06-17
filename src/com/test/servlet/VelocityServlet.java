package com.test.servlet;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import com.test.bean.User;

public class VelocityServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		SAXBuilder builder;
		Document root = null;

		try {
			builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");

			String xmlInput = "<root><tag1>zz hello</tag1></root>";
			root = builder.build(new StringReader(xmlInput));

			System.out.println (" $$$$$$$$ end " );
		} 
		catch (Exception ee) 
		{
			System.out.println (" $$$$$$$$ ee = " + ee);

		}

		System.out.println (" !!!!!!! root = " + root.getRootElement());
		

		try {
			// simulate a database query
			List<User> users = new ArrayList<User>();
			for (int i = 0; i < 5; i++) {
				User user = new User("name " + i, "Address " + i);
				users.add(user);
			}
			// set values
			request.setAttribute("users", users);
			request.setAttribute("root", root.getRootElement());

			request.setAttribute("val1", "roadrunner");

			// get UI
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("index.vm");
			requestDispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
