/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.controller;

import edu.wctc.asc.bookwebapp.service.AuthorService;
import edu.wctc.asc.bookwebapp.exceptions.DataAccessException;
import edu.wctc.asc.bookwebapp.model.Author;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Adam
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String AUTHOR_MAIN = "/authorResponse.jsp";
    private static final String AUTHOR_EDIT_VIEW = "/edit.jsp";
    private static final String AUTHOR_ADD_VIEW = "/add.jsp";
    private static final String HOME = "index.jsp";

    private String driver;
    private String url;
    private String username;
    private String password;
    private String dbJndiName;
    
    private AuthorService authorSrv;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String task = request.getParameter("task");
        String destination = AUTHOR_MAIN;

        

        try {
            
            
            if (task.equals("ViewAuthorList")) {
                this.refreshList(request, authorSrv);
                destination = AUTHOR_MAIN;

            } else if (task.equals("DeleteAuthor")) {
               
                String authorId = (String) request.getParameter("id");
                Author author = authorSrv.findById(authorId);
                authorSrv.remove(author);
                this.refreshList(request, authorSrv);
                destination = AUTHOR_MAIN;

            } else if (task.equals("EditAuthor")) {

                String authorId = (String) request.getParameter("id");
                Author author = authorSrv.findById(authorId);
                request.setAttribute("author", author);
                destination = AUTHOR_EDIT_VIEW;

            } else if (task.equals("Save")) {
                String authorName = request.getParameter("authorName");
                String authorId = request.getParameter("authorId");
                Author author = authorSrv.findById(authorId);
                author.setAuthorName(authorName);
                authorSrv.edit(author);
                this.refreshList(request, authorSrv);
                destination = AUTHOR_MAIN;
            } else if (task.equals("Cancel")) {
                this.refreshList(request, authorSrv);
                destination = AUTHOR_MAIN;

            } else if (task.equals("Add")) {
                System.out.println("Hello");
                destination = AUTHOR_ADD_VIEW;

            } else if (task.equals("AddNewAuthor")) {
                String authorName = request.getParameter("authorName");
                Author author = new Author(0);
                author.setAuthorName(authorName);
                author.setDateAdded(new Date());
                authorSrv.edit(author);
                this.refreshList(request, authorSrv);
                destination = AUTHOR_MAIN;

            } else if (task.equals("color")) {
                String table = request.getParameter("TableColor");
                String text = request.getParameter("TextColor");
                HttpSession session = request.getSession();
                session.setAttribute("tableColor", table);
                session.setAttribute("textColor", text);
                this.refreshList(request, authorSrv);
                destination = HOME;
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(response.encodeURL(destination));
        view.forward(request, response);

    }

    private void refreshList(HttpServletRequest request, AuthorService authorSrv) throws Exception {
        List<Author> authors = authorSrv.findAll();
        request.setAttribute("authors", authors);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() throws ServletException {
        // Ask Spring for object to inject
        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        authorSrv = (AuthorService) ctx.getBean("authorService");

    }
}
