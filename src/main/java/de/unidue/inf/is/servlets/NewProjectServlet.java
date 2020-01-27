package de.unidue.inf.is.servlets;
import de.unidue.inf.is.domain.Project;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class NewProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Project project;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
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
        String catUbuntuTouch = request.getParameter("UbuntuTouch");
        String catUbuntuTouchPro = request.getParameter("UbuntuTouchPro"); 
        String catNoPredecessor = request.getParameter("NoPredecessor");
        String btnCreate = request.getParameter("Create");
        
        String[] categories ={catHealthWellness,catArtCreativeWork,
        catEducation,catTechInnovation,catUbuntuTouch,catUbuntuTouchPro,catNoPredecessor};
        System.out.println(description);
       // this.project = new Project(title, )
    }
}
