package com.ice.operationlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class OperationLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationLogApplication.class, args);
    }

}
