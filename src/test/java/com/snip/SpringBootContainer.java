package com.snip;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.io.File;

public class SpringBootContainer extends GenericContainer<SpringBootContainer> {

    public SpringBootContainer() {
        super(new ImageFromDockerfile()
                .withFileFromFile("target/springboot-testcontainers-snip-1.0-SNAPSHOT.jar", new File("target/springboot-testcontainers-snip-1.0-SNAPSHOT.jar"))
                .withFileFromPath("./Dockerfile", new File("./Dockerfile").toPath()));
        addExposedPort(8080);
    }
}
