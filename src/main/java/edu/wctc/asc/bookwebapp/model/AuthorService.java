/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Adam
 */
@SessionScoped
public class AuthorService implements Serializable {

    @Inject
    private AuthorDAOStrategy dao;

    public AuthorService() {
    }

    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        return dao.getAuthorList();
    }

    public int deleteAuthorById(Object id) throws SQLException, ClassNotFoundException {
        return dao.deleteAuthor(id);
    }

    public int createAuthorById(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception {
        return dao.createAuthor(id, authorName);
    }

    public int updateAuthorById(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception {
        return dao.updateAuthor(id, authorName);
    }
    public Author getAuthorById(String authorId) throws SQLException, ClassNotFoundException {
        return dao.getAuthorById(Integer.parseInt(authorId));
    }

    public AuthorDAOStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDAOStrategy dao) {
        this.dao = dao;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService srv = new AuthorService();
        List<Author> authors = srv.getAuthorList();
        System.out.println(authors);
    }

}
