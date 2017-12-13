package com.snip;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;


import java.io.File;

import static com.jayway.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;

@Ignore
public class ControllerTest {

    @Rule
    public GenericContainer container = new GenericContainer(
            new ImageFromDockerfile()
                    .withFileFromFile("target/springboot-testcontainers-snip-1.0-SNAPSHOT.jar", new File("target/springboot-testcontainers-snip-1.0-SNAPSHOT.jar"))
                    .withFileFromPath("./Dockerfile", new File("./Dockerfile").toPath())
    )
            .withExposedPorts(8080);

    @Before
    public void setup() {
        RestAssured.baseURI = "http://" + container.getContainerIpAddress() + ":" + container.getMappedPort(8080);
    }

    @Test
    public void testHello() {
        Response response = get("/snip/hello");
        assertEquals(response.statusCode(), 200);
    }
}