/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Adam
 */
public class AuthorService {
    
    private AuthorDAOStrategy dao = new AuthorDao();
    public List<Author>getAuthorList() throws ClassNotFoundException, SQLException{
        return dao.getAuthorList();
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorService srv = new AuthorService();
        List<Author> authors = srv.getAuthorList();
        System.out.println(authors);
    }
    
}
