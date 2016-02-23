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
public interface AuthorDAOStrategy {


    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    public int deleteAuthor(Object id) throws ClassNotFoundException, SQLException;
    public int updateAuthor(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception;
    public int createAuthor(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception;
    public DBStrategy getDb();
    public void setDb(DBStrategy db);
    
}
