package com.snip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractControllerTest {

    private static final Logger LOG = LogManager.getLogger(AbstractControllerTest.class);

    public static Network network;

    public static PostgreSQLContainer postgreSQLContainer;

    public static SpringBootContainer springBootContainer;

    static {
        network = Network.newNetwork();

        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer()
                .withNetwork(network)
                .withNetworkAliases("postgres")
                .withLogConsumer(new Log4jConsumer(LOG));
        postgreSQLContainer.start();

        initDatabase();

        springBootContainer = new SpringBootContainer();
        springBootContainer.withNetwork(network)
                .withLogConsumer(new Log4jConsumer(LOG))
                .withEnv("DB_HOST", "postgres")
                .withEnv("DB_PORT", "5432")
                .withEnv("DB_NAME", "test")
                .withEnv("DB_USERNAME", "test")
                .withEnv("DB_PASSWORD", "test")
                .start();
    }

    public static void initDatabase() {
        performQuery(postgreSQLContainer, "CREATE TABLE Person (id integer NOT NULL, name character varying(250) NOT NULL);");
        performQuery(postgreSQLContainer, "INSERT INTO Person (id, name) VALUES (1, 'Steve');");
    }

    protected static void performQuery(PostgreSQLContainer containerRule, String sql) {
        Statement statement;
        try {
            statement = containerRule.createConnection("").createStatement();
            boolean result = statement.execute(sql);
            LOG.info("Execute statement result: {}", result);
        } catch (SQLException e) {
            LOG.error("SQLException: {}", e.getMessage());
        }
    }
}
