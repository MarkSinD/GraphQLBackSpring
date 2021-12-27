package com.example.graphQLSpingBack.service;

import com.example.graphQLSpingBack.entity.Person;
import com.example.graphQLSpingBack.entity.Vehicle;
import com.example.graphQLSpingBack.exception.PersonNotFoundException;
import com.example.graphQLSpingBack.repository.PersonRepository;
import com.example.graphQLSpingBack.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person createPerson(final Integer id, final String firstName) {
        System.out.println("Service layer");
        final Person person = new Person();
        person.setFirstName(firstName);
        return personRepository.save(person);
    }

    @Transactional
    public Person updateAvatarLocation(int personId, String avatarLocation) {
        Person person = getById(personId);
        person.setAvatar(avatarLocation);
        return person;
    }

    public Person getById(int id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
