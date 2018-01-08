package com.snip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "snip")
public class Controller {

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/hello/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> helloPerson(@PathVariable int id) {
        LOG.info("Start helloPerson with id: {}", id);
        Person person = personRepository.findOne(id);
        LOG.info("Result: {}", person);
        return new ResponseEntity<>("Hello " + person.getName(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/people", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Person>> people() {
        LOG.info("Start people");
        Iterable<Person> people = personRepository.findAll();
        LOG.info("Result: {}", people);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, path = "/people", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person) {
        LOG.info("Start create whit requestBody: {}", person);
        Person response = personRepository.save(person);
        LOG.info("Resource saved: {}", response);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
}