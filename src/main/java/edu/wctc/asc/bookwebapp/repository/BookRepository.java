package edu.wctc.asc.bookwebapp.repository;


import edu.wctc.asc.bookwebapp.model.Book;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jlombardo
 */
public interface BookRepository extends JpaRepository<Book, Integer>, Serializable {
    
}
