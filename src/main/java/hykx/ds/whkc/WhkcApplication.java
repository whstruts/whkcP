package hykx.ds.whkc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude={TransactionAutoConfiguration.class})
@ComponentScan(value = {"hykx.ds.whkc.rabbitmq"})
@MapperScan("hykx.ds.whkc.mapper")
@EnableScheduling
public class WhkcApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhkcApplication.class, args);
    }
}
