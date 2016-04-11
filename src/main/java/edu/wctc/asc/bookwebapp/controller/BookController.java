/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.controller;

import edu.wctc.asc.bookwebapp.exceptions.DataAccessException;
import edu.wctc.asc.bookwebapp.model.Author;
import edu.wctc.asc.bookwebapp.model.Book;
import edu.wctc.asc.bookwebapp.service.AuthorService;
import edu.wctc.asc.bookwebapp.service.BookService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    private BookService bookSrv;
    private AuthorService as;

    private static final String BOOK_MAIN = "/bookResponse.jsp";
    private static final String BOOK_EDIT_VIEW = "/bookEdit.jsp";
    private static final String BOOK_ADD_VIEW = "/bookAdd.jsp";
    private static final String HOME = "index.jsp";

    private String driver;
    private String url;
    private String username;
    private String password;
    private String dbJndiName;

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
        String destination = BOOK_MAIN;

        try {

            if (task.equals("ViewBookList")) {
                this.refreshList(request, bookSrv);
                destination = BOOK_MAIN;

            } else if (task.equals("DeleteBook")) {

                String bookId = (String) request.getParameter("id");
                Book book = bookSrv.findById(bookId);
                bookSrv.remove(book);
                this.refreshList(request, bookSrv);
                destination = BOOK_MAIN;

            } else if (task.equals("EditBook")) {

                String bookId = (String) request.getParameter("id");
                Book book = bookSrv.findById(bookId);
                request.setAttribute("book", book);
                request.setAttribute("authorsList", as.findAll());
                destination = BOOK_EDIT_VIEW;

            } else if (task.equals("Save")) {
                String title = request.getParameter("title");
                String bookId = request.getParameter("bookId");
                String isbn = request.getParameter("isbn");
                String authorId = request.getParameter("authorId");
                Author author = as.findById(authorId);
                //bookSrv.updateBook(bookId, title, isbn, author);
                this.refreshList(request, bookSrv);
                destination = BOOK_MAIN;
                
            } else if (task.equals("Cancel")) {
                this.refreshList(request, bookSrv);
                destination = BOOK_MAIN;

            } else if (task.equals("Add")) {
                request.setAttribute("authorsList", as.findAll());
                destination = BOOK_ADD_VIEW;

            } else if (task.equals("AddNewBook")) {
                String isbn = request.getParameter("ISBN");
                String title = request.getParameter("title");
                String authorId = request.getParameter("author");
                Author author = as.findById(authorId);
                Book book = new Book();
                book.setTitle(title);
                book.setAuthorId(author);
                book.setIsbn(isbn);
                //bookSrv.create(book);
                this.refreshList(request, bookSrv);
                destination = BOOK_MAIN;

            } else if (task.equals("color")) {
                String table = request.getParameter("TableColor");
                String text = request.getParameter("TextColor");
                HttpSession session = request.getSession();
                session.setAttribute("tableColor", table);
                session.setAttribute("textColor", text);
                this.refreshList(request, bookSrv);
                destination = HOME;
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(response.encodeURL(destination));
        view.forward(request, response);

    }

    private void refreshList(HttpServletRequest request, BookService bookSrv) throws Exception {
        List<Book> books = bookSrv.findAll();
        request.setAttribute("books", books);
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
        as = (AuthorService) ctx.getBean("authorService");
        bookSrv = (BookService) ctx.getBean("bookService");
    }
}
