package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonDetailsController {
    private PersonDAO personDAO;
    private PersonDetailsDAO personDetailsDAO;


    @RequestMapping("/personDetails")
    @ResponseBody
    public void hello(){
        PersonDetails personDetails = new PersonDetails();
        personDetails.setCity("Warszawa");
        personDetailsDAO.savePersonDetails(personDetails);
    }
    @RequestMapping("/personDetails/get/{id}")
    @ResponseBody
    public String getPersonDetails(@PathVariable long id) {
        PersonDetails personDetails = personDetailsDAO.findById(id);
        return personDetails.toString();
    }

    @RequestMapping("/personDetails/update/{id}/{lastName}")
    @ResponseBody
    public String updatePersonDetails(@PathVariable long id, @PathVariable String city ) {
        PersonDetails personDetails = personDetailsDAO.findById(id);
        personDetails.setCity(city);
        personDetailsDAO.update(personDetails);
        return personDetails.toString();
    }

    @RequestMapping("/personDetails/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        PersonDetails personDetails = personDetailsDAO.findById(id);
        personDetailsDAO.delete(personDetails);
        return "deleted";
    }
}
