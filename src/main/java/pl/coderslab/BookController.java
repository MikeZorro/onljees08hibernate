package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private final BookDAO bookDAO;
    private final PublisherDAO publisherDAO;
    private final AuthorDAO authorDAO;

    public BookController(BookDAO bookDAO, PublisherDAO publisherDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.publisherDAO = publisherDAO;
        this.authorDAO = authorDAO;
    }

    @RequestMapping("/")
    @ResponseBody
    public void hello(){
        Book book = new Book();
        book.setTitle("Yeah Hibernate");
        Author author1 = authorDAO.findById(3);
        Author author2 = authorDAO.findById(2);
        List<Author> newList = new ArrayList<>();
        newList.add(author1);
        newList.add(author2);
        book.setAuthorList(newList);

        bookDAO.saveBook(book);
    }
    @RequestMapping("/add")
    @ResponseBody
    public void helloWithPublisher(){
        Book book = new Book();
        book.setTitle("Yeah Hibernate");
        Publisher publisher = new Publisher();
        publisher.setName("Helion");
        publisherDAO.savePublisher(publisher);
        book.setPublisher(publisher);
        bookDAO.saveBook(book);
    }
    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDAO.findById(id);
        return book.toString();
    }

    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title ) {
        Book book = bookDAO.findById(id);
        book.setTitle(title);
        bookDAO.update(book);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDAO.findById(id);
        bookDAO.delete(book);
        return "deleted";
    }
    @RequestMapping("/book/anypublisher")
    @ResponseBody
    public String getAnyPublisher() {
        List<Book> list = bookDAO.findAllWithAnyPublisher();
        return list.toString();
    }
    @RequestMapping("/book/bypublisher/{publisherId}")
    @ResponseBody
    public String getThisPublisher(@PathVariable int publisherId) {
        Publisher publisher = publisherDAO.findById(publisherId);
        List <Book> list = bookDAO.findAllByPublisher(publisher);
        return list.toString();
    }
    @RequestMapping("/book/byauthor/{authorid}")
    @ResponseBody
    public String getThisAuthor(@PathVariable long authorid) {
        Author author = authorDAO.findById(authorid);
        List <Book> list = bookDAO.findBooksWithAuthor(author);
        return list.toString();

    }
}
