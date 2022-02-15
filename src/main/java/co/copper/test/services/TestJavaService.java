package co.copper.test.services;

import co.copper.test.datamodel.CopperPerson;
import co.copper.test.datamodel.Test;
import co.copper.test.datamodel.external.Person;
import co.copper.test.datamodel.external.Results;
import co.copper.test.storage.CopperPersonRepository;
import co.copper.test.storage.TestJavaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TestJavaService {

    private static final Logger log = LoggerFactory.getLogger(TestJavaService.class);
    private final TestJavaRepository testRepo;
    private final CopperPersonRepository copperPersonRepository;
    private final AsyncHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public TestJavaService(TestJavaRepository testRepo,
                           CopperPersonRepository copperPersonRepository, AsyncHttpClient httpClient
    ) {
        this.testRepo = testRepo;
        this.copperPersonRepository = copperPersonRepository;
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
    }

    public CompletableFuture<String> getOk() {
        log.info(testRepo.findById(1L).orElse(Test.builder().val("No Values Exist").build()).getVal());
        return this.httpClient.prepareGet("https://postman-echo.com/get").execute().toCompletableFuture()
                .handle((res, t) -> res.getResponseBody());
    }

    public void fetchPeople() {
        this.httpClient.prepareGet("https://randomuser.me/api?results=20").execute().toCompletableFuture()
                .handle((res, t) -> processPeopleResponse(res.getResponseBody()));
    }

    private List<CopperPerson> processPeopleResponse(String response) {

        try {
            Results results = objectMapper.readValue(response, Results.class);

            List<CopperPerson> copperPersonList = results.getResults().stream()
                    .map(this::convertToCopperPerson)
                    .collect(Collectors.toList());

            return Streamable.of(copperPersonRepository.saveAll(copperPersonList)).toList();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalStateException("Issue converting response");
        }

    }

    private CopperPerson convertToCopperPerson(Person person) {

//        String encodedPassword = MD5Encoder.encode(person.getLogin().getPassword().getBytes(StandardCharsets.UTF_8));

        return CopperPerson.builder()
                .first(person.getName().getFirst())
                .last(person.getName().getLast())
                .email(person.getEmail())
                .password(person.getLogin().getPassword())
                .build();
    }

    public List<CopperPerson> getCopperPersonList() {
        return copperPersonRepository.findAll();
    }
}
