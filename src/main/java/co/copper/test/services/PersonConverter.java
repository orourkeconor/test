package co.copper.test.services;

import co.copper.test.datamodel.CopperPerson;
import co.copper.test.datamodel.external.Person;
import org.apache.tomcat.util.security.MD5Encoder;

import java.nio.charset.StandardCharsets;

public class PersonConverter {


    public CopperPerson convertToCopperPerson(Person person) {

        String encodedPassword = MD5Encoder.encode(person.getLogin().getPassword().getBytes(StandardCharsets.UTF_8));

        return CopperPerson.builder()
                .first(person.getName().getFirst())
                .last(person.getName().getLast())
                .email(person.getEmail())
                .password(encodedPassword)
                .build();
    }
}
