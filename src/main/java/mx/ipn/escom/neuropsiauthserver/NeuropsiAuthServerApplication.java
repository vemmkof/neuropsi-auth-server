package mx.ipn.escom.neuropsiauthserver;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "neuropsi-auth-server")
public class NeuropsiAuthServerApplication {

  private static final Logger log = LoggerFactory.getLogger(NeuropsiAuthServerApplication.class);

  public static void main(String[] args) {
    if (args.length == 0) {
      log.info("No args");
      SpringApplication.run(NeuropsiAuthServerApplication.class);
    } else {
      String passedArgs = String.format("Passed args: %s", Arrays.toString(args));
      log.info(passedArgs);
      SpringApplication springApplication =
          new SpringApplication(NeuropsiAuthServerApplication.class);
      springApplication.setBannerMode(Mode.OFF);
      springApplication.run(args);
    }
  }

}
