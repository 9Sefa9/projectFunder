package de.unidue.inf.is.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.utils.DBUtil;



/**
 * Das könnte die Eintrittsseite sein.
 * AUFRUFBAR MIT http://localhost:9061/hello
 */
public final class ViewMainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
  
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
