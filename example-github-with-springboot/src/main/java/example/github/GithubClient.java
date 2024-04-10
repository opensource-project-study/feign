package example.github;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author wenboyu@fintopia.tech
 * @date 2024/4/10 16:56
 */
public class GithubClient {

  @FeignClient(value = "xxx", url = "https://api.github.com", configuration = {GithubClientConfiguration.class})
  public interface Github {

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo);

  }

  public static class Contributor {
    String login;
    int contributions;
  }
}
