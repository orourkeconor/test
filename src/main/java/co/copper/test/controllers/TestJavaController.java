package co.copper.test.controllers;

import co.copper.test.datamodel.CopperPerson;
import co.copper.test.services.TestJavaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class TestJavaController {

    private static final Logger log = LoggerFactory.getLogger(TestJavaController.class);

    private TestJavaService testJavaService;

    @Autowired
    public TestJavaController(TestJavaService testJavaService) {
        this.testJavaService = testJavaService;
    }

    @GetMapping("/test")
    public CompletableFuture<String> getTestObject() {
        return testJavaService.getOk();
    }

    @PostMapping("/people")
    public ResponseEntity<String> fetchPeople() {
        testJavaService.fetchPeople();
        return ResponseEntity.accepted().body("Fetching people in progress...");
    }

    @GetMapping("/getCopperPersonList")
    public List<CopperPerson> getCopperPersonList() {
        return testJavaService.getCopperPersonList();
    }

}
