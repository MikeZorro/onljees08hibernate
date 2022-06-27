package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {
    private PersonDAO personDAO;
    private PersonDetailsDAO personDetailsDAO;

    public PersonController(PersonDAO personDAO, PersonDetailsDAO personDetailsDAO) {
        this.personDAO = personDAO;
        this.personDetailsDAO = personDetailsDAO;
    }

    @RequestMapping("/person")
    @ResponseBody
    public void hello(){
        Person person= new Person();
        person.setLogin("Yo");
        person.setEmail("yo@wp.pl");
        PersonDetails personDetails = new PersonDetails();
        personDetails.setCity("Wroclaw");
        personDetailsDAO.savePersonDetails(personDetails);
        person.setPersonDetails(personDetails);
        personDAO.savePerson(person);
    }

    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id) {
        Person person = personDAO.findById(id);
        return person.toString();
    }

    @RequestMapping("/person/update/{id}/{lastName}")
    @ResponseBody
    public String updatePerson(@PathVariable long id, @PathVariable String lastName ) {
        Person person = personDAO.findById(id);
        person.setLogin(lastName);
        personDAO.update(person);
        return person.toString();
    }

    @RequestMapping("/person/delete/{id}")
    @ResponseBody
    public String deletePerson(@PathVariable long id) {
        Person person = personDAO.findById(id);
        personDAO.delete(person);
        return "deleted";
    }
}
