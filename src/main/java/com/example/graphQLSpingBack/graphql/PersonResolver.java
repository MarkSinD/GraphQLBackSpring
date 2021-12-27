package com.example.graphQLSpingBack.graphql;

import com.example.graphQLSpingBack.entity.Person;
import com.example.graphQLSpingBack.service.PersonAvatarService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
public class PersonResolver implements GraphQLResolver<Person> {
    private final PersonAvatarService avatarService;


    public PersonResolver(PersonAvatarService avatarService) {
        this.avatarService = avatarService;
    }

    public String getAvatar(Person person) {
        return avatarService.getPublicURL(person.getAvatar());
    }

}
