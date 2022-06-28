package pl.coderslab;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;
    private String gender;
    private String country;
    private String notes;
    private boolean mailingList;
    private List<String> programmingSkills;
    private List<String> hobbies;
}
