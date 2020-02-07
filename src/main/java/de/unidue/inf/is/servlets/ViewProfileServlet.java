package de.unidue.inf.is.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;


public final class ViewProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static List<Project> projects = new ArrayList<>();
    private static List<Project> supported = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
    	String userEmail = request.getParameter("user");
    	
    	User us = null;
    	
    	try {
			us = DBUtil.getUser(userEmail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			projects = DBUtil.getProjects(userEmail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			supported = DBUtil.getSupported(userEmail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	System.out.println(us.getFirstname()+ projects.size());
    	request.setAttribute("user", us);
    	request.setAttribute("projects", projects);
    	request.setAttribute("supported", supported);
    	request.getRequestDispatcher("view_profile.ftl").forward(request, response);          
    }

}