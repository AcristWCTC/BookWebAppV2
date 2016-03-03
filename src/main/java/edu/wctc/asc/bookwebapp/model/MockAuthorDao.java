/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Adam
 */
@Dependent
@Alternative
public class MockAuthorDao implements AuthorDAOStrategy, Serializable {

    private DBStrategy db;
    Date date = new Date();

    private Author author1 = new Author(27, "Bob Ross", date);
    private Author author2 = new Author(31, "Bill Gates", date);
    private Author author3 = new Author(93, "Darth Vader", date);
    private List<Author> authorList = asList(author1, author2, author3);

    @Override
    public List getAuthorList() {
        return authorList;
    }

    ;
    @Override
    public int deleteAuthor(Object id) {
        authorList.remove(id);
        return 1;
    }

    @Override
    public int updateAuthor(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception {
        return 0;

    }

    @Override
    public int createAuthor(Object id, String authorName) throws ClassNotFoundException, SQLException {
        return 0;
    }

    @Override
    public Author getAuthorById(Integer authorId) throws ClassNotFoundException, SQLException {
        return null;
    }

    public DBStrategy getDb() {
        return db;
    }

    public void setDb(DBStrategy db) {
        this.db = db;
    }

    @Override
    public void initDao(String driver, String url, String user, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDriver(String driver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassword(String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
