/* Italy Company - Fast Team(C) 2023 */
package it.nowprj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableTransactionManagement
public class NowPrjBEApplication {
  public static void main(String[] args) {
    SpringApplication.run(NowPrjBEApplication.class, args);
  }
}
