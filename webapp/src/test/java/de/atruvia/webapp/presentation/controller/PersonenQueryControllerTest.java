package de.atruvia.webapp.presentation.controller;

import de.atruvia.webapp.presentation.dto.PersonDto;
import de.atruvia.webapp.service.PersonenService;
import de.atruvia.webapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenQueryControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService personenServiceMock;
    @Test
    void findByIdTest() throws Exception{

        // Arrange
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        // RecordModus
        when(personenServiceMock.findeNachId(anyString())).thenReturn(optionalPerson);
        // Act
        PersonDto personDto = restTemplate.getForObject("/v1/personen/xyz", PersonDto.class);



        // Assert
        assertEquals("John", personDto.getVorname());
        verify(personenServiceMock, times(1)).findeNachId("xyz");
    }
    @Test
    void test2() throws Exception{

        // Arrange
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        // RecordModus
        when(personenServiceMock.findeNachId(anyString())).thenReturn(optionalPerson);
        // Act
        String personDto = restTemplate.getForObject("/v1/personen/xyz", String.class);


        // Assert
        System.out.println(personDto);
    }

    @Test
    void test3() throws Exception{

        // Arrange
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        // RecordModus
        when(personenServiceMock.findeNachId(anyString())).thenReturn(optionalPerson);
        // Act
        ResponseEntity<PersonDto> entity = restTemplate.getForEntity("/v1/personen/xyz", PersonDto.class);

        PersonDto personDto = entity.getBody();

        // Assert
        assertEquals("John", personDto.getVorname());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    void test4() throws Exception{

        // Arrange

        // RecordModus
        when(personenServiceMock.findeNachId(anyString())).thenReturn(Optional.empty());
        // Act
        //ResponseEntity<PersonDto> entity = restTemplate.getForEntity("/v1/personen/xyz", PersonDto.class);
        ResponseEntity<PersonDto> entity = restTemplate.exchange("/v1/personen/xyz", HttpMethod.GET,null, PersonDto.class);

        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }
    @Test
    void test5() throws Exception{

        // Arrange
        var personen = List.of(
                Person.builder().id("1").vorname("John").nachname("Doe").build(),
                Person.builder().id("2").vorname("John").nachname("Rambo").build(),
                Person.builder().id("3").vorname("John").nachname("Wayne").build()
        );
        // RecordModus
        when(personenServiceMock.findeAlle()).thenReturn(personen);
        // Act
        ResponseEntity<List<PersonDto>> entity = restTemplate.exchange("/v1/personen", HttpMethod.GET,null, new ParameterizedTypeReference<List<PersonDto>>() { });

        var liste = entity.getBody();

        assertEquals(3, liste.size());
    }
}