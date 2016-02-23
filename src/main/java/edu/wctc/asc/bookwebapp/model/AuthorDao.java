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
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Adam
 */
@SessionScoped
public class AuthorDao implements AuthorDAOStrategy, Serializable {
    @Inject
    private DBStrategy db;
   

    private final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/book";
    private final String USERNAME = "root";
    private final String PASSWORD = "admin";

    public AuthorDao() {
    }
    


    public int deleteAuthor(Object id) throws ClassNotFoundException, SQLException {
        db.openConnection(DRIVER_CLASS, URL, USERNAME, PASSWORD);
        int result = db.deleteRecordByPrimaryKey("author", "author_id", id);
        db.closeConnection();
        return result;
    }

    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        db.openConnection(DRIVER_CLASS, URL, USERNAME, PASSWORD);

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
        db.openConnection(DRIVER_CLASS, URL, USERNAME, PASSWORD);
        int result = db.updateRecords("author", Arrays.asList("author_name","date_added"), Arrays.asList(authorName), "author_id", id);
        db.closeConnection();
        return result;

    }

    @Override
    public int createAuthor(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception {       
        db.openConnection(DRIVER_CLASS, URL, USERNAME, PASSWORD);
        int result = db.updateRecords("author", Arrays.asList("author_name","date_added"),  Arrays.asList(authorName,new Date()), "author_id", id);
        db.closeConnection();
        return result;

    }

    public DBStrategy getDb() {
        return db;
    }

    public void setDb(DBStrategy db) {
        this.db = db;
    }
    
    

}
