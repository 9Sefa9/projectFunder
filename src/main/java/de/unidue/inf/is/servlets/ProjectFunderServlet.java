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
import de.unidue.inf.is.utils.DBUtil;



/**
 * Das könnte die Eintrittsseite sein.
 * AUFRUFBAR MIT http://localhost:9061/hello
 */
public final class ProjectFunderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static List<Project> projects = new ArrayList<>();
    private static List<Project> finishedProjects = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	projects = DBUtil.getProjects();
    	request.setAttribute("projects", projects);
    	request.setAttribute("fprojects", finishedProjects);
        boolean databaseExists = DBUtil.checkDatabaseExistsExternal();

        if (databaseExists) {
            request.setAttribute("db2exists", "vorhanden! Supi!");
        }
        else {
            request.setAttribute("db2exists", "nicht vorhanden :-(");
        }
        request.getRequestDispatcher("view_main.ftl").forward(request, response);
    }

}
