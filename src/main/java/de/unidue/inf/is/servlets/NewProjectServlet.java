package de.unidue.inf.is.servlets;
import de.unidue.inf.is.domain.Project;
import de.unidue.inf.is.utils.DBUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class NewProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static List<Project> projects = new ArrayList<>();
    private Project project;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
    	String userEmail = request.getParameter("user");
    	try {
			projects = DBUtil.getProjects(userEmail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	request.setAttribute("projects", projects);
        request.getRequestDispatcher("new_project.ftl").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String financeLimit = request.getParameter("financeLimit");
        
        String catHealthWellness = request.getParameter("healthWellness");
        String catArtCreativeWork = request.getParameter("artCreativeWork");
        String catEducation = request.getParameter("education");
        String catTechInnovation = request.getParameter("techInnovation");
        String vor = request.getParameter("vor");
        String btnCreate = request.getParameter("Create");
        
        String[] categories ={catHealthWellness,catArtCreativeWork,
        catEducation,catTechInnovation};
        System.out.println("TEST " + title);
        try {
			DBUtil.insertProject(request.getParameter("user"),title,description,financeLimit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
        request.getRequestDispatcher("view_main.ftl").forward(request, response);
    }
}
