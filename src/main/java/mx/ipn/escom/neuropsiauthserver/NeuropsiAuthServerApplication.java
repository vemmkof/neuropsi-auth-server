package mx.ipn.escom.neuropsiauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "neuropsi-auth-server")
public class NeuropsiAuthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(NeuropsiAuthServerApplication.class, args);
  }

}
