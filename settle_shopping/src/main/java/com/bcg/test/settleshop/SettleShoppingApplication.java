package com.bcg.test.settleshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EntityScan(basePackages = "com.bcg.test.settleshop.entity.*")
@SpringBootApplication(scanBasePackages = {"com.bcg.test.settleshop"}, exclude = {DataSourceAutoConfiguration.class})
public class SettleShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SettleShoppingApplication.class, args);
    }

}
