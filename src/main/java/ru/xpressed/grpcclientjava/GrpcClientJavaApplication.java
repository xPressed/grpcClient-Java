package ru.xpressed.grpcclientjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.xpressed.grpcclientjava.security.SecurityConfiguration;
import ru.xpressed.grpcclientjava.server.UserRequest;

@SpringBootApplication
public class GrpcClientJavaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientJavaApplication.class, args);
    }

    @Autowired
    SecurityConfiguration securityConfiguration;

    @Override
    public void run(String... args) throws Exception {
        UserRequest.add("admin", securityConfiguration.encoder().encode("123"));
    }
}
