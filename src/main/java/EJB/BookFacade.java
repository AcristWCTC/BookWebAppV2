/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import edu.wctc.asc.bookwebapp.model.Author;
import edu.wctc.asc.bookwebapp.model.Book;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adam
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {


    @PersistenceContext(unitName = "edu.wctc.asc_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }

    public void deleteBookById(String id) {
        Book book = this.find(new Integer(id));
        this.remove(book);
    }

    public void updateBook(String id, String name, String isbn, Author authorId) {
        Book book = new Book();
        if (id == null) {
            book.setTitle(name);
            book.setIsbn(isbn);
            book.setAuthorId(authorId);
        } else {
            book.setBookId(new Integer(id));
            book.setTitle(name);
            book.setIsbn(isbn);
            book.setAuthorId(authorId);
        }

        this.getEntityManager().merge(book);
    }

}
