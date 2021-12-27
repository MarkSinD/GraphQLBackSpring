package com.example.graphQLSpingBack.graphql;

import com.example.graphQLSpingBack.entity.Person;
import com.example.graphQLSpingBack.service.PersonAvatarService;
import com.example.graphQLSpingBack.service.PersonService;
import com.example.graphQLSpingBack.service.VehicleService;
import com.example.graphQLSpingBack.entity.Vehicle;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.io.IOException;

/**
 * todo Document type GraphQLMutation
 */

@Component
public class GraphQLMutation implements GraphQLMutationResolver {

    private final VehicleService vehicleService;
    private final PersonService personService;
    private final PersonAvatarService avatarService;

    private String status;

    public GraphQLMutation(VehicleService vehicleService, PersonService personService, PersonAvatarService avatarService) {
        this.vehicleService = vehicleService;
        this.personService = personService;
        this.avatarService = avatarService;
    }

    public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
        return vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
    public Person createPerson(final String firstName){
        Integer id = null;
        System.out.println("createPerson name = " + firstName);
        return personService.createPerson(id, firstName);
    }

    public Person update(Part part, DataFetchingEnvironment environment) throws IOException {
        Person person = new Person();
        person.setId(1);
        person.setFirstName("firstName");
        boolean isDeleted = avatarService.deleteAvatar(person.getAvatar());
        String newLocation = avatarService.updateAvatar(person.getId(), environment.getArgument("file"));
        System.out.println("isDeleted = " + isDeleted);
        return personService.updateAvatarLocation(person.getId(), newLocation);
    }

    public Person createPersonWithId(final int id, final String firstName) {
        Person person = new Person();
        person.setFirstName(firstName);
        System.out.println("Mutation");
        return person;
    }

}
