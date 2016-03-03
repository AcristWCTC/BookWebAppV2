/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.controller;

import edu.wctc.asc.bookwebapp.model.Author;
import edu.wctc.asc.bookwebapp.model.AuthorService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adam
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String DEST_PAGE = "/authorResponse.jsp";
    private static final String AUTHOR_EDIT_VIEW = "/edit.jsp";
    private static final String AUTHOR_ADD_VIEW = "/add.jsp";

    private String driver;
    private String url;
    private String username;
    private String password;

    @Inject
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String task = request.getParameter("task");
        String destination = DEST_PAGE;

        configDbConnection();

        try {
            if (task.equals("ViewAuthorList")) {
                this.refreshList(request, authorSrv);
                destination = DEST_PAGE;

            } else if (task.equals("DeleteAuthor")) {
                System.out.println("TESTING");
                String authorId = (String) request.getParameter("id");
                int i = authorSrv.deleteAuthorById(authorId);
                request.setAttribute("authorsDeleted", i);
                this.refreshList(request, authorSrv);
                destination = DEST_PAGE;

            } else if (task.equals("EditAuthor")) {

                String authorId = (String) request.getParameter("id");
                Author author = authorSrv.getAuthorById(authorId);
                request.setAttribute("author", author);
                destination = AUTHOR_EDIT_VIEW;

            } else if (task.equals("Save")) {
                System.out.println("HI");
                String authorName = request.getParameter("authorName");
                String authorId = request.getParameter("authorId");
                authorSrv.updateAuthorById(authorId, authorName);
                this.refreshList(request, authorSrv);
                destination = DEST_PAGE;
            } else if (task.equals("Cancel")) {
                this.refreshList(request, authorSrv);
                destination = DEST_PAGE;

            } else if (task.equals("Add")) {
                destination = AUTHOR_ADD_VIEW;

            } else if (task.equals("AddNewAuthor")) {
                String authorName = request.getParameter("authorName");
                String dateAdded = request.getParameter("date");
                authorSrv.createAuthorById(null, authorName);
                this.refreshList(request, authorSrv);
                destination = DEST_PAGE;
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(response.encodeURL(destination));
        view.forward(request, response);

    }

    private void configDbConnection() {
        authorSrv.getDao().initDao(driver, url, username, password);
    }

    private void refreshList(HttpServletRequest request, AuthorService authorSrv) throws Exception {
        List<Author> authors = authorSrv.getAuthorList();
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
        driver = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        username = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
    }
}
