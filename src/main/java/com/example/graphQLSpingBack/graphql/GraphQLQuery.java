package com.example.graphQLSpingBack.graphql;

import com.example.graphQLSpingBack.entity.Person;
import com.example.graphQLSpingBack.service.PersonService;
import com.example.graphQLSpingBack.service.VehicleService;
import com.example.graphQLSpingBack.entity.Vehicle;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GraphQLQuery implements GraphQLQueryResolver {
    final private VehicleService vehicleService;
    final private PersonService personService;

    public GraphQLQuery(VehicleService vehicleService, PersonService personService) {
        this.vehicleService = vehicleService;
        this.personService = personService;
    }

    public List<Vehicle> getVehicles(final int count) {
        return vehicleService.getAllVehicles(count);
    }
    public Optional<Vehicle> getVehicle(final int id) {
        return vehicleService.getVehicle(id);
    }
    public Person getPerson(final int id) {
        return personService.getById(id);
    }

}