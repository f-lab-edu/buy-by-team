package com.flab.bbt;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {
    static final String MYSQL_IMAGE = "mysql:8.0";
    static final MySQLContainer MYSQL_CONTAINER;

    static {
        MYSQL_CONTAINER = new MySQLContainer(MYSQL_IMAGE);
        MYSQL_CONTAINER.start();
    }
}
