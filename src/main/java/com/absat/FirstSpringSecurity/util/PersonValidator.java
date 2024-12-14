package com.absat.FirstSpringSecurity.util;

import com.absat.FirstSpringSecurity.models.Person;
import com.absat.FirstSpringSecurity.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> personOptional = personService.findByUsername(person.getUsername());
        if (personOptional.isPresent()) {
            errors.rejectValue("username", null, "Username already exists");
        }
    }
}
