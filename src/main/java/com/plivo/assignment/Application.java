package com.plivo.assignment;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

import lombok.extern.slf4j.*;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
