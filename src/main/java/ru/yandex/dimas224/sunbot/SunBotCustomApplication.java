package ru.yandex.dimas224.sunbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class SunBotCustomApplication {
  public static void main(String[] args) {
    SpringApplication.run(SunBotCustomApplication.class, args);
  }

}
