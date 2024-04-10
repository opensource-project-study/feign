package example.github;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ExampleGithubWithSpringbootApplication implements SmartInitializingSingleton {

  public static void main(String[] args) {
    SpringApplication.run(ExampleGithubWithSpringbootApplication.class, args);
  }

  @Resource
  private GithubClient.Github github;

  @Override
  public void afterSingletonsInstantiated() {
    // Fetch and print a list of the contributors to this library.
    List<GithubClient.Contributor> contributors = github.contributors("OpenFeign", "feign");
    for (GithubClient.Contributor contributor : contributors) {
      System.out.println(contributor.login + " (" + contributor.contributions + ")");
    }
  }
}
