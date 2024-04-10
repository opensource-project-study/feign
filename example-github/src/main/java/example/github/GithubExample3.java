package example.github;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

import java.util.List;

/**
 * @author wenboyu@fintopia.tech
 * @date 2024/4/10 16:02
 */
public class GithubExample3 {
  interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
  }

  public static class Contributor {
    String login;
    int contributions;
  }

  public static void main(String... args) {
    GitHub github = Feign.builder()
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .client(new OkHttpClient())
        .requestInterceptor(template -> template.header("Authorization", "token " + Constants.GITHUB_PERSONAL_TOKEN))
        .target(GitHub.class, "https://api.github.com");

    // Fetch and print a list of the contributors to this library.
    List<Contributor> contributors = github.contributors("OpenFeign", "feign");
    for (Contributor contributor : contributors) {
      System.out.println(contributor.login + " (" + contributor.contributions + ")");
    }
  }
}
