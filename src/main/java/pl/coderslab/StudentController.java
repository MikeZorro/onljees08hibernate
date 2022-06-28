package pl.coderslab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;


@Controller
public class StudentController {

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }
    @ModelAttribute("programmingSkills")
    public List<String> programmingSkills() {
        return Arrays.asList("Java", "Python", "JavaScript", "AWS", "Git");
    }
    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        return Arrays.asList("Dzierganie", "Dzemy i konfitury", "hodowla golebi", "wiertarki", "rowy i melioracja");
    }
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    public String simple(Model model) {
        Student student = new Student();
        student.setFirstName("Mike");
        student.setLastName("Magic");
        student.setGender("Male");
        student.setProgrammingSkills(programmingSkills());
        model.addAttribute("student", student);
        return "/simple";
    }

    @RequestMapping(value = "/simple", method = RequestMethod.POST)
    public String processSimple(Student student, Model model) {
        model.addAttribute("student", student);
        return "/success";
    }
}
