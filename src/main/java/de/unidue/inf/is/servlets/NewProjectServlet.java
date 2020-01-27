package de.unidue.inf.is.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public final class NewProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        request.getRequestDispatcher("new_project.ftl").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String title = request.getParameter("title");
        String financeLimit = request.getParameter("financeLimit");
        
        String katHealthWellness = request.getParameter("healthWellness");
        String katArtCreativeWork = request.getParameter("artCreativeWork");
        String katEducation = request.getParameter("education");
        String katTechInnovation = request.getParameter("techInnovation");
        String katUbuntuTouch = request.getParameter("UbuntuTouch");
        String katUbuntuTouchPro = request.getParameter("UbuntuTouchPro"); 
        String katNoPredecessor = request.getParameter("NoPredecessor");
        String btnCreate = request.getParamter("Create");
        doGet(request,response);
    }
}
