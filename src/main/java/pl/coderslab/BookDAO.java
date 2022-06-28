package pl.coderslab;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book));
    }
    public List<Book> getAll(){
        return entityManager
                .createQuery("select  b from Book b")
                .getResultList();
    }

    public List<Book> findAllByRating(int rating){
        return  entityManager
                .createQuery("select b from Book b where b.rating =: rating")
                .setParameter("rating",rating)
                .getResultList();
    }
    public List<Book> findAllWithAnyPublisher(){
        return  entityManager
                .createQuery("select b from Book b join  b.publisher")
                .getResultList();
    }
    public List<Book> findAllByPublisher(Publisher publisher){
        return  entityManager
                .createQuery("select b from Book b where b.publisher =: publisher")
                .setParameter("publisher", publisher)
                .getResultList();
    }
    public List<Book> findBooksWithAuthor(Author author) {
        return entityManager
                .createQuery("SELECT distinct b FROM Book b join FETCH b.authorList " +
                        "WHERE :author member of b.authorList")
                .setParameter("author", author)
                .getResultList();
    }
}
