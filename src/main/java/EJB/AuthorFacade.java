/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import edu.wctc.asc.bookwebapp.model.Author;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adam
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "edu.wctc.asc_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    public void deleteAuthorById(String id){
        Author author = this.find(new Integer(id));
        this.remove(author);
    }
    public void updateAuthor(String id, String name, String date){
        Author author = new Author();
        if(id==null){
            author.setAuthorName(name);
            author.setDateAdded(new Date(date));
        } else {
            author.setAuthorId(new Integer(id));
            author.setAuthorName(name);
            author.setDateAdded(new Date(date));
        }
        this.getEntityManager().merge(author);    }
    
}