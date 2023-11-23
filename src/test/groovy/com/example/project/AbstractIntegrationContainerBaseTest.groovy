package com.example.project

import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import spock.lang.Specification


@SpringBootTest
abstract class AbstractIntegrationContainerBaseTest extends Specification {
    static final GenericContainer MY_REDIS_CONTAIER

    static {
        MY_REDIS_CONTAIER = new GenericContainer<>("redis:6")
            .withExposedPorts(6379)

        MY_REDIS_CONTAIER.start()

        System.setProperty("spring.redis.host",MY_REDIS_CONTAIER.getHost())
        System.setProperty("spring.redis.port",MY_REDIS_CONTAIER.getMappedPort(6379).toString())

    }

}
