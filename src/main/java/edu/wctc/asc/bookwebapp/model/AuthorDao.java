/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Adam
 */
@Dependent
public class AuthorDao implements AuthorDAOStrategy, Serializable {

    @Inject
    private DBStrategy db;

    private String driver;
    private String url;
    private String username;
    private String password;

    public AuthorDao() {
    }

    @Override
    public void initDao(String driver, String url, String username, String password) {
        setDriver(driver);
        setUrl(url);
        setUsername(username);
        setPassword(password);
    }

    public int deleteAuthor(Object id) throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, username, password);
        int result = db.deleteRecordByPrimaryKey("author", "author_id", id);
        db.closeConnection();
        return result;
    }

    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, username, password);

        List<Map<String, Object>> rawData = db.retreiveAllRecordsForTable("author", 0);
        List<Author> authors = new ArrayList<>();

        for (Map rec : rawData) {
            Author author = new Author();
            Integer id = new Integer(rec.get("author_id").toString());
            author.setAuthorId(id);
            String name = rec.get("author_name") == null ? "" : rec.get("author_name").toString();
            author.setAuthorName(name);
            Date date = rec.get("date_added") == null ? null : (Date) rec.get("date_added");
            author.setDateAdded(date);
            authors.add(author);
        }

        db.closeConnection();
        return authors;
    }

    @Override
    public int updateAuthor(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception {
        db.openConnection(driver, url, username, password);
        int result = db.updateRecords("author", Arrays.asList("author_name"), 
                                       Arrays.asList(authorName),
                                       "author_id", id);
        db.closeConnection();
        return result;

    }

    @Override
    public int createAuthor(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception {
        db.openConnection(driver, url, username, password);
        int result = db.insertRecord("author", Arrays.asList("author_name","date_added"), 
                                      Arrays.asList(authorName,new Date()));
        db.closeConnection();
        return result;

    }
     @Override
     public Author getAuthorById(Integer authorId)throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, username, password);
        
        Map<String,Object> rawRec = db.findById("author", "author_id", authorId);
        Author author = new Author();
        author.setAuthorId((Integer)rawRec.get("author_id"));
        author.setAuthorName(rawRec.get("author_name").toString());
        author.setDateAdded((Date)rawRec.get("date_added"));
        
        return author;
    }

    @Override
    public DBStrategy getDb() {
        return db;
    }

    @Override
    public void setDb(DBStrategy db) {
        this.db = db;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

}
