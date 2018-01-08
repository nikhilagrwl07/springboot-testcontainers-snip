package com.snip;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class ControllerTest extends AbstractControllerTest {

    private static final Logger LOG = LogManager.getLogger(ControllerTest.class);

    @BeforeClass
    public static void setUpURI() {
        RestAssured.baseURI = "http://" + springBootContainer.getContainerIpAddress();
        RestAssured.port = springBootContainer.getMappedPort(8080);
        RestAssured.basePath = "snip";
    }

    @Test
    public void helloPersonTest() {
        when().get("/hello/1")
                .then()
                .body(equalTo("Hello Steve"))
                .statusCode(200);
    }

    @Test
    public void peopleTest() {
        Response response = when().get("/people");
        assertEquals("[{\"id\":1,\"name\":\"Steve\"}]", response.getBody().asString());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void createTest() {
        Person personToSave = new Person();
        personToSave.setId(2);
        personToSave.setName("Stefano");

        given().body(personToSave)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .post("/people")
                .then()
                .body(equalTo("{\"id\":2,\"name\":\"Stefano\"}"))
                .statusCode(201);
    }
}