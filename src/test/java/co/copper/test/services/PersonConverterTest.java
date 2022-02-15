package co.copper.test.services;

import co.copper.test.datamodel.CopperPerson;
import co.copper.test.datamodel.external.Login;
import co.copper.test.datamodel.external.Name;
import co.copper.test.datamodel.external.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonConverterTest {

    PersonConverter personConverter;

    @BeforeEach
    void setUp() {

        personConverter = new PersonConverter();
    }

    @Test
    void convertToCopperPerson() {

        CopperPerson copperPerson = personConverter.convertToCopperPerson(Person.builder()
                .name(Name.builder().first("joe").last("b").build())
                .email("conor@gmail.com")
                .login(Login.builder().password("123").build())
                .build());

        Assertions.assertThat(copperPerson.getFirst()).isEqualTo("joe");

    }
}