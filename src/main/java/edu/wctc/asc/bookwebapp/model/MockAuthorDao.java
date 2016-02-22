/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asc.bookwebapp.model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Adam
 */
public class MockAuthorDao implements AuthorDAOStrategy {

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
    public int updateAuthor(Object id, List colNames, List colValues) throws ClassNotFoundException, SQLException, Exception {
        return 0;

    }

    @Override
    public int createAuthor(Object id) throws ClassNotFoundException, SQLException {
        return 0;
    }

}
