package edu.bu.met.cs665.domain;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;


/**
 * Summary.
 * 
 * @author tim_abiok
 * @course CS-665
 * @term Summer 2
 * @assignment PROJECT
 * @date 20 AUG 2020
 */
@Controller
public class GlobalCovidRestData implements RestViaHttp {

  private static Logger log = Logger.getLogger(GlobalCovidRestData.class);
  private static final String URL = "https://corona.lmao.ninja/v2/countries?yesterday=&sort=";
  private URI uri;
  public static ResponseEntity<String> result;

  /**
   * Generates uri for rest command line runner.
   */
  @Override
  public URI createUri(String url) {
    try {
      this.uri = new URI(url);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return uri;
  }

  /**
   * Getter for URI.
   */
  @Override
  public URI getUri() {
    return uri;
  }


  /**
   * Builds rest template for Bean.
   */
  @Override
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  /**
   * Command line runner wired to the spring application and generates rest data upon receipt of all
   * local and global parameters.
   */
  @Override
  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/json");
    return args -> {
      result = restTemplate.exchange(GlobalCovidRestData.URL, HttpMethod.GET,
          new HttpEntity<String>(headers), String.class);
      log.info(result.toString());
    };

  }


}


