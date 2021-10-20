package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EVENT DAEMON APPLICATION 실행 파일
 */
@SpringBootApplication
public class Application {


  public static void main(String[] args) throws InterruptedException {
    System.setProperty("spring.profiles.active", "test");
    SpringApplication.run(Application.class, args).close();
  }

}
