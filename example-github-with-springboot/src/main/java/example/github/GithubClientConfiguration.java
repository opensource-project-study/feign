package example.github;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import org.springframework.context.annotation.Bean;

/**
 * @author wenboyu@fintopia.tech
 * @date 2024/4/10 16:53
 */
public class GithubClientConfiguration {

  @Bean
  public Decoder decoder() {
    return new GsonDecoder();
  }

//  @Bean
//  public RequestInterceptor requestInterceptor() {
//    return template -> template.header("Authorization", "token ");
//  }
}
