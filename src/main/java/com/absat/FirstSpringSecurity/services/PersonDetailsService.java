package com.absat.FirstSpringSecurity.services;


import com.absat.FirstSpringSecurity.models.Person;
import com.absat.FirstSpringSecurity.repositories.PeopleRepository;
import com.absat.FirstSpringSecurity.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> getPeopleByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new PersonDetails(person.get());
    }
}
