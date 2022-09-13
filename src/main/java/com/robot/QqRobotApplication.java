package com.robot;


import com.robot.service.ISensitiveWordService;
import love.forte.simbot.spring.autoconfigure.EnableSimbot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableSimbot
@EnableTransactionManagement
@MapperScan("com.robot.mapper")
@EnableScheduling
public class QqRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(QqRobotApplication.class, args);
    }

}
