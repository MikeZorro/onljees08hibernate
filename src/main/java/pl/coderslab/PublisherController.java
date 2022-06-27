package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublisherController {
    private final PublisherDAO publisherDAO;

    public PublisherController(PublisherDAO publisherDAO) {
        this.publisherDAO = publisherDAO;
    }

    @RequestMapping("/publisher")
    @ResponseBody
    public void hello() {
        Publisher publisher = new Publisher();
        publisher.setName("Mikes & Co");
        publisherDAO.savePublisher(publisher);
    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id) {
        Publisher publisher = publisherDAO.findById(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/update/{id}/{name}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDAO.findById(id);
        publisher.setName(name);
        publisherDAO.update(publisher);
        return publisher.toString();
    }

    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Publisher publisher = publisherDAO.findById(id);
        publisherDAO.delete(publisher);
        return "deleted";
    }
}
